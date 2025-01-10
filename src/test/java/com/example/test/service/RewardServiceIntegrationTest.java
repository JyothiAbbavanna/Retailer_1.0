package com.example.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dto.RewardDTO;
import com.example.exception.CustomerNotFoundException;
import com.example.reward.RewardPointsApplication;
import com.example.service.RewardService;

@SpringBootTest(classes = RewardPointsApplication.class)
@AutoConfigureMockMvc
class RewardServiceIntegrationTest {

	@Autowired
	private RewardService rewardService;

	@Test
	void testGetCalculateRewardPointForCustomer_Success() {
		Map<Long, RewardDTO> rewards = rewardService.getCalculateRewardPointForCustomer(1L);
		assertEquals(1, rewards.size());
		assertEquals(90, rewards.get(1L).getMonthlyPoints().get("JANUARY"));
	}

	@Test
	void testGetCalculateRewardPointForCustomer_CustomerNotFound() {
		assertThrows(CustomerNotFoundException.class, () -> rewardService.getCalculateRewardPointForCustomer(999L));
	}
}
