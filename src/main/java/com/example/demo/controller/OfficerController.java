package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfficerController {
	
	@PostMapping("/search")
	public String searchPatient() {
		return "patientDetails";
	}
	

}
