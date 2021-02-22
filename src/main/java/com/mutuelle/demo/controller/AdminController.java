
package com.mutuelle.demo.controller;


import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.mutuelle.demo.Model.Employee;
import com.mutuelle.demo.Model.User;
import com.mutuelle.demo.Model.security.Role;
import com.mutuelle.demo.Model.security.UserRole;
import com.mutuelle.demo.Repo.IEmployeeRepository;
import com.mutuelle.demo.service.EmployeeService;
import com.mutuelle.demo.service.UserService;
import com.mutuelle.demo.utils.IDGenerator;
import com.mutuelle.demo.utils.SearchPatient;

@Controller
public class AdminController {

	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private IEmployeeRepository employeeRepository;
	

	@GetMapping("/")
	public String homepage(Model model) {
		
	     List<Employee> listEmployees = employeeService.listAll();
		 model.addAttribute("listEmployees", listEmployees);
		 long workersize = employeeService.countWorkers();
		model.addAttribute("workersize", workersize);	
	     
		return "homepage";
	}
	@RequestMapping("/new")
	public String showNewEmployeePage(Model model) {
		Employee doc = new Employee();
		Iterable<Role> roles = employeeService.findAll();
		model.addAttribute("emp", doc);
		model.addAttribute("roles", roles);
		boolean doctors = true;
		model.addAttribute("doctors", doctors);
		return "users/newuser";
		
	}
	
	@RequestMapping(value = "/docregistration", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("emp") @Valid Employee user, BindingResult results, Model model) {
		
		System.out.println(user.toString() + " User to be saved");
		if ( user.getEmail() != null) {
		Employee doc = new Employee();
		doc.setFname(user.getFname());
		doc.setLname(user.getLname());
		doc.setEmail(user.getEmail());
		doc.setPhone(user.getPhone());
		doc.setEmployeeNumber(IDGenerator.generateEmployeeNumber(doc));
		doc.setUser(user.getUser());
		if (employeeService.checkUsernameExists(user.getEmail())) {
			if (employeeService.checkUsernameExists(user.getEmail())) {
		  Iterable<Role> roles = employeeService.findAll();
			model.addAttribute("roles",roles);
			model.addAttribute("doctors", true);
			System.out.println("Errors Found" + results.getAllErrors().toString());
			model.addAttribute("message", "There is user with this Email ");
			System.out.println("email exists");
			return "users/newuser";
			}}
		
		
		User myuser = new User();
		myuser.setUsername(user.getEmail());
		
		myuser.setPassword("pass");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(myuser, employeeService.findByName(user.getUser().getUsername())));
		myuser.setDoctor(doc);
		doc.setUser(myuser);
		
		employeeService.createUser(myuser);
		User empoUser = employeeService.createUser(myuser,userRoles);
		if (empoUser != null) {
			model.addAttribute("user", employeeService.findByUsername(empoUser.getUsername()));
			return "redirect:/";
		}
	}
	return "redirect:/";
}
	
	
	@GetMapping("/details/employee/{employeeNumber}")
	public String showInformation(Model model, @PathVariable String employeeNumber, Principal principal) {
		if (employeeNumber != null) {
			Employee result = employeeService.findEmployeeByEmployeeNumber(employeeNumber);
			// if the employee is found, we proceed with admission
			if (result != null) {
				boolean employeeInfo = true;
				model.addAttribute("employeeInfo", employeeInfo);
				model.addAttribute("emp", result);
				return "users/userinfo";
			}
		}

		return "redirect:/users/newuser";

	}
}
	
	

	
