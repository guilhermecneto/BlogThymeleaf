package com.example.blog.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.model.Post;
import com.example.blog.repository.CodeblogRepository;
import com.example.blog.service.BlogService;

@Service
public class ServiceImpl implements BlogService {

	@Autowired
	CodeblogRepository codeblogRepository;
	
	@Override
	public List<Post> findAll() {
		return codeblogRepository.findAll();
	}

	@Override
	public Post findById(long id) {
		return codeblogRepository.findById(id).get();
	}

	@Override
	public Post save(Post post) {
		return codeblogRepository.save(post);
	}

}
