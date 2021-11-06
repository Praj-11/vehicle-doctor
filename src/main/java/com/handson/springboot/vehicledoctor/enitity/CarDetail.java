package com.handson.springboot.vehicledoctor.enitity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car_details")
public class CarDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "type")
	private String type;
	

	@Column(name = "make")
	private String make;
	

	@Column(name = "model")
	private String model;
	

	@Column(name = "variant")
	private String variant;
	

	@Column(name = "year")
	private Integer year;
	

	@Column(name = "fuel_type")
	private String fuelType;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getVariant() {
		return variant;
	}


	public void setVariant(String variant) {
		this.variant = variant;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public String getFuelType() {
		return fuelType;
	}


	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}


	@Override
	public String toString() {
		return "CarDetail [id=" + id + ", type=" + type + ", make=" + make + ", model=" + model + ", variant=" + variant
				+ ", year=" + year + ", fuelType=" + fuelType + "]";
	}
	
	
	
}
