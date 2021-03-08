package com.mutuelle.demo.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mutuelle.demo.model.HealthFacility;
import com.mutuelle.demo.model.PaymentLog;
import com.mutuelle.demo.model.security.Role;
import com.mutuelle.demo.model.security.User;
import com.mutuelle.demo.model.security.UserRole;
import com.mutuelle.demo.service.IHealthFacilityService;
import com.mutuelle.demo.service.IPaymentService;
import com.mutuelle.demo.service.IUserService;


@Controller
public class AdminController
{

    private final Logger LOG = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private IHealthFacilityService healthService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPaymentService paymentService;

    @GetMapping(value = "/addHealthFacility")
    public String addHealthFacility(final Model model)
    {

        final HealthFacility healthFacility = new HealthFacility();
        model.addAttribute("healthFacility", healthFacility);
        return "admin/addHealthFacicility";
    }

    @PostMapping(value = "/addfacility")
    public String addfacility(@ModelAttribute("healthFacility") @Valid final HealthFacility hf, final BindingResult result,
                              final Model model)
    {
        if (result.hasErrors())
        {
            System.out.println("validation error");
            return "admin/addHealthFacicility";
        }
        // System.out.println(hf);
        final HealthFacility exit = healthService.findByName(hf.getFacilityName());
        if (exit == null)
        {
            final HealthFacility newHF = new HealthFacility();
            newHF.setAdrress(hf.getAdrress());
            newHF.setFacilityName(hf.getFacilityName());
            final HealthFacility createdHf = healthService.create(newHF);
            if (createdHf != null)
            {
                model.addAttribute("successMessage", "Health Center added Successfully");
                model.addAttribute("healthFacility", healthService.findAllHealthFacilities());
                return "admin/healthFacility";
            }

        }
        else
        {
            model.addAttribute("message", "Health Center with provided name exit");
            return "admin/addHealthFacicility";
        }
        return "admin/addHealthFacicility";

    }

    @GetMapping(value = "/healthFacilities")
    public String healthFacilities(final Model model)
    {
        final List<HealthFacility> healthFacility = healthService.findAllHealthFacilities();
        LOG.info("{}", healthFacility);
        model.addAttribute("healthFacility", healthFacility);
        return "admin/healthFacility";
    }

    @GetMapping(value = "/user")
    public String addUser(final Model model)
    {
        final User user = new User();
        final Iterable<Role> roles = userService.findAllRole();
        final Iterable<HealthFacility> healthFacility = healthService.findAllHealthFacilities();
        model.addAttribute("healthFacility", healthFacility);
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "admin/user";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") @Valid final User user, final BindingResult results, final Model model)
    {
        if (results.hasErrors())
        {
            final Iterable<Role> roles = userService.findAllRole();
            final Iterable<HealthFacility> healthFacility = healthService.findAllHealthFacilities();
            model.addAttribute("healthFacility", healthFacility);
            model.addAttribute("roles", roles);
            System.out.println("Errors Found" + results.getAllErrors().toString());
            return "admin/user";
        }
        if (userService.checkUsernameExists(user.getEmail()))
        {
            final Iterable<Role> roles = userService.findAllRole();
            final Iterable<HealthFacility> healthFacility = healthService.findAllHealthFacilities();
            model.addAttribute("healthFacility", healthFacility);
            model.addAttribute("roles", roles);
            model.addAttribute("message", "There is user with this Email ");
            System.out.println("email exists");
            return "admin/user";
        }

        final User myuser = new User();
        final HealthFacility hf;
        if (user.getUsername().equals("ROLE_ADMIN"))
        {
            hf = healthService.findByName("MiniSante");
        }
        else
        {
            hf = healthService.findOne(user.getHealthFacility().getId());
        }
        // System.out.print(user.getUsername());
        myuser.setfName(user.getfName());
        myuser.setPassword("pass");
        myuser.setEmail(user.getEmail());
        myuser.setlName(user.getlName());
        myuser.setUsername(user.getEmail());
        if (hf != null)
        {
            myuser.setHealthFacility(hf);
        }
        final Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(myuser, userService.findByName(user.getUsername())));
        final User createdUser = userService.createUser(myuser, userRoles);
        if (createdUser != null)
        {
            model.addAttribute("successMessage", "User added Successfully");
            model.addAttribute("users", userService.findUserList());
            return "adminpage";
        }
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") final long id, final Model model)
    {
        final User user = userService.findById(id);
        userService.delete(user);
        model.addAttribute("successMessage", "User deleted Successfully");
        model.addAttribute("users", userService.findUserList());
        return "redirect:/admin";
    }
    
    @GetMapping("/report")
    public String Receptionist(final Model model, final Principal principal)
    {
        final List<PaymentLog> paymentLogList = paymentService.showPaymentStatus();
        model.addAttribute("paymentLogList", paymentLogList);
        return "admin/report";
    }
}
