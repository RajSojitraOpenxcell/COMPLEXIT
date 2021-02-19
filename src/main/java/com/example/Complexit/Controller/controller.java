package com.example.Complexit.Controller;

import com.example.Complexit.Converter.CustomerConverter;
import com.example.Complexit.Converter.OrderConverter;
import com.example.Complexit.Converter.UserConverter;
import com.example.Complexit.DTO.CustomerDto;
import com.example.Complexit.DTO.OrderDto;
import com.example.Complexit.DTO.UserDto;
import com.example.Complexit.Models.*;
import com.example.Complexit.Repository.CustomerRepository;
import com.example.Complexit.Services.CustomerService;
import com.example.Complexit.Services.MyUserDetailsService;
import com.example.Complexit.Services.OrderService;
import com.example.Complexit.Services.UserService;
import com.example.Complexit.Util.jwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class controller {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private jwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderConverter orderConverter;

	@Autowired
	private CustomerConverter customerConverter;

	@Autowired
	private UserService userService;


//	@RequestMapping(value = "/verifyUser", method = RequestMethod.GET)
//	public ResponseEntity loadRegister(@RequestBody UserVO userVO) throws Exception{
//
//	}

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String hello()
	{
		return "Hello World..!!";
	}

	@RequestMapping(value = "/authenticate",method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			System.out.println("Username :"+authenticationRequest.getUserName()+" & passwprd ="+authenticationRequest.getPassword());
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e){
			throw new Exception("Incorrect UserName and Password ..!!",e);
		}
		final UserDetails userDetails= userDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());
		final String jwt=jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	//Customer APIS

	@GetMapping("AllCustomers")
	public ResponseEntity<List<CustomerDto>> getAllCustomers() {
		List<CustomerDto> clist = customerService.getAllCustomers();
		return new ResponseEntity<List<CustomerDto>>(clist, HttpStatus.OK);
	}

//	@PostMapping("customer")
//	public ResponseEntity<Void> addCustomer(@RequestBody CustomerVO customerVO, UriComponentsBuilder builder) {
//		boolean flag = customerService.addCustomer(customerVO);
//		//boolean flagOrder=orderService.addOrder(customerVO);
//		if (flag == false) {
//			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//		}
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(builder.path("/customer/{id}").buildAndExpand(customerVO.getCustomerId()).toUri());
//		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//	}

	@PutMapping("customer")
	public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
		customerService.updateCustomer(customerDto);
		return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
	}

	@DeleteMapping("customer/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Integer id) {
		customerService.deleteCustomer(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	//Order APIS..........................................
	@GetMapping("AllOrders")
	public ResponseEntity<List<OrderDto>> getAllOrders() {
		List<OrderDto> olist = orderService.getAllOrders();
		return new ResponseEntity<List<OrderDto>>(olist, HttpStatus.OK);
	}

	@PostMapping("orders")
	public ResponseEntity<Void> addOrder(@RequestBody OrderDto orderDto, UriComponentsBuilder builder) {
		//log.warn();
		boolean flag = orderService.addOrder(orderDto);

		if (flag == false ) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/orders/{id}").buildAndExpand(orderDto.getOrderId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("orders")
	public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) {
		orderService.updateOrder(orderDto);
		return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
	}

	@DeleteMapping("orders/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id) {
		orderService.deleteOrder(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	//native Query API
	@GetMapping("leftJoin")
	public ResponseEntity<?> InnerJoin() {
		List list=new ArrayList();
		list=  customerRepository.findOneByInterval();
		System.out.println(list);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	//user APIS
	@PostMapping("RegisterUser")
	public ResponseEntity<String> RegisterUser(@RequestBody UserVO uservo, UriComponentsBuilder builder) {
		System.out.println("order controller..."+uservo);

		String massage=userService.addUser(uservo);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/RegisterUser/{username}").buildAndExpand(uservo.getUserName()).toUri());
		return new ResponseEntity<String>(massage, HttpStatus.CREATED);
	}

	@PutMapping("user")
	public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) {
		String massage=userService.updateUser(userDto);
		return new ResponseEntity<String>(massage, HttpStatus.OK);
	}

	@DeleteMapping("user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("user/{keyword}")
	public ResponseEntity<List<UserDto>> SearchUser(@PathVariable("keyword") String user) {
		List<UserDto> userDtoList=userService.searchUser(user);
		return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
	}

	@GetMapping("AllUsers")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> olist = userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(olist, HttpStatus.OK);
	}

}
