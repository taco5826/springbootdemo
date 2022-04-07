package com.ispan.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

	@GetMapping("/")
	public String welcomeIndex() {
		return "index";
	}
	
	@GetMapping("/message/add")
	public String addMessagePage() {
		return "addMessages";
	}
	
	
	
}
