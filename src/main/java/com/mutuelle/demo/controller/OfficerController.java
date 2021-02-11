package com.mutuelle.demo.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mutuelle.demo.Model.Patient;
import com.mutuelle.demo.service.IPatientService;
import com.mutuelle.demo.utils.SearchPatient;


@Controller
public class OfficerController {
	@Autowired
	private IPatientService PatientService;
	
	@PostMapping("/search")
	public String searchPatient( @ModelAttribute("patient") @Valid SearchPatient patient,BindingResult results,Model model) {
		if(results.hasErrors()) {
			System.out.println("Validation Errors occured");
			return "healthOfficerpage";
		}
		System.out.println(patient.getIdnb());
			Patient res = PatientService.findPatientByIdnbr(patient.getIdnb());
			// if the patient is found, we proceed
			if (res != null) {
				boolean patientidnb = true;
				
				model.addAttribute("patient",res);
				
				model.addAttribute("patientidnb",patientidnb);
				return "patientDetails";

			}
			model.addAttribute("errormessage","identification details are not found.");
			return "healthOfficerpage";
	}
}
