package com.example.Complexit.Services;

import com.example.Complexit.DTO.CustomerDto;
import com.example.Complexit.Models.CustomerVO;

import java.util.List;

public interface CustomerService {

	List<CustomerDto> getAllCustomers();
	CustomerVO getCustomerByCustomerId(int customerId);
	boolean addCustomer(CustomerVO customerVO);
	void updateCustomer(CustomerDto customerDto);
	void deleteCustomer(int customerId);
}
