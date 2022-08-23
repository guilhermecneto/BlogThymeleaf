package com.example.blog.service;

import java.util.List;

import com.example.blog.model.Post;

public interface BlogService {
	
	List<Post> findAll();
	Post findById(long id);
	Post save(Post post);
	

}
