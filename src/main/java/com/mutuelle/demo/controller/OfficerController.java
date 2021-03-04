package com.mutuelle.demo.controller;

import java.security.Principal;
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

import com.mutuelle.demo.model.Invoice;
import com.mutuelle.demo.model.MedicalAct;
import com.mutuelle.demo.model.MedicalService;
import com.mutuelle.demo.model.Patient;
import com.mutuelle.demo.model.PaymentLog;
import com.mutuelle.demo.model.security.Users;
import com.mutuelle.demo.service.IInvoiceService;
import com.mutuelle.demo.service.IMedicalActService;
import com.mutuelle.demo.service.IMedicalServiceService;
import com.mutuelle.demo.service.IPatientService;
import com.mutuelle.demo.service.IPaymentService;
import com.mutuelle.demo.service.UserService;
import com.mutuelle.demo.utils.EMedicalServiceType;
import com.mutuelle.demo.utils.Exam;
import com.mutuelle.demo.utils.ExamData;
import com.mutuelle.demo.utils.MedicamentData;
import com.mutuelle.demo.utils.SearchPatient;


@Controller
public class OfficerController
{
    @Autowired
    private IPatientService PatientService;
    @Autowired
    private IMedicalServiceService mService;
    @Autowired
    private IMedicalActService mActs;
    @Autowired
    private UserService userService;

    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/search")
    public String searchPatient(@ModelAttribute("patient") @Valid final SearchPatient patient, final BindingResult results,
                                final Model model)
    {
        if (results.hasErrors())
        {
            System.out.println("Validation Errors occured");
            return "healthOfficerpage";
        }

        final Patient res = PatientService.findPatientByIdnbr(patient.getIdnb());
        final List<MedicalService> exams = mService.findMedicaments(EMedicalServiceType.EXAM);
        final List<MedicalAct> examActs = mActs.findOneDateActByPatient(res, LocalDate.now());
        final List<MedicalAct> mActs = examActs.stream().filter(acts -> acts.getService().getType()
            .equals(EMedicalServiceType.MEDICAMENT)).collect(Collectors.toList());
        final List<MedicalAct> eActs = examActs.stream().filter(acts -> acts.getService().getType()
            .equals(EMedicalServiceType.EXAM)).collect(Collectors.toList());

//		System.out.println(examActs);
        final List<MedicalService> medicaments = mService.findMedicaments(EMedicalServiceType.MEDICAMENT);
        // if the patient is found, we proceed
        if (res != null)
        {
            final boolean patientidnb = true;

            model.addAttribute("patient", res);
            final MedicamentData medicamentData = new MedicamentData();
            final ExamData examData = new ExamData();
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
    public @ResponseBody
    String addMedicament(@ModelAttribute final MedicamentData us, final BindingResult result)
    {

        final String returnText;
        if (!result.hasErrors())
        {
            final MedicalAct mA = new MedicalAct();
            final MedicalService service = mService.findById(us.getService());
            final Patient res = PatientService.findPatientByIdnbr(us.getIdnb());
            mA.setDate(LocalDate.now());
            mA.setService(service);
            mA.setAmount(us.getQuantity() * service.getUnitPrice());
            mA.setPatient(res);
            System.out.println(mA.toString());
            mActs.createMedicalAct(mA);

            returnText = "User has been added to the list.";
        }
        else
        {
            returnText = "Sorry, an error has occur. User has not been added to list.";
        }
        return returnText;
    }

    @RequestMapping(value = "/getAll/{idnb}", method = RequestMethod.GET)
    public ModelAndView getAll(final Model model, @PathVariable final String idnb)
    {
        final Patient res = PatientService.findPatientByIdnbr(idnb);
        final List<MedicalAct> examActs = mActs.findOneDateActByPatient(res, LocalDate.now());
        final List<MedicalAct> mActs = examActs.stream().filter(acts -> acts.getService().getType()
            .equals(EMedicalServiceType.MEDICAMENT)).collect(Collectors.toList());
        final ModelAndView mv = new ModelAndView("patientDetails::exam_Acts");
        mv.addObject("mActs", mActs);

        return mv;
    }

    @RequestMapping(value = "/addExams", method = RequestMethod.POST)
    public @ResponseBody
    String addExams(@RequestBody final ExamData examData)
    {
        final String returnText;
        final Patient res = PatientService.findPatientByIdnbr(examData.getIdnb());

        for (final Exam exam : examData.getExam())
        {
            final Long examId = Long.valueOf(exam.getExamName()).longValue();
            final MedicalService service = mService.findById(examId);
            final MedicalAct mA = new MedicalAct();
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
    public ModelAndView getExam(final Model model, @PathVariable final String idnb)
    {
        final Patient res = PatientService.findPatientByIdnbr(idnb);
        final List<MedicalAct> examActs = mActs.findOneDateActByPatient(res, LocalDate.now());
        final List<MedicalAct> eActs = examActs.stream().filter(acts -> acts.getService().getType()
            .equals(EMedicalServiceType.EXAM)).collect(Collectors.toList());
        final ModelAndView mv = new ModelAndView("patientDetails::exams");
        mv.addObject("examActs", eActs);

        return mv;
    }

    @RequestMapping(value = "/invoiceSuccess/{idnb}", method = RequestMethod.GET)
    public String generateInvoice(final Model model, @PathVariable final String idnb, final Principal principal)
    {
        // creating conslutation acts
        final MedicalAct mA = new MedicalAct();
        MedicalService service = (MedicalService) null;
        final List<MedicalService> services = mService.findByType(EMedicalServiceType.CONSULTATION);
        for (final MedicalService ms : services)
        {
            if (ms.getType().equals(EMedicalServiceType.CONSULTATION))
            {
                service = ms;
                break;
            }

        }
        final Patient res = PatientService.findPatientByIdnbr(idnb);
        mA.setDate(LocalDate.now());
        mA.setService(service);
        mA.setAmount(1200);
        mA.setPatient(res);
        //System.out.println(mA.toString());
        //mActs.createMedicalAct(mA);
        // generating personal invoice
        final Invoice inv = new Invoice();
        final long total = invoiceService.calculateInvoiceAmount(res, LocalDate.now());
        final Users user = userService.findByUsername(principal.getName());
        final long patient_Percentage = (total * 15) / 100;
        final long rssb_Percentage = total - patient_Percentage;
        inv.setGeneratedBy(user);
        inv.setPatient(res);
        inv.setPatientPayment(patient_Percentage);
        inv.setRssbPayment(rssb_Percentage);
        inv.setTotal(total);
        inv.setHealthFacility(user.getHealth_facility());

        final Invoice invoice = invoiceService.createInvoice(inv);
        model.addAttribute("invoice", invoice);

        //Update payment log by adding this amount to what RSSB owes the health facility
        final PaymentLog paymentLog = paymentService.findPaymentLog(invoice.getHealthFacility());
        final long updatedBalance = paymentLog.getTotalBalance() + invoice.getRssbPayment();
        paymentLog.setTotalBalance(updatedBalance);

        paymentService.updatePaymentLog(paymentLog);

        return "InvoiceSuccess";
    }
}
