package com.example.blog.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.blog.model.Post;
import com.example.blog.service.BlogService;

@Controller
public class CodeblogController {

	@Autowired
	BlogService blogService;
	
	@RequestMapping(value="/posts", method = RequestMethod.GET)
	public ModelAndView getPosts() {
		ModelAndView mv = new ModelAndView("posts");
		List<Post> posts = blogService.findAll();
		mv.addObject("posts", posts);
		return mv;
	}
	
	@RequestMapping(value="/posts/{id}", method = RequestMethod.GET)
	public ModelAndView getPostDetails(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("postsDetails");
		Post post = blogService.findById(id);
		mv.addObject("post", post);
		return mv;
	}
	@RequestMapping(value="", method = RequestMethod.GET)
	public String getHome() {
		return "index";
	}
	@RequestMapping(value="index", method = RequestMethod.GET)
	public String ReturnHome() {
		return "index";
	}
	
	@RequestMapping(value="/newpost", method = RequestMethod.GET)
	public String getPostForm() {
		return "postForm";
	}
	
	@RequestMapping(value="/newpost", method = RequestMethod.POST)
	public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/newpost";
		}
		
		post.setData(LocalDate.now());
		blogService.save(post);
		return "redirect:/posts";
		
	}
	
	
}
