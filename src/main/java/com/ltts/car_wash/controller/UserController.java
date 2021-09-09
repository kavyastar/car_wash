package com.ltts.car_wash.controller;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ltts.car_wash.Bo.BookingBo;
import com.ltts.car_wash.Bo.CostBo;
import com.ltts.car_wash.Bo.UserBo;
import com.ltts.car_wash.model.Booking;
import com.ltts.car_wash.model.Cost;
import com.ltts.car_wash.model.User;
import com.ltts.car_wash.service.UserService;




@RestController
public class UserController {
	
	@Autowired
	UserBo ub;
	@Autowired
	BookingBo bb;
	@Autowired
	CostBo cb;
	
	@Autowired 
	UserService us;
	
	@RequestMapping("/")
	public ModelAndView  m1()
	{
		return new ModelAndView("index");
	}
	
	@RequestMapping("/ks")
    public String home(){
        return "Hello World!";
    }
	@RequestMapping("/register")
	public ModelAndView  mm2()
	{
		return new ModelAndView("register");
	}
    
	@RequestMapping(value="/insertuser", method = RequestMethod.POST)
    public ModelAndView saveProduct(@RequestParam("file") MultipartFile file,
    		@RequestParam("name") String name,
    		@RequestParam("email") String email,
    		@RequestParam("password") String password,
    		@RequestParam("mobile") String mobile,
    		@RequestParam("address") String address,
    		@RequestParam("gender") String gender
    		)
    {
		
		 	us.saveUser(file,name,email,password,mobile,address,gender);
		 	return new ModelAndView("index");
    }
	
	@GetMapping("/users")
	public ModelAndView  dash2()
	{
		return new ModelAndView("mybookings");
	}
	
	@RequestMapping("/checkusers")
	public ModelAndView  dasd2()
	{
		return new ModelAndView("mybookings");
	}
	
	@RequestMapping("/checkuser")
	public ModelAndView m8(HttpServletRequest request,Model model)
	{
		ModelAndView mv =null;
        String email=request.getParameter("email");
        
		String password=request.getParameter("password");
		
		User u=ub.findByEmail(email);
		
		if(u.equals(null))
		{
			model.addAttribute("msg","invalid username");
			mv= new ModelAndView("index");
		}
		if(u.getEmail().equals(email)) {
			BCryptPasswordEncoder b = new BCryptPasswordEncoder();
			
			if(b.matches(password, u.getPassword()))
			{
				mv= new ModelAndView("mybookings");
				List <Booking> list2 = bb.findByCustomer_id(u.getId());
				 
				 model.addAttribute("list2", list2);
				model.addAttribute("name",u.getName());
				model.addAttribute("email",u.getEmail());
			}
			else {
				model.addAttribute("msg","invalid login");
				mv= new ModelAndView("index");
			}
		}
		else {
			mv= new ModelAndView("index");
		}
		
		return mv;
	}
	
	
	@RequestMapping("/updateuser")
	public ModelAndView m9(@RequestParam String email,Model model)
	{
		ModelAndView mv = new  ModelAndView("update");
		User u = ub.findByEmail(email);
		
		//u.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		
		model.addAttribute("user", u);
		return mv;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public ModelAndView m10(HttpServletRequest request, Model model) {
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		//String password=request.getParameter("password");
		String mobile=request.getParameter("mobile");
		 String address=request.getParameter("address");
				
		 ModelAndView mv= new ModelAndView("mybookings");
			
		 User p = new User();
	    	p = ub.findByEmail(email);
		p.setAddress(address);
		p.setMobile(mobile);
		p.setName(name);
		
		ub.save(p);
		List <Booking> list2 = bb.findByCustomer_id(p.getId());
		 
		 model.addAttribute("list2", list2);
		model.addAttribute("name",p.getName());
		model.addAttribute("email",p.getEmail());
		return mv;// new ModelAndView("success");
	}
	
	@RequestMapping("/book")
	public ModelAndView  mm2(@RequestParam String email,Model model)
	{
		ModelAndView mv=new ModelAndView("newbooking");
		List <Cost> li = cb.findAll();
		model.addAttribute("list", li);
         User u = ub.findByEmail(email);
         model.addAttribute("name",u.getName());
 		model.addAttribute("email",u.getEmail());
 		
		return  mv;
	}
	
	@RequestMapping(value="/makebook", method = RequestMethod.POST)
	public ModelAndView m3(HttpServletRequest request,Model model)
	{
		String email=request.getParameter("email");
		User u = ub.findByEmail(email); 
		int customer_id=u.getId();
		LocalDate booking_date=LocalDate.parse(request.getParameter("booking_date"));
		String start_time =request.getParameter("start_time");
		String car_number=request.getParameter("car_number");
		int car_type=Integer.parseInt(request.getParameter("car_type"));
		float cost=0;
		String status="New";
		LocalDate booked_on=LocalDate.now();
		ModelAndView mv=null;
		List<Booking> test =bb.findByBooking_dateAndStart_time(booking_date,start_time);
		if(test.isEmpty())
	    {
		
		Booking  b= new Booking(customer_id,  booking_date, start_time,  car_number,  car_type, cost,  status,  booked_on);
		
		bb.save(b);
	          mv=new ModelAndView("mybookings");
	          List <Booking> list2 = bb.findByCustomer_id(u.getId());
				 
				 model.addAttribute("list2", list2);
        model.addAttribute("name",u.getName());
		model.addAttribute("email",u.getEmail());
		return  mv;
		
	    }
		else
		{
			mv=new ModelAndView("mybookings");
			List <Booking> list2 = bb.findByCustomer_id(u.getId());
			 
			 model.addAttribute("list2", list2);
			model.addAttribute("name",u.getName());
			model.addAttribute("email",u.getEmail());
			model.addAttribute("msg","Slot not available");
			return mv;
		}
		
		
		
	}
	
	
	@RequestMapping("/booked")
	public ModelAndView  mybook(@RequestParam String email,Model model)
	{
		ModelAndView mv=new ModelAndView("mybookings");
		List <Cost> li = cb.findAll();
		model.addAttribute("list", li);
		 User u = ub.findByEmail(email);
		 
		List <Booking> list2 = bb.findByCustomer_id(u.getId());
		 model.addAttribute("list", li);
		 model.addAttribute("list2", list2);
		 
         model.addAttribute("name",u.getName());
 		model.addAttribute("email",u.getEmail());
 		
		return  mv;
	}
	
	
	@RequestMapping("/adminlogin")
	public ModelAndView log(HttpServletRequest request,Model model)
	{
		ModelAndView mv =null;
        String email=request.getParameter("email");
        
		String password=request.getParameter("password");
		
		
		
		
		if(email.equals("admin@carwash.com") && password.equals("123456")) {
			
			mv= new ModelAndView("admin");
			
			List <Booking> li = bb.findAll();
			model.addAttribute("list2", li);
			
		}
		else {
			model.addAttribute("msg","invalid login");
			mv= new ModelAndView("adminlogin");
		}
		
		return mv;
	}
	@RequestMapping("/admin")
	public ModelAndView  mk()
	{
		return new ModelAndView("adminlogin");
	}
	
	
	@RequestMapping("/updatestatus")
	public ModelAndView statu(@RequestParam String booking_id,Model model)
	{
		int k= Integer.parseInt(booking_id);
		 Booking b = bb.findById(k);
		    b.setStatus("Completed");
		    
		    Cost c = cb.findById(b.getCar_type());
		    
			
		    b.setCost(c.getCost());
		    bb.save(b);
		    ModelAndView mv= new ModelAndView("admin");
			
			List <Booking> li = bb.findAll();
			model.addAttribute("list2", li);
			return mv;
		
	}
	
	
}

