package com.mutuelle.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.Model.Employee;






@Repository
public interface IEmployeeRepository extends JpaRepository<Employee ,Long>{

	List<Employee> findAll();

	

	Employee findByEmployeeNumber(String employeeNumber);


	Employee findByEmployeeId(Long id);
	
	

	
	
	

	

	
	

}
