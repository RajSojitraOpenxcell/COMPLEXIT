package com.example.Complexit.Repository;


import com.example.Complexit.Models.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserVO,Integer> {
	Optional<UserVO> findByUserName(String userName);

	@Query(value = "SELECT * from uservo u where u.first_name LIKE %:word% or u.last_name LIKE %:word% or u.user_name LIKE %:word% ", nativeQuery = true)
	List<UserVO> findOneByWord(String word);

}
