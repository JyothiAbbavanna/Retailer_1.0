package com.example.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.example.dto.TransactionDTO;

public class DataUtil {

	public static List<TransactionDTO> getData() {

		List<TransactionDTO> transactions = Arrays.asList(
				new TransactionDTO(1L, 120.0, LocalDate.parse("2025-01-01")),
				new TransactionDTO(2L, 80.0, LocalDate.parse("2024-12-05")),
				new TransactionDTO(1L, 65.0, LocalDate.parse("2024-11-15")),
				new TransactionDTO(1L, 300.0, LocalDate.parse("2024-12-20")),
				new TransactionDTO(2L, 75.0, LocalDate.parse("2024-11-07")),
				new TransactionDTO(3L, 500.0, LocalDate.parse("2024-08-07")),
				new TransactionDTO(4L, 350.0, LocalDate.parse("2023-08-07")),
				new TransactionDTO(4L, 345.0, LocalDate.parse("2022-08-07")),
				new TransactionDTO(5L, 130.0, LocalDate.parse("2024-12-07")),
				new TransactionDTO(3L, 80.0, LocalDate.parse("2024-12-05")),
				new TransactionDTO(1L, 200.0, LocalDate.parse("2024-10-15")),
				new TransactionDTO(2L, 90.0, LocalDate.parse("2024-09-05")),
				new TransactionDTO(3L, 400.0, LocalDate.parse("2024-07-07")),
				new TransactionDTO(5L, 250.0, LocalDate.parse("2024-06-01")),
				new TransactionDTO(6L, 300.0, LocalDate.parse("2024-05-07")),
				new TransactionDTO(2L, 110.0, LocalDate.parse("2024-04-15")),
				new TransactionDTO(1L, 140.0, LocalDate.parse("2024-03-10")),
				new TransactionDTO(3L, 75.0, LocalDate.parse("2024-02-20")),
				new TransactionDTO(5L, 80.0, LocalDate.parse("2024-01-15")),
				new TransactionDTO(4L, 500.0, LocalDate.parse("2023-12-25")),
				new TransactionDTO(7L, 95.0, LocalDate.parse("2023-11-10")),
				new TransactionDTO(1L, 100.0, LocalDate.parse("2023-10-05")),
				new TransactionDTO(3L, 150.0, LocalDate.parse("2023-09-15")),
				new TransactionDTO(5L, 600.0, LocalDate.parse("2023-08-07")),
				new TransactionDTO(4L, 700.0, LocalDate.parse("2023-07-07")),
				new TransactionDTO(2L, 450.0, LocalDate.parse("2023-06-15")),
				new TransactionDTO(1L, 50.0, LocalDate.parse("2023-05-01")),
				new TransactionDTO(3L, 120.0, LocalDate.parse("2023-04-20")),
				new TransactionDTO(5L, 340.0, LocalDate.parse("2023-03-15")),
				new TransactionDTO(4L, 410.0, LocalDate.parse("2023-02-10")),
				new TransactionDTO(1L, 390.0, LocalDate.parse("2023-01-05")),
				new TransactionDTO(2L, 310.0, LocalDate.parse("2022-12-25")),
				new TransactionDTO(3L, 290.0, LocalDate.parse("2022-11-15")),
				new TransactionDTO(5L, 470.0, LocalDate.parse("2022-10-10")),
				new TransactionDTO(4L, 430.0, LocalDate.parse("2022-09-05")),
				new TransactionDTO(2L, 550.0, LocalDate.parse("2022-08-01")),
				new TransactionDTO(1L, 180.0, LocalDate.parse("2022-07-20")),
				new TransactionDTO(8L, 220.0, LocalDate.parse("2022-06-15")),
				new TransactionDTO(5L, 380.0, LocalDate.parse("2022-05-07")),
				new TransactionDTO(4L, 140.0, LocalDate.parse("2022-04-10")),
				new TransactionDTO(1L, 210.0, LocalDate.parse("2022-03-15")),
				new TransactionDTO(2L, 80.0, LocalDate.parse("2022-02-25")),
				new TransactionDTO(9L, 360.0, LocalDate.parse("2022-01-20")),
				new TransactionDTO(5L, 120.0, LocalDate.parse("2021-12-15")),
				new TransactionDTO(4L, 500.0, LocalDate.parse("2021-11-10")),
				new TransactionDTO(1L, 100.0, LocalDate.parse("2021-10-05")),
				new TransactionDTO(2L, 270.0, LocalDate.parse("2021-09-15")),
				new TransactionDTO(3L, 320.0, LocalDate.parse("2021-08-07")),
				new TransactionDTO(5L, 90.0, LocalDate.parse("2021-07-01")),
				new TransactionDTO(4L, 340.0, LocalDate.parse("2021-06-15")),
				new TransactionDTO(2L, 50.0, LocalDate.parse("2021-05-10")));

		return transactions;
	}

}
