package com.luv2code.springdemo.mvc;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;

import jakarta.validation.Valid;



@Controller
public class CustomerController {
	
	// add an initbinder ... to cocnvert trim input strings
	// remove leading and trailing whitespace
	// resolve issue for our validation
	
	@InitBinder // pre process olarak whitespaceleri silmektedir
	public void initBinder(WebDataBinder dataBinder)
	{
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	

	@GetMapping("/")
	public String showForm(Model theModel)
	{
		theModel.addAttribute("customer", new Customer());
		
		return "customer-form";
	}
	
	@PostMapping("/processForm")
	public String processForm(
			@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) // binding result validationun sonucunu  tutmaktadır. 
	{
		if(theBindingResult.hasErrors()) {
			return "customer-form";
		}
		else 
		{
			return "customer-confirmation";
		}
	}
	
	
}
