package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloWorldController {

	// need a controller method to show intial HTML form
//	@RequestMapping("/showForm")
//	public String showForm()
//	{
//		return "helloworld-form";
//	}
	@GetMapping("/showForm")
	public String showForm()
	{
		return "helloworld-form";
	}
	
	// need a  controller method to process the HTML form
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}
	
	@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model)
	{
		
		String theName = request.getParameter("studentName");
		theName= theName.toUpperCase();
		
		String result = "Hey" + theName;
		
		model.addAttribute("message", result);
		
		
		return "helloworld";
	}
	@RequestMapping("/processFormVersionThree")
	public String processFormVersionThree(@RequestParam("studentName") String theName, Model model)
	{
		
		
		theName= theName.toUpperCase();
		
		String result = "Merhaba arkadaşım" + theName;
		
		model.addAttribute("message", result);
		
		
		return "helloworld";
	}
}
