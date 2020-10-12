package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Model.Patient;
import com.example.demo.service.IMedicalTestService;
import com.example.demo.service.IPatientService;
import com.example.demo.service.ITreatmentService;


@Controller
public class OfficerController {
	@Autowired
	private IPatientService PatientService;
	@Autowired
	private ITreatmentService tService;
	@Autowired
	private IMedicalTestService mTestService;

	@PostMapping("/search")
	public String searchPatient(@ModelAttribute @Valid Patient patient,BindingResult results,Model model) {
		if(results.hasErrors()) {
			System.out.println("Validation Errors occured");
			return "healthOfficerpage";
		}
			Patient res = PatientService.findPatientByidnb(patient.getIdnb());
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
