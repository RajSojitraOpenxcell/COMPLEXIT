package com.example.Complexit.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="CustomerVO")
public class CustomerVO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name="customer_name")
	private String customerName;

	@Column(name="contact_name")
	private String contactName;

	@Column(name="address")
	private String address;

	@Column(name="city")
	private String city;

	@Column(name="postal_code")
	private String postalCode;

	@Column(name="country")
	private String country;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "CustomerVO{" +
				"customerId=" + customerId +
				", customerName='" + customerName + '\'' +
				", contactName='" + contactName + '\'' +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", postalCode='" + postalCode + '\'' +
				", country='" + country + '\'' +
				'}';
	}

	public interface JoinProjection {
		Integer getCustomerId();
		String getAddress();
		String getCity();
		Integer getEmployeeId();
		Integer getShipperId();
	}
}
