package com.ltts.car_wash.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int booking_id;
	private int customer_id;
	private LocalDate booking_date;
	private String start_time;
	private String car_number;
	private int car_type;
	private float cost;
	private String status;
	private LocalDate booked_on;
	
	
	
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Booking(int customer_id, LocalDate booking_date, String start_time, String car_number, int car_type,
			float cost, String status, LocalDate booked_on) {
		super();
		this.customer_id = customer_id;
		this.booking_date = booking_date;
		this.start_time = start_time;
		this.car_number = car_number;
		this.car_type = car_type;
		this.cost = cost;
		this.status = status;
		this.booked_on = booked_on;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public LocalDate getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(LocalDate booking_date) {
		this.booking_date = booking_date;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getCar_number() {
		return car_number;
	}
	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}
	public int getCar_type() {
		return car_type;
	}
	public void setCar_type(int car_type) {
		this.car_type = car_type;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getBooked_on() {
		return booked_on;
	}
	public void setBooked_on(LocalDate booked_on) {
		this.booked_on = booked_on;
	}
	public int getBooking_id() {
		return booking_id;
	}
	

}
