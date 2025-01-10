package com.example.dto;

import java.time.LocalDate;

public class TransactionDTO {
	private Long customerId;
	private Double amount;
	private LocalDate transactionDate;

	public TransactionDTO() {
	}

	public TransactionDTO(Long customerId, Double amount, LocalDate transactionDate) {
		this.customerId = customerId;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "TransactionDTO{" + "customerId=" + customerId + ", amount=" + amount + ", transactionDate="
				+ transactionDate + '}';
	}
}
