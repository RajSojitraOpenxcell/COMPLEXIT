package com.example.Complexit.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="OrderVO")
public class OrderVO {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="order_id")
	private int orderId;


	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinColumn(name="cus_id",referencedColumnName = "customer_id")
	private CustomerVO cid;

	@Column(name="cus_id" ,insertable = false,updatable = false)
	private Integer customerId;

	@Column(name="employee_id")
	private int employeeId;

	@Column(name="order_date")
	private LocalDateTime orderDate;

	@Column(name="shipper_id")
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

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "OrderVO{" +
				"orderId=" + orderId +
				", cid=" + cid +
				", customerId=" + customerId +
				", employeeId=" + employeeId +
				", orderDate=" + orderDate +
				", shipperId=" + shipperId +
				'}';
	}
}
