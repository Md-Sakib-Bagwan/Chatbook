package com.example.demo.controller;



import java.io.IOException;
import java.net.http.HttpClient.Redirect;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.services.PostServices;
import com.example.demo.services.Userservices;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class PostController {
	@Autowired
	PostServices ps;
	
	@Autowired
	Userservices us;
	
	@PostMapping("/createPost")
	public String createPost(@RequestParam ("caption") String caption,
            @RequestParam("photo") MultipartFile photo,HttpSession session	) {
		
		String username=(String) session.getAttribute("username");
			
		User user=us.findUser(username);
		
		Post post = new Post();
		post.setCaption(caption);
		try {						
			post.setPhoto(photo.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		post.setUser(user);
		ps.createPost(post);
		
		List<Post> pl=user.getPosts();
		if(pl==null) {
			pl=new ArrayList<Post>();
		}
		pl.add(post);
		user.setPosts(pl);
		us.updateUser(user);
		
		return "redirect:/goHome";

	}
	
	@PostMapping("/likePost")
	public String likePost(@RequestParam Long id,Model model) {
		Post p=ps.getPost(id);
		p.setLikes(p.getLikes()+1);
		ps.updatePost(p);
		
	/*	List<Post> allPosts = ps.getAllPost();
		model.addAttribute("allPosts", allPosts);  */
		return "redirect:/goHome";
	}
	
	@PostMapping("/addComment")
	public String addComment(@RequestParam Long id,@RequestParam String comments,Model model) {
		Post p=ps.getPost(id);
		List<String> cl=p.getComments();
		
		if(cl==null) {
			cl=new ArrayList<String>();
		}
		
		cl.add(comments);
		p.setComments(cl);
		ps.updatePost(p);
		
		return "redirect:/goHome";
	}
	
	@GetMapping("/allposts/{id}")
	public String getAllPosts(@PathVariable (value = "id") int id, Model model) {
		User user=us.findUserById(id);
		
		model.addAttribute("user",user);
		
		return "allpost";
	}
	
	@GetMapping("/editpost/{id}")
	public String editPost(@PathVariable (value = "id") Long id, Model model,HttpSession session) {
		String username=(String)session.getAttribute("username");
		model.addAttribute("user", us.findUser(username));
		
		Post post=ps.getPost(id);
		model.addAttribute("post", post);
		return "editpost";
	}
	
	@PostMapping("/updatepost")
	public String updatePost(@RequestParam String caption,@RequestParam Long id) {
		Post post=ps.getPost(id);
		post.setCaption(caption);
		ps.updatePost(post);
		
		return "redirect:/myprofile";
	}
	
	@GetMapping("/deletepost/{id}")
	public String deletePost(@PathVariable (value = "id") Long id) {
		Post post=ps.getPost(id);
		
		User user=us.findUserById(post.getUser().getId());
		List<Post> pl=user.getPosts();
		pl.remove(post);
		user.setPosts(pl);
		us.updateUser(user);
		
		ps.deletePost(post);
		
		return "redirect:/myprofile";
	}
	
	
}