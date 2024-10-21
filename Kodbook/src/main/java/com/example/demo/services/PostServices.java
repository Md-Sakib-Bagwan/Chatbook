package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Post;

public interface PostServices {

	void createPost(Post post);
	
	public List<Post> getAllPost();

	Post getPost(Long id);

	void updatePost(Post p);

	void deleteAllPost(List<Post> pl);

	void deletePost(Post post);

}