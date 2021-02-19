package com.example.Complexit.Converter;

import com.example.Complexit.DTO.CustomerDto;
import com.example.Complexit.Models.CustomerVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {

	public CustomerDto entityToDto(CustomerVO customerVO)
	{
		CustomerDto customerDto=new CustomerDto();
		customerDto.setCustomerId(customerVO.getCustomerId());
		customerDto.setAddress(customerVO.getAddress());
		customerDto.setCustomerName(customerVO.getCustomerName());
		customerDto.setContactName(customerVO.getContactName());
		customerDto.setCity(customerVO.getCity());
		customerDto.setPostalCode(customerVO.getPostalCode());
		return customerDto;
	}

	public List<CustomerDto> entityToDto(List<CustomerVO> customerVOlist)
	{
		return customerVOlist.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}

	public CustomerVO dtoToEntity(CustomerDto customerDto)
	{
		CustomerVO customerVO=new CustomerVO();
		customerVO.setCustomerId(customerDto.getCustomerId());
		customerVO.setCustomerName(customerDto.getCustomerName());
		customerVO.setContactName(customerDto.getContactName());
		customerVO.setAddress(customerDto.getAddress());
		customerVO.setCity(customerDto.getCity());
		customerVO.setPostalCode(customerDto.getPostalCode());
		return customerVO;
	}

	public List<CustomerVO> dtoToEntity(List<CustomerDto> customerDtolist)
	{
		return customerDtolist.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
}
