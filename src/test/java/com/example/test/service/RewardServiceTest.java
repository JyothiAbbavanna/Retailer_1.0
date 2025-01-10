package com.example.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.dto.RewardDTO;
import com.example.dto.TransactionDTO;
import com.example.service.RewardService;
import com.example.util.DataUtil;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class RewardServiceTest {

	private RewardService rewardService;

	@BeforeEach
	void setUp() {
		rewardService = new RewardService();
	}

	@Test
	void testCalculateRewards() {
		try (MockedStatic<DataUtil> mockedStatic = Mockito.mockStatic(DataUtil.class)) {
			List<TransactionDTO> transactions = Arrays.asList(
					new TransactionDTO(1L, 120.0, LocalDate.parse("2024-12-01")),
					new TransactionDTO(2L, 80.0, LocalDate.parse("2024-12-05")),
					new TransactionDTO(1L, 50.0, LocalDate.parse("2024-10-15")));
			mockedStatic.when(DataUtil::getData).thenReturn(transactions);

			List<RewardDTO> rewards = rewardService.calculateRewards();

			assertNotNull(rewards);
			assertEquals(2, rewards.size());
		}
	}

	@Test
	void testGetAllCustomerDetails() {
		try (MockedStatic<DataUtil> mockedStatic = Mockito.mockStatic(DataUtil.class)) {
			List<TransactionDTO> transactions = Arrays.asList(
					new TransactionDTO(1L, 100.0, LocalDate.parse("2024-11-01")),
					new TransactionDTO(2L, 150.0, LocalDate.parse("2024-09-10")));
			mockedStatic.when(DataUtil::getData).thenReturn(transactions);
			List<TransactionDTO> result = rewardService.getAllCustomerDetails();
			assertNotNull(result);
			assertEquals(2, result.size());
			assertEquals(1L, result.get(0).getCustomerId());
			assertEquals(150.0, result.get(1).getAmount());
		}
	}

	@Test
	void testGetCalculateRewardPointForCustomer() {
		Long customerId = 1L;
		try (MockedStatic<DataUtil> mockedStatic = Mockito.mockStatic(DataUtil.class)) {
			List<TransactionDTO> transactions = Arrays.asList(
					new TransactionDTO(1L, 120.0, LocalDate.parse("2024-12-01")),
					new TransactionDTO(1L, 50.0, LocalDate.parse("2024-10-01")));
			mockedStatic.when(DataUtil::getData).thenReturn(transactions);
			Map<Long, RewardDTO> rewardsMap = rewardService.getCalculateRewardPointForCustomer(customerId);

			assertNotNull(rewardsMap);
			assertEquals(1, rewardsMap.size());
			assertTrue(rewardsMap.containsKey(customerId));
			assertEquals(90, rewardsMap.get(customerId).getTotalPoints());
		}
	}

	@Test
	void testFilterLatestThreeMonthsRecords() {
		List<TransactionDTO> transactions = Arrays.asList(new TransactionDTO(1L, 120.0, LocalDate.parse("2024-12-01")),
				new TransactionDTO(2L, 80.0, LocalDate.parse("2024-09-01")),
				new TransactionDTO(1L, 150.0, LocalDate.parse("2024-08-01")));

		List<TransactionDTO> filteredTransactions = RewardService.filterLatestThreeMonthsRecords(transactions);

		assertNotNull(filteredTransactions);
		assertEquals(1, filteredTransactions.size());
		assertEquals(120.0, filteredTransactions.get(0).getAmount());
	}

	@Test
	void testCalculatePoints() {
		RewardService rewardService = new RewardService();

		int pointsForHighAmount = rewardService.calculatePoints(150.0);
		int pointsForMediumAmount = rewardService.calculatePoints(60.0);
		int pointsForLowAmount = rewardService.calculatePoints(40.0);

		assertEquals(150, pointsForHighAmount);
		assertEquals(10, pointsForMediumAmount);
		assertEquals(0, pointsForLowAmount);
	}

	@Test
	void testCalculatePoints_NullAmount() {
		RewardService rewardService = new RewardService();
		assertThrows(NullPointerException.class, () -> {
			rewardService.calculatePoints(null);
		});
	}

	@Test
	void testGetCalculateRewardPointForCustomer_InvalidDateFormat() {
		LocalDate invalidDate = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		try {
			invalidDate = LocalDate.parse("12-22-202", formatter);
			fail("Expected DateTimeParseException due to invalid date format");
		} catch (DateTimeParseException e) {
			assertFalse(e.getMessage().contains("invalidDate :" + invalidDate));
		}
	}

}
