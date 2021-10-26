package com.handson.springboot.vehicledoctor.enitity;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
    
	  @Id
	  @GeneratedValue(strategy= GenerationType.IDENTITY)
	  @Column(name="id")
	  private Long id;
	  
	  @Column(name="name")
	  private String name;
	  
	  @Column(name="address_id")
	  private Long address_id;
	  
	  @Column(name="phone_number", columnDefinition="Decimal(10,2) default null")
	  private Long phone_number;
	  
	  @Column(name="image_url")
	  private String image_url;
	  
	  @Column(name="email_id")
	  private String email_id;
	  
	  @Column(name="password")
	  private String password;
	  
	  @Column(name="car_id")
	  private Long car_id;

	  private boolean loggedIn;
	  
	  public Customer() {
	    }
	    public Customer(Long id,String name, Long address_id,Long phone_number,
	    		String image_url,String email_id, String password,Long car_id ) {
	        
	    	this.id=id;
	    	this.name=name;
	    	this.address_id=address_id;
	    	this.phone_number=phone_number;
	    	this.image_url=image_url;
	    	this.email_id=email_id;
	    	this.password=password;
	    	this.car_id=car_id;
	    	this.loggedIn = false;
	    }
	    	
	    public Long getId() {
	        return id;
	    }
	    
	    public String getName() {
	    	return name;
	    }
	    public void setName(String name) {
	    	this.name=name;
	    }
	    
	    public Long getAddressId() {
	    	return address_id;
	    }
	    public void setAddressId(Long address_id) {
	    	this.address_id=address_id;
	    }
	    
	    public Long getPhoneNumber() {
	    	return phone_number;
	    }
	    public void setPhoneNumber(Long phone_number) {
	    	this.phone_number=phone_number;
	    }
	    
	    public String getEmailId() {
	    	return email_id;
	    }
	    public void setEmailId(String email_id) {
	    	this.email_id=email_id;
	    }
	    
	    public String getPassword() {
	    	return password;
	    }
	    public void setPassword(String password) {
	    	this.password=password;
	    }
	    
	    public Long getCarId() {
	    	return car_id;
	    }
	    public void setCarId(Long car_id) {
	    	this.car_id=car_id;
	    }
		public boolean isLoggedIn() {
			return loggedIn;
		}
		public void setLoggedIn(boolean loggedIn) {
	        this.loggedIn = loggedIn;
	    }
	
		@Override
	    public String toString() {
	        return "Customer:\nName:"+this.name+"\nEmail:"+this.email_id+"\nPhone Number:"+this.phone_number;
	    }
	
	
}
