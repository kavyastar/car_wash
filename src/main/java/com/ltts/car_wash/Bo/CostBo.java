package com.ltts.car_wash.Bo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ltts.car_wash.model.Booking;
import com.ltts.car_wash.model.Cost;

@Repository
public interface CostBo extends JpaRepository<Cost, Integer> {
	
	@Query("SELECT b FROM Cost b WHERE b.cost_id = ?1")
	Cost findById(int cost_id);

}
