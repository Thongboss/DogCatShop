package edu.poly.shop.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	private Long orderId;
	private int customerId;
	private String status;
	private String address;
	private String codeOrder;
	private String email;
	private String phoneNumber;
	private double totalMoney;
	private Date orderDate;
}
