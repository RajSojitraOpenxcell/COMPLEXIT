package com.example.Complexit.Converter;

import com.example.Complexit.DTO.UserDto;
import com.example.Complexit.Models.UserVO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

	public UserDto entityToDto(UserVO userVO)
	{
		UserDto userDto=new UserDto();
		userDto.setUserName(userVO.getUserName());
		userDto.setFirstName(userVO.getFirstName());
		userDto.setLastName(userVO.getLastName());
		return userDto;
	}

	public List<UserDto> entityToDto(List<UserVO> userVOList)
	{
		return userVOList.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}

	public UserVO dtoToEntity(UserDto userDto)
	{
		UserVO userVO=new UserVO();
		userVO.setUserName(userDto.getUserName());
		userVO.setFirstName(userDto.getFirstName());
		userVO.setLastName(userDto.getLastName());
		return userVO;
	}

	public List<UserVO> dtoToEntity(List<UserDto> userDtoList)
	{
		return userDtoList.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
}
