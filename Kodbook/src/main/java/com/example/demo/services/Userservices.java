package com.example.demo.services;

import com.example.demo.entity.User;

public interface Userservices {

	public void addUser(User user);

	public boolean userExist(String username, String email);

	public boolean validate(String username, String password);

	public User findUser(String username);

	public void updateUser(User u);

	public User findUserById(int id);

	public void deleteUser(User user);

}
