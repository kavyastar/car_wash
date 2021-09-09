package com.ltts.car_wash.Bo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ltts.car_wash.model.User;

@Repository
public interface UserBo extends JpaRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

	

}
