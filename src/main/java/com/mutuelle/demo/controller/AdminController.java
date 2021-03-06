package com.mutuelle.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.mutuelle.demo.model.HealthFacility;
import com.mutuelle.demo.service.IhealthFacility;

@Controller
public class AdminController {
	
	private final Logger LOG = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private IhealthFacility healthService;

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
		HealthFacility newHF = new HealthFacility();
		newHF.setAdrress(hf.getAdrress());
		newHF.setFacilityName(hf.getFacilityName());
		HealthFacility createdHf = healthService.create(newHF);
		if (createdHf != null) {
			model.addAttribute("successMessage", "Health Center added Successfully");
			model.addAttribute("message", true);
			return "redirect:/healthFacilities";
		} else
			return "admin/addHealthFacicility";

	}

	@GetMapping(value = "/healthFacilities")
	public String healthFacilities(Model model) {
		final List<HealthFacility> healthFacility = healthService.findAllService();
		LOG.info("{}", healthFacility);
		model.addAttribute("healthFacility", healthFacility);
		model.addAttribute("message", false);
		return "admin/healthFacility";
	}

}
