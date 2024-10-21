package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);
	public User findByEmail(String email);
	
}
