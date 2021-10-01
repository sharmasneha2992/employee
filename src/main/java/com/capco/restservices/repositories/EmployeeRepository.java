package com.capco.restservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capco.restservices.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//	@Query("select e from Employee e where (:firstName is null or e.firstName = :firstName)"
//			+ " and (:lastName is null or e.lastName = :lastName)")
//	List<Employee> searchByFirstAndOrLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
	
	List<Employee> findByFirstnameAndLastname(String firstname,String lastname);
}
