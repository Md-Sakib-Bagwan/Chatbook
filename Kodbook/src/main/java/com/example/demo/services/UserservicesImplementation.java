package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserservicesImplementation implements Userservices{

	@Autowired
	UserRepository ur;

	public void addUser(User user) {
		ur.save(user);

	}

	public boolean userExist(String username, String email) {

		if(ur.findByUsername(username)==null && ur.findByEmail(email)==null) {

			return false;
		}
		return true;
	}

	@Override
	public boolean validate(String username, String password) {
		User u=ur.findByUsername(username);
		if(u!=null) {
			if(u.getPassword().equals(password)) {
				return true;
			}
		}
		else {
			System.out.println("User not found");
		}
		return false;
	}

	@Override
	public User findUser(String username) {

		return ur.findByUsername(username);
	}

	@Override
	public void updateUser(User u) {
		ur.save(u);

	}

	@Override
	public User findUserById(int id) {
		Optional<User> optional=ur.findById(id);
		User user=null;

		if(optional.isPresent()) {
			user=optional.get();
		}
		else {
			System.out.println("User not found");
		}
		return user;
	}

	@Override
	public void deleteUser(User user) {
		ur.delete(user);
		
	}
}
