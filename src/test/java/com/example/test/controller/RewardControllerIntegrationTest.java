package com.example.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.reward.RewardPointsApplication;

@SpringBootTest(classes = RewardPointsApplication.class)
@AutoConfigureMockMvc
public class RewardControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {

	}

	@Test
	void testCalculateRewardsIntegration() throws Exception {
		mockMvc.perform(get("/reward/customer/all")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(4))
				.andExpect(jsonPath("$[0].customerId").value(1L));
	}

	@Test
	void testGetCalculateRewardPointForCustomer_Success() throws Exception {
		mockMvc.perform(get("/reward/customer/{customerId}", 1L)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(1));
	}

	@Test
	void testGetCalculateRewardPointForCustomer_CustomerNotFound() throws Exception {
		mockMvc.perform(get("/reward/customer/{customerId}", 999L)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.error").value("Customer with ID 999 not found."));
	}
}
