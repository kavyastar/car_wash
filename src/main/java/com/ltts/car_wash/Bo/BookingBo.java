package com.ltts.car_wash.Bo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ltts.car_wash.model.Booking;

@Repository
public interface BookingBo extends JpaRepository<Booking, Integer> {
	
	
	//@Query(value="select b from Booking b where b.booking_date = %searchtext% and b.start_time = %searchtext%")    
	@Query("SELECT b FROM Booking b WHERE b.booking_date = ?1 and b.start_time = ?2")
	List<Booking> findByBooking_dateAndStart_time(LocalDate booking_date, String start_time);
	
	@Query("SELECT b FROM Booking b WHERE b.customer_id = ?1")
	List<Booking> findByCustomer_id(int customer_id);

	@Query("SELECT b FROM Booking b WHERE b.booking_id = ?1")
	Booking findById(int booking_id);
	
	
	
}
