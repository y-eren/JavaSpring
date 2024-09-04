package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
 @RequestMapping("/api")
public class EmployeeRestController {
	// normalde quick ve diry olarak doğrudan employee dao'ya bağlanıyorduk ancak şimdi service üzerinden bağlanacağız.
	// private EmployeeDAO employeeDAO;
	private EmployeeService employeeService;
	// inject employee dao constructor injection
//	public EmployeeRestController(EmployeeDAO theEmployeeDAO)
//	{
//		employeeDAO = theEmployeeDAO;
//	}
	
//	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService)
	{
		employeeService = theEmployeeService;
	}
	// expose /employee and return a list of employee
	@GetMapping("/employees")
	public List<Employee> findAll()
	{
		// return employeeDAO.findAll();
		return employeeService.findAll();
	}
	
	// add mapping for GET /employees/{employeeId}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployees(@PathVariable int employeeId)
	{
		Employee theEmployee = employeeService.findById(employeeId);
		if(theEmployee == null)
		{
			throw new RuntimeException("Employee id not found" + employeeId);
		}
		return theEmployee;
		
	}
	
	
	// add mapping for post employees - add new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee)
	{
		// eger id set = 0 ise save new item degilse update
		theEmployee.setId(0);
		Employee dbEmployee = employeeService.save(theEmployee);
		return dbEmployee;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee)
	{
		Employee dbEmployee = employeeService.save(theEmployee);
		return dbEmployee;
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId)
	{
		Employee tempEmployee = employeeService.findById(employeeId);
		if(tempEmployee == null)
		{
			throw new RuntimeException("Employee id not found"+ employeeId);
		}
		employeeService.deleteById(employeeId);
		return "Deleted employee id - " + employeeId;
		
	}
	
}
