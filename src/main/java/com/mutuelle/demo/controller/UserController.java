
package com.mutuelle.demo.controller;


import java.security.Principal;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mutuelle.demo.Model.Patient;

@Controller
public class UserController {

	/*@Autowired
	private UserService userService;
*/
	

	@GetMapping("/admin")
	public String homepage(Model model) {

		return "adminpage";
	}

	@GetMapping("/rssb")
	public String Receptionist(Model model, Principal principal) {
		
		return "rssbpage";
	}

	@GetMapping("/healthcenter")
	public String gDoctor(Model model, Principal principal) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "healthOfficerpage";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
