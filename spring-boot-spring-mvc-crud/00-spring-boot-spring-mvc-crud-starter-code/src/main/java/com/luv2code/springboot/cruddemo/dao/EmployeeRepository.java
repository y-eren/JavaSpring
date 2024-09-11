package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!
	
	// add a method to sort by last name
	public List<Employee> findAllByOrderByLastNameAsc(); // buna query methods dneiyor önceden yaptığımız querylerde from Employee order by lastName asc şeklinde olmaktaydı artık bu şekilde olmamaktadır. 
	// bunlara jpa query methods denmektedir. 

}
