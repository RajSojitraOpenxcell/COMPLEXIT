package com.example.Complexit.Repository;

import com.example.Complexit.Models.CustomerVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerVO,Integer>, PagingAndSortingRepository<CustomerVO,Integer> {

	List<CustomerVO> findByContactNameAndPostalCode(String contactName,String postalCode);

	@Query(value = "SELECT c.customer_id as CustomerId,c.address as address,c.city as city,o.employee_id as employeeId,o.shipper_id as shipperId from customervo c LEFT JOIN ordervo o on c.customer_id=o.cus_id", nativeQuery = true)
	List<CustomerVO.JoinProjection> findOneByInterval();

}
