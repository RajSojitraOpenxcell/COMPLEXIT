package com.example.Complexit.DTO;

import com.example.Complexit.Models.CustomerVO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {

	private int orderId;
	private CustomerVO cid;
	private int employeeId;
	private LocalDateTime orderDate;
	private int shipperId;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public CustomerVO getCid() {
		return cid;
	}

	public void setCid(CustomerVO cid) {
		this.cid = cid;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public int getShipperId() {
		return shipperId;
	}

	public void setShipperId(int shipperId) {
		this.shipperId = shipperId;
	}
}
