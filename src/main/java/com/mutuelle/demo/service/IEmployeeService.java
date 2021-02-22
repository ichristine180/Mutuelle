package com.mutuelle.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mutuelle.demo.Model.Employee;
import com.mutuelle.demo.Model.User;
import com.mutuelle.demo.Model.security.Role;
import com.mutuelle.demo.Model.security.UserRole;



public interface IEmployeeService {
	
	String NAME = "employeeService";
	
	long countWorkers();


	

	
	boolean checkUsernameExists(String username);
	boolean checkUserExists(String username);

	User createUser(User user);
	User createUser(User user, Set<UserRole> userRoles);

	User encryptPass(User password);


	
	User saveUser(User user);

	List<User> findUserList();

	Role findByName(String rolename);
	User findByUsername(String username);

	void save(User user);




	List<Employee> findAllEmployees();
	Employee createEmployee(Employee employee);





	Employee findEmployeeByEmployeeNumber(String EmployeeNumber);



	Employee findByEmployeeId(Long id);

	

	



}
