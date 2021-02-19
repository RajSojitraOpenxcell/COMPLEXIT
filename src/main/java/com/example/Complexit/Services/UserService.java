package com.example.Complexit.Services;

import com.example.Complexit.DTO.OrderDto;
import com.example.Complexit.DTO.UserDto;
import com.example.Complexit.Models.UserVO;

import java.util.List;

public interface UserService {

	public String addUser(UserVO userVO);
	String updateUser(UserDto userDto);
	void deleteUser(int userId);
	List<UserDto> searchUser(String user);
	List<UserDto> getAllUsers();
}
