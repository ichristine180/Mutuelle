package com.mutuelle.demo.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mutuelle.demo.Model.Employee;





public interface EmployeeRepository extends CrudRepository<Employee ,Long> {

	@Override
	List<Employee> findAll();
	
	
	Employee findByEmployeeNumber(String employeeNumber);
	

	
	
	

}
