
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
public class UserController {

	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private IEmployeeRepository employeeRepository;
	

	@GetMapping("/admin")
	public String homepage(Model model) {
	     List<Employee> listEmployees = employeeService.listAll();
		 model.addAttribute("listEmployees", listEmployees);
		 long workersize = employeeService.countWorkers();
		model.addAttribute("workersize", workersize);	 
		return "homepage";
	
	}
	

	@GetMapping("/rssb")
	public String Receptionist(Model model, Principal principal) {
		
		
		
		return "rssbpage";
	}

	@GetMapping("/healthcenter")
	public String gDoctor(Model model, Principal principal) {
		SearchPatient patient = new SearchPatient();
		model.addAttribute("patient", patient);
		return "healthOfficerpage";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
