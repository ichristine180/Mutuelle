package com.mutuelle.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.mutuelle.demo.Model.MedicalAct;
import com.mutuelle.demo.Model.MedicalService;
import com.mutuelle.demo.Model.Patient;
import com.mutuelle.demo.service.IMedicalActService;
import com.mutuelle.demo.service.IMedicalServiceService;
import com.mutuelle.demo.service.IPatientService;
import com.mutuelle.demo.utils.EMedicalServiceType;
import com.mutuelle.demo.utils.Exam;
import com.mutuelle.demo.utils.ExamData;
import com.mutuelle.demo.utils.MedicamentData;
import com.mutuelle.demo.utils.SearchPatient;

@Controller
public class OfficerController {
	@Autowired
	private IPatientService PatientService;
	@Autowired
	private IMedicalServiceService mService;
	@Autowired
	private IMedicalActService mActs;

	@PostMapping("/search")
	public String searchPatient(@ModelAttribute("patient") @Valid SearchPatient patient, BindingResult results,
			Model model) {
		if (results.hasErrors()) {
			System.out.println("Validation Errors occured");
			return "healthOfficerpage";
		}
		
		Patient res = PatientService.findPatientByIdnbr(patient.getIdnb());
		List<MedicalService> exams = mService.findMedicaments(EMedicalServiceType.EXAM);
		List<MedicalAct> examActs = mActs.findOneDateActByPatient(res, LocalDate.now());
		List<MedicalAct> mActs = examActs.stream().filter(acts -> acts.getService().getType()
				.equals(EMedicalServiceType.MEDICAMENT)).collect(Collectors.toList());
		List<MedicalAct> eActs = examActs.stream().filter(acts -> acts.getService().getType()
				.equals(EMedicalServiceType.EXAM)).collect(Collectors.toList());
		
//		System.out.println(examActs);
		List<MedicalService> medicaments = mService.findMedicaments(EMedicalServiceType.MEDICAMENT);
		// if the patient is found, we proceed
		if (res != null) {
			boolean patientidnb = true;

			model.addAttribute("patient", res);
			MedicamentData medicamentData = new MedicamentData();
			ExamData examData = new ExamData();
			model.addAttribute("patientidnb", patientidnb);
			model.addAttribute("mActs", mActs);
			model.addAttribute("examActs", eActs);
			model.addAttribute("exams", exams);
			model.addAttribute("medicaments", medicaments);
			model.addAttribute("medicamentData", medicamentData);
			model.addAttribute("examData", examData);
			return "patientDetails";

		}
		model.addAttribute("errormessage", "identification details are not found.");
		return "healthOfficerpage";
	}

	@RequestMapping(value = "/addMedicament", method = RequestMethod.POST)
	public @ResponseBody String addMedicament(@ModelAttribute MedicamentData us, BindingResult result) {
		
		String returnText;
		if (!result.hasErrors()) {
			MedicalAct mA = new MedicalAct();
			MedicalService service = mService.findById(us.getService());
			Patient res = PatientService.findPatientByIdnbr(us.getIdnb());
			mA.setDate(LocalDate.now());
			mA.setService(service);
			mA.setAmount(us.getQuantity()*service.getUnitPrice());
			mA.setPatient(res);
			System.out.println(mA.toString());
			mActs.createMedicalAct(mA);

			returnText = "User has been added to the list.";
		} else {
			returnText = "Sorry, an error has occur. User has not been added to list.";
		}
		return returnText;
	}

	@RequestMapping(value = "/getAll/{idnb}", method = RequestMethod.GET)
	public ModelAndView  getAll(Model model,@PathVariable String idnb) {
		Patient res = PatientService.findPatientByIdnbr(idnb);
		List<MedicalAct> examActs = mActs.findOneDateActByPatient(res, LocalDate.now());
		List<MedicalAct> mActs = examActs.stream().filter(acts -> acts.getService().getType()
				.equals(EMedicalServiceType.MEDICAMENT)).collect(Collectors.toList());
		  ModelAndView mv= new ModelAndView("patientDetails::exam_Acts"); 
	        mv.addObject("mActs",mActs);

	        return mv;
	}
	
	@RequestMapping(value = "/addExams", method = RequestMethod.POST)
	public @ResponseBody String addExams(@RequestBody ExamData examData) {
		String returnText;
		Patient res = PatientService.findPatientByIdnbr(examData.getIdnb());
		
	for(Exam exam:examData.getExam()){
		Long examId = Long.valueOf(exam.getExamName()).longValue();
			MedicalService service = mService.findById(examId);
			MedicalAct mA = new MedicalAct();
			mA.setDate(LocalDate.now());
			mA.setService(service);
			mA.setAmount(service.getUnitPrice());
			mA.setPatient(res);
			System.out.println(mA.toString());
			mActs.createMedicalAct(mA);
	}
		returnText = "exam has been added to the list.";
		
		return returnText;
	}
	@RequestMapping(value = "/getExam/{idnb}", method = RequestMethod.GET)
	public ModelAndView  getExam(Model model,@PathVariable String idnb) {
		Patient res = PatientService.findPatientByIdnbr(idnb);
		List<MedicalAct> examActs = mActs.findOneDateActByPatient(res, LocalDate.now());
		List<MedicalAct> eActs = examActs.stream().filter(acts -> acts.getService().getType()
				.equals(EMedicalServiceType.EXAM)).collect(Collectors.toList());
		  ModelAndView mv= new ModelAndView("patientDetails::exams"); 
	        mv.addObject("examActs",eActs);

	        return mv;
	}
}
