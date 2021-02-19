package com.example.Complexit.Services;


import com.example.Complexit.Converter.OrderConverter;
import com.example.Complexit.DTO.OrderDto;
import com.example.Complexit.Models.CustomerVO;
import com.example.Complexit.Models.OrderVO;
import com.example.Complexit.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderConverter orderConverter;

	@Autowired CustomerService customerService;

	@Override
	public List<OrderDto> getAllOrders() {
		List<OrderVO> list = new ArrayList<>();
		orderRepository.findAll().forEach(e->list.add(e));
		List<OrderDto> clist=orderConverter.entityToDto(list);
		return clist;
	}

	@Override
	public OrderVO getOrderByOrderId(int orderId) {
		OrderVO obj=orderRepository.findById(orderId).get();
		return obj;
	}

	@Override
	public synchronized boolean addOrder(OrderDto orderDto) {
//		List<CustomerVO> list = orderRepository.findByCustomerIdAndEmployeeId(orderVO.getCid(),orderVO.getEmployeeId());
//		if (list.size() > 0) {
//			return false;
//		} else {

		boolean flagCustoner=customerService.addCustomer(orderDto.getCid());
		OrderVO orderVO=orderConverter.dtoToEntity(orderDto);
		orderVO.setCustomerId(new Integer(orderVO.getCid().getCustomerId()));
		orderRepository.save(orderVO);
		return true;
//		}
	}

	@Override
	public void updateOrder(OrderDto orderDto) {
		OrderVO orderVO=orderConverter.dtoToEntity(orderDto);
		orderRepository.save(orderVO);
	}

	@Override
	public void deleteOrder(int orderId) {
		orderRepository.delete(getOrderByOrderId(orderId));
	}
}
