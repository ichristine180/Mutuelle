package com.mutuelle.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mutuelle.demo.model.Patient;
import com.mutuelle.demo.model.PaymentLog;
import com.mutuelle.demo.service.IPatientService;
import com.mutuelle.demo.service.IPaymentService;
import com.mutuelle.demo.service.UserService;


@Controller
public class UserController
{

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String homepage(final Model model)
    {
    	model.addAttribute("users", userService.findUserList());

        return "adminpage";
    }

    @GetMapping("/rssb")
    public String Receptionist(final Model model, final Principal principal)
    {
        final List<PaymentLog> paymentLogList = paymentService.showPaymentStatus();
        model.addAttribute("paymentLogList", paymentLogList);
        return "rssb/index";
    }

    @GetMapping("/healthcenter")
    public String gDoctor(final Model model, final Principal principal)
    {
        final List<Patient> patientList = patientService.findAllPatient();
        model.addAttribute("patientList", patientList);
        return "officer/index";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

}
