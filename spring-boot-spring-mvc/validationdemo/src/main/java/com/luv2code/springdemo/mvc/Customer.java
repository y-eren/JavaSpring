package com.luv2code.springdemo.mvc;

import com.luv2code.springdemo.mvc.validation.CourseCode;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Customer {

	private String firstName;
	
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String lastName;
	
	
	@Min(value=0, message="must be greater than or equal to zero")
	@Max(value=10, message="must be less than or equal to 10")
	@NotNull(message= "bu alan gereklidir")
	private Integer freepasses; // eklenen type conversiondan dolayı wrapper classı eklememiz gerekmektedir.
	
	
	@Pattern(regexp = "^[a-zA-Z0-9]{5}", message="only 5 chars/digits")
	private String postalCode;
	@CourseCode(value = "ysf", message="ysf ile başlamalıdır")
	private String courseCode;
	
	
	
	
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	public Integer getFreepasses() {
		return freepasses;
	}
	public void setFreepasses(Integer freepasses) {
		this.freepasses = freepasses;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
