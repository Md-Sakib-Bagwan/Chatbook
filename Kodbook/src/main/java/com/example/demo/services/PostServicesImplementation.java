package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;

@Service
public class PostServicesImplementation
		implements PostServices{

	@Autowired
	PostRepository pr;

	@Override
	public void createPost(Post post) {
		pr.save(post);
	}

	@Override
	public List<Post> getAllPost() {
		return pr.findAll();
	}

	@Override
	public Post getPost(Long id) {
		
		return pr.findById(id).get();
	}

	@Override
	public void updatePost(Post p) {
		pr.save(p);
		
	}

	@Override
	public void deleteAllPost(List<Post> pl) {
		pr.deleteAll(pl);
		
	}

	@Override
	public void deletePost(Post post) {
		pr.delete(post);
		
	}
}
