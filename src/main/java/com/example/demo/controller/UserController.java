
package com.example.demo.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.Model.security.Erole;
import com.example.demo.Model.security.Role;
import com.example.demo.Model.security.Users;
import com.example.demo.Repo.RoleRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.UserRegistration;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index() {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUser(Model model) {
		UserRegistration user = new UserRegistration();
		Iterable<Role> roles = roleRepository.findAll();
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		return "register/registration";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") @Valid UserRegistration user, Model model) {
		System.out.println(user.getRoleName() + " Please");
		Users toSave = new Users();
		toSave.setEmail(user.getEmail());
		toSave.setfName(user.getFirstName());
		toSave.setlName(user.getLastName());
		toSave.setPassword(user.getPassword());
		toSave.setUserName(user.getEmail());
		if (userService.checkUserExists(user.getUsername(), user.getEmail())) {
			if (userService.checkEmailExists(user.getEmail())) {
				model.addAttribute("emailExists", true);
				System.out.println("EMail exists");
			}
			if (userService.checkUsernameExists(user.getUsername())) {
				model.addAttribute("usernameExists", true);
				System.out.println("Username exists");
			}
			return "register/registration";
		}
		// Set the default password for now
		toSave.setPassword("mutuelle@123");
		Set<Erole> eroles = new HashSet<>();

		userService.createUser(toSave, eroles);
		return "redirect:/userFront";
	}

	@RequestMapping("/userFront")
	public String userFront(Principal principal, Model model) {
		Users user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		return "home/index";
	}

	@GetMapping("/403")
	public String error403() {
		return "home/403";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Principal principal, Model model) {
		Users user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		return "profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profilePost(@ModelAttribute("user") Users newUser, Model model) {
		Users user = userService.findByUsername(newUser.getUserName());
		user.setUserName(newUser.getUserName());
		user.setfName(newUser.getfName());
		user.setlName(newUser.getlName());
		user.setEmail(newUser.getEmail());
		model.addAttribute("user", user);
		userService.saveUser(user);
		return "profile";
	}
}