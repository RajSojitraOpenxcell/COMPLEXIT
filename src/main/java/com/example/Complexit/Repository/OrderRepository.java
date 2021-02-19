package com.example.Complexit.Repository;

import com.example.Complexit.Models.CustomerVO;
import com.example.Complexit.Models.OrderVO;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableJpaRepositories
public interface OrderRepository extends CrudRepository<OrderVO,Integer> {

	//List<CustomerVO> findByCustomerIdAndEmployeeId(CustomerVO cid,int eid);


}
