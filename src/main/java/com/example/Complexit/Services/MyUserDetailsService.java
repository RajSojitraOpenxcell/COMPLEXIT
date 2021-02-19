package com.example.Complexit.Services;

import com.example.Complexit.Models.MyUserDetails;
import com.example.Complexit.Models.UserVO;
import com.example.Complexit.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<UserVO> user=userRepository.findByUserName(userName);
		System.out.println("In myuserdetailsService user vo "+user+"   username:"+userName);
		user.orElseThrow(()->new UsernameNotFoundException("User not Found "+userName));
		return user.map(MyUserDetails::new).get();
	}
}
