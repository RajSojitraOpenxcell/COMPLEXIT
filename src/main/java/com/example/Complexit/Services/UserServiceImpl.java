package com.example.Complexit.Services;

import com.example.Complexit.Converter.UserConverter;
import com.example.Complexit.DTO.UserDto;
import com.example.Complexit.Models.UserVO;
import com.example.Complexit.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Override
	public String addUser(UserVO userVO) {
		String massage = "";
		Optional<UserVO> userlist = userRepository.findByUserName(userVO.getUserName());
		if (!userlist.isEmpty()) {
			massage += " UserName Already taken. Try with Different Username.";
		}

		if(userVO.getFirstName().length()>15)
		{
			massage+=" First Name length must less than 15 charaters.";
		}
		if(userVO.getLastName().length()>15)
		{
			massage+=" Last Name length must less than 15 charaters.";
		}

		if(userVO.getPassword().length()<8)
		{
			massage+="password must be grater than 8 charater.";
		}

		if(massage.equals(""))
		{
			userRepository.save(userVO);
			massage="User Register Successfully.";
		}
		return massage;
	}

	@Override
	public String updateUser(UserDto userDto) {
		try{
			UserVO userVO = userConverter.dtoToEntity(userDto);
			Optional<UserVO> userExist = userRepository.findByUserName(userVO.getUserName());
			if (userExist.isEmpty()) {
				return "User Not Exist..!";
			} else {
				UserVO olduser=userExist.get();
				userVO.setUserId(olduser.getUserId());
				userVO.setPassword(olduser.getPassword());
				userRepository.save(userVO);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "User Updated SuccuessFully..!!";
	}

	@Override
	public void deleteUser(int userId) {
		List<UserVO> list=userRepository.findAllById(Collections.singleton(new Integer(userId)));
		if(list.size()>0)
		{
			UserVO user=list.get(0);
			user.setActive(false);
			userRepository.save(user);
		}
	}

	@Override
	public List<UserDto> searchUser(String user) {
		List<UserVO> list=userRepository.findOneByWord(user);
		List<UserDto> clist=userConverter.entityToDto(list);
		return clist;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserVO> list = new ArrayList<>();
		userRepository.findAll().forEach(e->list.add(e));
		List<UserDto> clist=userConverter.entityToDto(list);
		return clist;
	}

}
