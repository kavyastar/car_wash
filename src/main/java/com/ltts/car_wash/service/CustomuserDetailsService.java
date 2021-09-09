package com.ltts.car_wash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ltts.car_wash.Customuser;
import com.ltts.car_wash.Bo.UserBo;
import com.ltts.car_wash.model.User;

public class CustomuserDetailsService implements UserDetailsService {

	@Autowired
	private UserBo ub;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=ub.findByEmail(email);
		if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new Customuser(user);
	}

}
