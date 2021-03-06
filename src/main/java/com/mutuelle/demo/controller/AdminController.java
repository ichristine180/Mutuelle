package com.mutuelle.demo.controller;

import java.util.HashSet;


import java.util.List;
import java.util.Set;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.mutuelle.demo.model.HealthFacility;
import com.mutuelle.demo.model.security.Role;
import com.mutuelle.demo.model.security.UserRole;
import com.mutuelle.demo.model.security.User;
import com.mutuelle.demo.service.IUserService;
import com.mutuelle.demo.service.IhealthFacility;

@Controller
public class AdminController {

	private final Logger LOG = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private IhealthFacility healthService;
	@Autowired
	private IUserService userService;

	@GetMapping(value = "/addHealthFacility")
	public String addHealthFacility(Model model) {

		HealthFacility healthFacility = new HealthFacility();
		model.addAttribute("healthFacility", healthFacility);
		return "admin/addHealthFacicility";
	}

	@PostMapping(value = "/addfacility")
	public String addfacility(@ModelAttribute("healthFacility") @Valid HealthFacility hf, final BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			System.out.println("validation error");
			return "admin/addHealthFacicility";
		}
		// System.out.println(hf);
		HealthFacility exit = healthService.findByName(hf.getFacilityName());
		if (exit == null) {
			HealthFacility newHF = new HealthFacility();
			newHF.setAdrress(hf.getAdrress());
			newHF.setFacilityName(hf.getFacilityName());
			HealthFacility createdHf = healthService.create(newHF);
			if (createdHf != null) {
				model.addAttribute("successMessage", "Health Center added Successfully");
				model.addAttribute("healthFacility", healthService.findAllService());
				return "admin/healthFacility";
			}

		} else {
			model.addAttribute("message", "Health Center with provided name exit");
			return "admin/addHealthFacicility";
		}
		return "admin/addHealthFacicility";

	}

	@GetMapping(value = "/healthFacilities")
	public String healthFacilities(Model model) {
		final List<HealthFacility> healthFacility = healthService.findAllService();
		LOG.info("{}", healthFacility);
		model.addAttribute("healthFacility", healthFacility);
		return "admin/healthFacility";
	}

	@GetMapping(value = "/user")
	public String addUser(Model model) {
		User user = new User();
		Iterable<Role> roles = userService.findAllRole();
		Iterable<HealthFacility> healthFacility = healthService.findAllService();
		model.addAttribute("healthFacility", healthFacility);
		model.addAttribute("roles", roles);
		model.addAttribute("user", user);
		return "admin/user";
	}

	@PostMapping("/addUser")
	public String addUser(@ModelAttribute("user") @Valid User user, BindingResult results, Model model) {
		if (results.hasErrors()) {
			Iterable<Role> roles = userService.findAllRole();
			Iterable<HealthFacility> healthFacility = healthService.findAllService();
			model.addAttribute("healthFacility", healthFacility);
			model.addAttribute("roles", roles);
			System.out.println("Errors Found" + results.getAllErrors().toString());
			return "admin/user";
		}
		if (userService.checkUsernameExists(user.getEmail())) {
			Iterable<Role> roles = userService.findAllRole();
			Iterable<HealthFacility> healthFacility = healthService.findAllService();
			model.addAttribute("healthFacility", healthFacility);
			model.addAttribute("roles", roles);
			model.addAttribute("message", "There is user with this Email ");
			System.out.println("email exists");
			return "admin/user";
		}

		User myuser = new User();
		HealthFacility hf;
		if (user.getUsername().equals("ROLE_ADMIN")) {
			hf = healthService.findByName("MiniSante");
		} else {
			hf = healthService.findOne(user.getHealthFacility().getId());
		}
		// System.out.print(user.getUsername());
		myuser.setfName(user.getfName());
		myuser.setPassword("pass");
		myuser.setEmail(user.getEmail());
		myuser.setlName(user.getlName());
		myuser.setUsername(user.getEmail());
		if (hf != null) {
			myuser.setHealthFacility(hf);
		}
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(myuser, userService.findByName(user.getUsername())));
		final User createdUser = userService.createUser(myuser, userRoles);
		if (createdUser != null) {
			model.addAttribute("successMessage", "User added Successfully");
			model.addAttribute("users", userService.findUserList());
			return "adminpage";
		}
		return "redirect:/admin";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		User user = userService.findById(id);
		userService.delete(user);
		model.addAttribute("successMessage", "User deleted Successfully");
		model.addAttribute("users", userService.findUserList());
	    return "redirect:/admin";
	}
}
