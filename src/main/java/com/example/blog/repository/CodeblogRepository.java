package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.Post;

public interface CodeblogRepository extends JpaRepository<Post, Long> {

}
