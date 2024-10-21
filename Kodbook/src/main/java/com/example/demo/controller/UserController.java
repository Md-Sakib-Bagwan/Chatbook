package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.services.PostServices;
import com.example.demo.services.Userservices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UserController {
	
	@Autowired
	Userservices us;
	
	@Autowired
	PostServices ps;
	
	@PostMapping("/createaccount")
	public String addUser(@Valid @ModelAttribute User user,BindingResult result) {
		
		if(result.hasErrors()) {
			return "signup";
		}
		
		boolean status=us.userExist(user.getUsername(),user.getEmail());
		
		if(status==false) {
			us.addUser(user);
		}
		return "redirect:/";
		
	}
	
	@PostMapping("/login")
	public String validUser(@RequestParam String username,@RequestParam String password,Model model,HttpSession session) {
		
		boolean status=us.validate(username,password);
		if(us.userExist(username, password)==false) {
			return "redirect:/";
		}
		else {
			if(status==true) {
				session.setAttribute("username",username);
				model.addAttribute("session", session);
				List<Post> allPosts = ps.getAllPost();
				model.addAttribute("allPosts", allPosts);
				return "redirect:/goHome";
			}
			else {
				return "redirect:/";
			}
		}	
		
	}
	
	@PostMapping("/changepassword")
	public String changePassword(@RequestParam String username,@RequestParam String password,@RequestParam String newpassword) {
		
		User u=us.findUser(username);
		if(password.equals(newpassword)) {
		u.setPassword(newpassword);
		us.updateUser(u);
		}
		else {
			System.out.println("Please enter same password");
		}
		return "redirect:/";
	}
	
	@PostMapping("/updateprofile")
	public String updateProfile( @RequestParam String dob,@RequestParam String gender,@RequestParam String city,@RequestParam String bio,@RequestParam String college,@RequestParam String linkedin,@RequestParam String github,@RequestParam("profilephoto") MultipartFile profilephoto,HttpSession session) {
		
		String username=(String) session.getAttribute("username");
		User user=us.findUser(username);
		user.setDob(dob);
		user.setGender(gender);
		user.setCity(city);
		user.setBio(bio);
		user.setCollege(college);
		user.setLinkedin(linkedin);
		user.setGithub(github);
		
		try {						
			user.setProfilephoto(profilephoto.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		us.updateUser(user);
		
		return "redirect:/myprofile";
	}
	
	@GetMapping("/deleteuser")
	public String deleteUser(HttpSession session) {
		String username=(String) session.getAttribute("username");
		User user=us.findUser(username);
		//List<Post>pl=user.getPosts();
		
	/*	if(pl.isEmpty()) {
			us.deleteUser(user);
		}
		else {
			pl.forEach(p->pl.remove(p));  //lambda function in java
			user.setPosts(pl);
			us.updateUser(user);
			
			us.deleteUser(user);
		} */
		us.deleteUser(user);
		
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
}
