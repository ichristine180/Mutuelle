package com.mutuelle.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mutuelle.demo.model.HealthFacility;
import com.mutuelle.demo.model.Patient;
import com.mutuelle.demo.model.PaymentLog;
import com.mutuelle.demo.model.security.User;
import com.mutuelle.demo.service.IPatientService;
import com.mutuelle.demo.service.IPaymentService;
import com.mutuelle.demo.service.UserService;
import com.mutuelle.demo.utils.Password;


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
    public String homepage(final Model model,Principal principal)
    {
    	List<User> users = userService.findUserList().stream().filter(user->user.isEnabled()== true).collect(Collectors.toList());
    	model.addAttribute("users", users);
    	model.addAttribute("username", principal.getName());
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
    @GetMapping("/change")
    public String change(final Model model,Principal principal)
    {
    	Password password = new Password();
    	model.addAttribute("password", password);
        return "change";
    }
    
    @PostMapping("/user/updatePassword")
    public String changeUserPassword(Locale locale, 
      @RequestParam("password") String password, 
      @RequestParam("oldpassword") String oldPassword) {
        User user = userService.findByEmail(
          SecurityContextHolder.getContext().getAuthentication().getName());
        
        if (!userService.checkIfValidOldPassword(user, oldPassword)) {
            return "change";
        }
        userService.changeUserPassword(user, password);
        return "redirect:/logout";
    }
}
