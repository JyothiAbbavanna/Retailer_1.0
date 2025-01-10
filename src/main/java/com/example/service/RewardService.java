package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.RewardDTO;
import com.example.dto.TransactionDTO;
import com.example.exception.CustomerNotFoundException;
import com.example.util.DataUtil;

/**
 * RewardService is a service class responsible for calculating and managing
 * reward points for customers based on their transaction data. It processes
 * transactions, calculates points, and filters the latest records within a
 * specified period (last three months).
 */
@Service
public class RewardService {

	/**
	 * Calculates rewards for all customers based on their transaction history in
	 * the last three months. This method processes each customer's transactions,
	 * calculates reward points for each, and returns a list of RewardDTO objects
	 * representing the reward points accumulated by each customer.
	 * 
	 * @return a list of RewardDTO objects containing the reward points for each
	 *         customer
	 */
	public List<RewardDTO> calculateRewards() {
		Map<Long, RewardDTO> rewardsMap = new HashMap<>();
		List<TransactionDTO> transactions = filterLatestThreeMonthsRecords(DataUtil.getData());
		for (TransactionDTO transaction : transactions) {
			Long customerId = transaction.getCustomerId();
			Double amount = transaction.getAmount();
			String month = transaction.getTransactionDate().getMonth().toString();

			int points = calculatePoints(amount);

			rewardsMap.putIfAbsent(customerId, new RewardDTO(customerId));
			rewardsMap.get(customerId).addPoints(month, points);
		}

		return new ArrayList<>(rewardsMap.values());
	}

	/**
	 * Calculates reward points for a given transaction amount. Points are awarded
	 * based on specific rules: - For amounts greater than 100, points are awarded
	 * at a rate of 2 points for each dollar over 100. - For amounts between 50 and
	 * 100, points are awarded at a rate of 1 point for each dollar over 50 (up to
	 * 50).
	 * 
	 * @param amount the transaction amount
	 * @return the number of points calculated for the given amount
	 */
	public int calculatePoints(Double amount) {
		int points = 0;
		if (amount > 100) {
			points += (amount - 100) * 2;
		}
		if (amount > 50) {
			points += Math.min(amount - 50, 50) * 1;
		}
		return points;
	}

	/**
	 * Retrieves all customer transaction details. This method returns the entire
	 * list of transaction records.
	 * 
	 * @return a list of all customer transaction details
	 */
	public List<TransactionDTO> getAllCustomerDetails() {
		return DataUtil.getData();
	}

	/**
	 * Filters the given list of transactions to return only those within the last
	 * three months. Transactions that are older than three months from the current
	 * date are excluded.
	 * 
	 * @param transactions a list of TransactionDTO objects
	 * @return a filtered list of transactions from the last three months
	 */
	public static List<TransactionDTO> filterLatestThreeMonthsRecords(List<TransactionDTO> transactions) {
		LocalDate currentDate = LocalDate.now();
		LocalDate threeMonthsAgo = currentDate.minusMonths(3);
		return transactions.stream().filter(transaction -> transaction != null)
				.filter(transaction -> transaction.getTransactionDate().isAfter(threeMonthsAgo)
						|| transaction.getTransactionDate().isEqual(threeMonthsAgo))
				.collect(Collectors.toList());
	}

	/**
	 * Calculates and retrieves the reward points for a specific customer based on
	 * their transaction history from the last three months.
	 * CustomerNotFoundException if given customerId not found in DataUtil(acts like
	 * DB)
	 * 
	 * @param customerId the unique identifier of the customer
	 * @return a map containing the calculated reward points for the customer
	 * @throws CustomerNotFoundException if given customerId not found in
	 *                                   DataUtil(acts like DB)
	 */
	public Map<Long, RewardDTO> getCalculateRewardPointForCustomer(Long customerId) {
		Map<Long, RewardDTO> rewardsMap = new HashMap<>();
		boolean exists = DataUtil.getData().stream().anyMatch(item -> customerId.equals(item.getCustomerId()));
		if (!exists) {
			throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
		}
		LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
		List<TransactionDTO> filteredTransactions = DataUtil.getData().stream()
				.filter(transaction -> transaction.getCustomerId().equals(customerId))
				.filter(transaction -> !transaction.getTransactionDate().isBefore(threeMonthsAgo)).toList();
		for (TransactionDTO transaction : filteredTransactions) {
			Long custId = transaction.getCustomerId();
			Double amount = transaction.getAmount();
			String month = transaction.getTransactionDate().getMonth().toString();

			int points = calculatePoints(amount);

			rewardsMap.putIfAbsent(custId, new RewardDTO(custId));
			rewardsMap.get(custId).addPoints(month, points);
		}
		return rewardsMap;
	}
}
