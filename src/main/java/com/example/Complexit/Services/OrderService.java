package com.example.Complexit.Services;


import com.example.Complexit.DTO.OrderDto;
import com.example.Complexit.Models.OrderVO;

import java.util.List;

public interface OrderService {

	List<OrderDto> getAllOrders();
	OrderVO getOrderByOrderId(int orderId);
	boolean addOrder(OrderDto orderDto);
	void updateOrder(OrderDto orderDto);
	void deleteOrder(int orderId);
}
