package com.ltts.car_wash.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ltts.car_wash.Bo.BookingBo;
import com.ltts.car_wash.model.Booking;
import com.ltts.car_wash.model.Cost;
import com.ltts.car_wash.model.User;

@RestController
public class BookingController {
	
	@RequestMapping("/bookings")
	public ModelAndView  mm2()
	{
		return new ModelAndView("allbookings");
	}
	
	

}
