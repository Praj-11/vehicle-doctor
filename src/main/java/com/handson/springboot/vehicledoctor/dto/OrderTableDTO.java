package com.handson.springboot.vehicledoctor.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderTableDTO {
	
	private Long id;
	
	private String orderTrackingNumber;
	
	private String orderDescription;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private Date orderAppointmentDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public Date getOrderAppointmentDate() {
		return orderAppointmentDate;
	}

	public void setOrderAppointmentDate(Date orderAppointmentDate) {
		this.orderAppointmentDate = orderAppointmentDate;
	}
	
	
}
