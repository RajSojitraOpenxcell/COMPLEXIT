package com.example.Complexit.Services;

import com.example.Complexit.Converter.CustomerConverter;
import com.example.Complexit.DTO.CustomerDto;
import com.example.Complexit.Models.CustomerPage;
import com.example.Complexit.Models.CustomerVO;
import com.example.Complexit.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerConverter customerConverter;

	@Override
	public List<CustomerDto> getAllCustomers() {
		List<CustomerVO> list = new ArrayList<>();
		customerRepository.findAll().forEach(e -> list.add(e));
		List<CustomerDto> clist = customerConverter.entityToDto(list);
		return clist;
	}

	@Override
	public CustomerVO getCustomerByCustomerId(int customerId) {
		CustomerVO obj=customerRepository.findById(customerId).get();
		return obj;
	}

	@Override
	public synchronized boolean addCustomer(CustomerVO customerVO) {
		System.out.println("  Customer Service impl Customer object :"+customerVO);
		List<CustomerVO> list = customerRepository.findByContactNameAndPostalCode(customerVO.getContactName(),customerVO.getPostalCode());
		if (list.size() > 0) {
			return false;
		} else {
			customerRepository.save(customerVO);
			return true;
		}
	}

	@Override
	public void updateCustomer(CustomerDto customerDto) {
		CustomerVO customerVO=customerConverter.dtoToEntity(customerDto);
		customerRepository.save(customerVO);
	}

	@Override
	public void deleteCustomer(int customerId) {
		customerRepository.delete(getCustomerByCustomerId(customerId));
	}

	public Page<CustomerVO> getCustomers(CustomerPage customerPage)
	{
		Sort sort= Sort.by(customerPage.getSortDirection(),customerPage.getSortBy());
		Pageable pageable=PageRequest.of(customerPage.getPageNumber(),customerPage.getPageSize(),sort);
		return customerRepository.findAll(pageable);
	}

}
