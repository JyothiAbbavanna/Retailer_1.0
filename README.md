# Retailer_1.0
# Reward Points Calculation Service

## Overview

This project provides a **Reward Points Calculation Service** for customers based on their transaction history. It calculates reward points for each customer based on the transactions they make within the last three months. The service provides endpoints for retrieving customer transaction details, calculating rewards for individual customers, and calculating rewards for all customers.

## Features

- **Transaction Data Processing:** Handles transaction data and calculates reward points based on the transaction amount.
- **Reward Calculation:** Applies different reward points rules to calculate points based on the transaction value.
- **Filtering by Date:** Filters transactions that occurred in the last three months.
- **RESTful API Endpoints:** Exposes APIs for retrieving transaction details and reward calculations.

## Technologies Used

- **Spring Boot:** The project is built with Spring Boot to handle the REST API.
- **JUnit 5:** Used for unit testing and integration testing.
- **Mockito:** Used for mocking dependencies in unit tests.
- **Maven:** Dependency management and build tool.
- **Java 11+:** The project is implemented in Java 11+.

## Project Structure

The project follows a standard Maven-based structure:

