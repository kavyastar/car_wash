package com.ltts.car_wash.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Cost {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cost_id;
	private float cost;
	private String car_type;
	
	public Cost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cost(int cost_id, float cost, String car_type) {
		super();
		
		this.cost = cost;
		this.car_type = car_type;
	}
	public int getCost_id() {
		return cost_id;
	}
	public void setCost_id(int cost_id) {
		this.cost_id = cost_id;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	
}
