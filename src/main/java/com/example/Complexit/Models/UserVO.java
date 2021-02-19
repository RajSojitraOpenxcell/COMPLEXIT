package com.example.Complexit.Models;

import javax.persistence.*;

@Entity
@Table(name="UserVO")
public class UserVO {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="user_id")
	private Integer userId;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="user_name",unique = true)
	private String userName;

	@Column(name="password")
	private String password;

	@Column(name="active")
	private boolean active=true;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserVO{" +
				"userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", active=" + active +
				'}';
	}
}

