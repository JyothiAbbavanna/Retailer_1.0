package com.example.dto;

import java.util.HashMap;
import java.util.Map;

public class RewardDTO {
	private Long customerId;
	private Map<String, Integer> monthlyPoints;
	private Integer totalPoints;

	public RewardDTO(Long customerId) {
		this.customerId = customerId;
		this.monthlyPoints = new HashMap<>();
		this.totalPoints = 0;
	}

	public void addPoints(String month, int points) {
		this.monthlyPoints.put(month, this.monthlyPoints.getOrDefault(month, 0) + points);
		this.totalPoints += points;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Map<String, Integer> getMonthlyPoints() {
		return monthlyPoints;
	}

	public void setMonthlyPoints(Map<String, Integer> monthlyPoints) {
		this.monthlyPoints = monthlyPoints;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	@Override
	public String toString() {
		return "RewardDTO{" + "customerId=" + customerId + ", monthlyPoints=" + monthlyPoints + ", totalPoints="
				+ totalPoints + '}';
	}
}
