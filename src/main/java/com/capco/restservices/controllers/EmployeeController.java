package com.capco.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capco.restservices.services.EmployeeService;
import com.capco.restservices.entities.Employee;
import com.capco.restservices.exceptions.UserNotFoundException;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getAllUsers() {

		return employeeService.getAllEmployees();

	}
	
	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable("id") Long id) {

		try {
			return employeeService.getEmployeeById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

	@PutMapping("/employees/{id}")
	public Employee updateUserById(@PathVariable("id") Long id, @RequestBody Employee employee) {

		try {
			return employeeService.updateEmployeeById(id, employee);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

	}
	
	@GetMapping("/employees/byname/{firstname}/{lastname}")
	public List<Employee> getByFirstAndOrLastName(@PathVariable("firstname") String firstname,@PathVariable("lastname") String lastname) {
		return employeeService.searchByFirstAndOrLastName(firstname,lastname);
	}
}
