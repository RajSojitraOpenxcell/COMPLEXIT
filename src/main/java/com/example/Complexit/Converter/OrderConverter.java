package com.example.Complexit.Converter;

import com.example.Complexit.DTO.OrderDto;
import com.example.Complexit.Models.OrderVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class OrderConverter {

	public OrderDto entityToDto(OrderVO orderVO)
	{
		OrderDto orderDto=new OrderDto();
		orderDto.setOrderId(orderVO.getOrderId());
		orderDto.setCid(orderVO.getCid());
		orderDto.setEmployeeId(orderVO.getEmployeeId());
		orderDto.setOrderDate(orderVO.getOrderDate());
		orderDto.setOrderId(orderVO.getShipperId());
		return orderDto;
	}
	public List<OrderDto> entityToDto(List<OrderVO> orderVOlist)
	{
		return orderVOlist.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}

	public OrderVO dtoToEntity(OrderDto orderDto)
	{
		OrderVO orderVO=new OrderVO();
		orderVO.setOrderId(orderDto.getOrderId());
		orderVO.setCid(orderDto.getCid());
		orderVO.setEmployeeId(orderDto.getEmployeeId());
		orderVO.setOrderDate(orderDto.getOrderDate());
		orderVO.setShipperId(orderDto.getShipperId());
		return orderVO;
	}

	public List<OrderVO> dtoToEntity(List<OrderDto> orderDtolist)
	{
		return orderDtolist.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
}
