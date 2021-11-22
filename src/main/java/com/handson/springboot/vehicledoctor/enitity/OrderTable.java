package com.handson.springboot.vehicledoctor.enitity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "order_table")
public class OrderTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "order_tracking_number")
	private String orderTrackingNumber;
	
	@Column(name = "order_description")
	private String orderDescription;
	
	@Column(name = "bill_amount")
	private Double billAmount;
	
	@Column(name = "order_created")
	@CreationTimestamp
	private Date orderCreated;
	
	@Column(name = "order_completed")
	private Date orderCompleted;
	
	@Column(name = "order_appointment_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private Date orderAppointmentDate;
	
	@Column(name = "status")
	private Character status;
	
	@Column(name = "payment_status")
	private Character paymentStatus;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "garage_id")
	private Garage garage;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mechanic_id")
	private Mechanic mechanic;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_table_spare_part", 
		joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")}, 
		inverseJoinColumns = {@JoinColumn(name = "spare_part_id", referencedColumnName = "id")})
	private List<SparePart> sparePartsUsed;

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

	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	public Date getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}

	public Date getOrderCompleted() {
		return orderCompleted;
	}

	public void setOrderCompleted(Date orderCompleted) {
		this.orderCompleted = orderCompleted;
	}

	public Date getOrderAppointmentDate() {
		return orderAppointmentDate;
	}

	public void setOrderAppointmentDate(Date orderAppointmentDate) {
		this.orderAppointmentDate = orderAppointmentDate;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Character getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Character paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Garage getGarage() {
		return garage;
	}

	public void setGarage(Garage garage) {
		this.garage = garage;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public List<SparePart> getSparePartsUsed() {
		return sparePartsUsed;
	}
	
	public void setSparePartsUsed(List<SparePart> sparePartsUsed) {
		this.sparePartsUsed = sparePartsUsed;
	}
	
	public void addSparePartsUsed(List<SparePart> spareParts) {
		
		this.sparePartsUsed.addAll(spareParts);
	}

	@Override
	public String toString() {
		return "OrderTable [id=" + id + ", orderTrackingNumber=" + orderTrackingNumber + ", orderDescription="
				+ orderDescription + ", billAmount=" + billAmount + ", orderCreated=" + orderCreated
				+ ", orderCompleted=" + orderCompleted + ", orderAppointmentDate=" + orderAppointmentDate + ", status="
				+ status + ", paymentStatus=" + paymentStatus + ", customer= {id: " + customer.getId() 
				+ ",name: " + customer.getName() + "}, garage= {id: " + garage.getId() + ", name: " + garage.getGarageName() 
				+ "}, mechanic= {id: " + mechanic.getId() + ", name: " + mechanic.getName() 
				+ "}, sparePartsUsed=" + sparePartsUsed + "]";
	}
}

