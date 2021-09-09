package com.ltts.car_wash.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ltts.car_wash.Bo.UserBo;
import com.ltts.car_wash.model.User;

@Service
public class UserService {
	@Autowired 
	UserBo ub;

	
	public void saveUser(MultipartFile file, String name, String email, String password,String mobile,String address,String gender)
	{
		User u =new User();
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		u.setAddress(address);
		u.setEmail(email);
		u.setMobile(mobile);
		u.setName(name);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(password);
	    u.setPassword(encodedPassword);
		
		try {
			u.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		ub.save(u);
	}
}
