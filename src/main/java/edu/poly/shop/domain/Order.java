package edu.poly.shop.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private int orderId;
	private int customerId;
	private short status;
	private double amount;
	private Date orderDate;
}
