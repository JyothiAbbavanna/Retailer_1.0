package com.example.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.controller.RewardController;
import com.example.dto.RewardDTO;
import com.example.dto.TransactionDTO;
import com.example.exception.CustomerNotFoundException;
import com.example.service.RewardService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RewardControllerTest {

	private MockMvc mockMvc;

	@Mock
	private RewardService rewardService;

	@InjectMocks
	private RewardController rewardController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(rewardController).build();
	}

	@Test
	void testTestEndpoint() throws Exception {
		mockMvc.perform(get("/reward/test")).andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Welcome to reward calculation!"));
	}

	@Test
	void testGetAllCustomerDetails() throws Exception {
		List<TransactionDTO> transactionDetails = Arrays.asList(
				new TransactionDTO(1L, 120.0, LocalDate.parse("2025-01-01")),
				new TransactionDTO(2L, 80.0, LocalDate.parse("2024-12-20")));
		when(rewardService.getAllCustomerDetails()).thenReturn(transactionDetails);

		mockMvc.perform(get("/reward/transaction-details")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(2)).andExpect(jsonPath("$[0].customerId").value(1L))
				.andExpect(jsonPath("$[1].customerId").value(2L));
	}

	@Test
	void testGetCalculateRewardPointForCustomer() throws Exception {
		Long customerId = 1L;
		Map<Long, RewardDTO> rewardMap = Map.of(customerId, new RewardDTO(customerId));

		when(rewardService.getCalculateRewardPointForCustomer(customerId)).thenReturn(rewardMap);

		mockMvc.perform(get("/reward/customer/{customerId}", customerId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(1)).andExpect(jsonPath("$[\"1\"]").exists());
	}

	@Test
	void testCalculateRewards() throws Exception {
		List<RewardDTO> rewards = Arrays.asList(new RewardDTO(1L), new RewardDTO(2L));
		when(rewardService.calculateRewards()).thenReturn(rewards);

		mockMvc.perform(get("/reward/customer/all")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(2))
				.andExpect(jsonPath("$[0].customerId").value(1L)).andExpect(jsonPath("$[1].customerId").value(2L));
	}

	@Test
	void testGetAllCustomerDetails_NoTransactions() throws Exception {
	    when(rewardService.getAllCustomerDetails()).thenReturn(Arrays.asList());
	    mockMvc.perform(get("/reward/transaction-details"))
	           .andExpect(status().isOk())
	           .andExpect(jsonPath("$.size()").value(0));
	}

	@Test
	void testCustomerNotFoundException() throws Exception {
		Long customerId = 10L;
		Mockito.when(rewardService.getCalculateRewardPointForCustomer(customerId))
				.thenThrow(new CustomerNotFoundException("Customer with ID " + customerId + " not found."));
		mockMvc.perform(get("/reward/customer/" + customerId)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.error").value("Customer with ID " + customerId + " not found."));
	}
}
