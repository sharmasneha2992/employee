package com.capco.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capco.restservices.repositories.EmployeeRepository;
import com.capco.restservices.entities.Employee;
import com.capco.restservices.exceptions.UserNotFoundException;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	// getAllUsers Method
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();

	}

	public Employee updateEmployeeById(Long id, Employee employee) throws UserNotFoundException {
		Optional<Employee> optionalEmp = employeeRepository.findById(id);

		if (!optionalEmp.isPresent()) {
			throw new UserNotFoundException("Employee Not found in Emp Repository, provide the correct user id");
		}

		employee.setId(id);
		return employeeRepository.save(employee);

	}

	public Optional<Employee> getEmployeeById(Long id) throws UserNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);

		if (!employee.isPresent()) {
			throw new UserNotFoundException("Emp Not found in Emp Repository");
		}

		return employee;
	}
	

	public List<Employee> searchByFirstAndOrLastName(String firstName,String lastname) {
		return employeeRepository.findByFirstnameAndLastname(firstName,lastname);
	}

}
