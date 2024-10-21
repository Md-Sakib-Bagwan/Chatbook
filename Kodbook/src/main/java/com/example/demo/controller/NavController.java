package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.services.PostServices;
import com.example.demo.services.Userservices;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NavController {
	
	@Autowired
	PostServices ps;
	
	@Autowired
	Userservices us;

	@GetMapping("/signup")
	public String nav(Model model){
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@GetMapping("/forget")
	public String forget(){
		return "forget";
	}
	
	@GetMapping("/post")
	public String post( HttpSession session,Model model){
		if(session.getAttribute("username")!=null) {
			String username=(String) session.getAttribute("username");
			User user=us.findUser(username);
			model.addAttribute("user", user);
			return "createpost";
		}
		return "redirect:/";
	}
	
	@GetMapping("/goHome")
	public String login(Model model,HttpSession session)	{
		
		    String username=(String) session.getAttribute("username");
		    User user=us.findUser(username);
		    model.addAttribute("user", user);
		   
			List<Post> allPosts = ps.getAllPost();
			model.addAttribute("allPosts", allPosts);
			return "home";
	}
	
	@GetMapping("/myprofile")
	public String profile(HttpSession session,Model model){
		String username=(String) session.getAttribute("username");
		User user=us.findUser(username);
		model.addAttribute("user",user);
		return "myprofile";
	}
	
	@GetMapping("/editprofile")
	public String editProfile(HttpSession session,Model model){
		if(session.getAttribute("username")!=null) {
			String username=(String) session.getAttribute("username");
			User user=us.findUser(username);
			model.addAttribute("user", user);
		return "editprofile";
		}
		else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/profile/{id}")
	public String getProfile(@PathVariable("id") int id,Model model) {
		User user=us.findUserById(id);
		model.addAttribute("user",user); 
		return "profile";
	}
	
}
