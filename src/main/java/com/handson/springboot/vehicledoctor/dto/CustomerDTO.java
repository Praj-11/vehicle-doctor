package com.handson.springboot.vehicledoctor.dto;

import com.handson.springboot.vehicledoctor.enitity.Address;
import com.handson.springboot.vehicledoctor.enitity.CarDetail;

public class CustomerDTO {
	
	private Long id;
	
	private String name;
	
	private Address address;
	
	private Long phoneNumber;

	private String email;
	
	private String password;
	
	private CarDetail carDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CarDetail getCarDetails() {
		return carDetails;
	}

	public void setCarDetails(CarDetail carDetails) {
		this.carDetails = carDetails;
	}
	
	
}
