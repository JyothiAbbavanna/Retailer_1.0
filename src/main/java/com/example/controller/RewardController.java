package com.example.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.RewardDTO;
import com.example.dto.TransactionDTO;
import com.example.exception.CustomerNotFoundException;
import com.example.service.RewardService;

/**
 * RewardController is a REST controller responsible for managing the reward
 * calculation system. It exposes various endpoints to interact with the reward
 * services such as calculating rewards for all customers and specific customer,
 * fetching transaction details, and handling customer-specific reward
 * calculations.
 */
@RestController
@RequestMapping("/reward")
public class RewardController {

	@Autowired
	private RewardService rewardService;

	private static final Logger logger = LoggerFactory.getLogger(RewardController.class);

	/**
	 * . This is a simple endpoint to verify that the reward service is up and
	 * running.
	 * 
	 * @return a ResponseEntity with a message confirming the service is working.
	 */
	@GetMapping("/test")
	public ResponseEntity<Map<String, String>> testEndpoint() {
		logger.info("Test endpoint accessed");
		return ResponseEntity.ok(Map.of("message", "Welcome to reward calculation!"));
	}

	/**
	 * This endpoint retrieves the transaction details for all customers from the
	 * reward service.
	 * 
	 * @return a ResponseEntity containing a list of all transaction details
	 */
	@GetMapping("/transaction-details")
	public ResponseEntity<List<TransactionDTO>> getAllCustomerDetails() {
		logger.info("Fetching all transaction details");
		List<TransactionDTO> transDetailsList = rewardService.getAllCustomerDetails();
		return ResponseEntity.ok(transDetailsList);
	}

	/**
	 * Retrieves and calculates the reward points for a specific customer based on
	 * their transaction history. If the customer is not found, it returns a 404
	 * status with an error message.
	 * 
	 * @param customerId the unique identifier of the customer
	 * @return a ResponseEntity containing the calculated reward points for the
	 *         customer, or an error message if not found
	 */
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Object> getCalculateRewardPointForCustomer(@PathVariable Long customerId) {
		try {
			logger.info("Calculating rewards for customer with ID: {}", customerId);
			Map<Long, RewardDTO> rewards = rewardService.getCalculateRewardPointForCustomer(customerId);
			return ResponseEntity.ok(rewards);
		} catch (CustomerNotFoundException ex) {
			logger.error("Customer not found: {}", customerId, ex);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
		}
	}

	/**
	 * Calculates and retrieves reward points for all customers. This endpoint
	 * retrieves and calculates the reward points for all customers, providing an
	 * overall picture of reward distribution across the customers spent on their
	 * amount.
	 * 
	 * @return a ResponseEntity containing a list of rewards for all customers
	 */
	@GetMapping("/customer/all")
	public ResponseEntity<List<RewardDTO>> calculateRewards() {
		logger.info("Calculating rewards for all customers");
		List<RewardDTO> rewards = rewardService.calculateRewards();
		return ResponseEntity.ok(rewards);
	}
}
