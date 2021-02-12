package com.mutuelle.demo.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mutuelle.demo.Model.MedicalService;
import com.mutuelle.demo.Model.Patient;
import com.mutuelle.demo.service.IMedicalServiceService;
import com.mutuelle.demo.service.IPatientService;
import com.mutuelle.demo.utils.EMedicalServiceType;
import com.mutuelle.demo.utils.SearchPatient;


@Controller
public class OfficerController {
	@Autowired
	private IPatientService PatientService;
	@Autowired
	private IMedicalServiceService mService;
	
	@PostMapping("/search")
	public String searchPatient( @ModelAttribute("patient") @Valid SearchPatient patient,BindingResult results,Model model) {
		if(results.hasErrors()) {
			System.out.println("Validation Errors occured");
			return "healthOfficerpage";
		}
		System.out.println(patient.getIdnb());
			Patient res = PatientService.findPatientByIdnbr(patient.getIdnb());
			List<MedicalService> exams  = mService.
					findMedicaments(EMedicalServiceType.EXAM);
			List<MedicalService> medicaments  = mService.
					findMedicaments(EMedicalServiceType.MEDICAMENT);
			// if the patient is found, we proceed
			if (res != null) {
				boolean patientidnb = true;
				
				model.addAttribute("patient",res);
				
				model.addAttribute("patientidnb",patientidnb);
				model.addAttribute("exams",exams);
				model.addAttribute("medicaments",medicaments);
				return "patientDetails";

			}
			model.addAttribute("errormessage","identification details are not found.");
			return "healthOfficerpage";
	}
}
