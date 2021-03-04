package com.mutuelle.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mutuelle.demo.model.Invoice;
import com.mutuelle.demo.model.MedicalService;
import com.mutuelle.demo.model.Patient;
import com.mutuelle.demo.service.IInvoiceService;
import com.mutuelle.demo.service.IMedicalServiceService;
import com.mutuelle.demo.service.IPatientService;


@Controller
@RequestMapping("/healthcenter")
public class HealthCenterController
{
    @Autowired
    private IPatientService patientService;

    @Autowired
    IMedicalServiceService medicalServiceService;

    private final Logger LOG = LoggerFactory.getLogger(HealthCenterController.class);
    @Autowired
    private IInvoiceService invoiceService;

    @GetMapping("/patient/details/{id}")
    public String showPatientInformation(@PathVariable final long id, final Model model)
    {
        final Patient patient = patientService.findPatientById(id);
        final List<Invoice> pastInvoiceList = invoiceService.findInvoice(patient);
        LOG.info("{}", pastInvoiceList);
        model.addAttribute("patient", patient);
        model.addAttribute("treatementHistory", pastInvoiceList);
        return "officer/patient";
    }

    @GetMapping("/patient/transaction/{patientId}")
    public String createNewPayment(@PathVariable final long patientId, final Model model)
    {
        final Patient patient = patientService.findPatientById(patientId);
        final List<MedicalService> medicalServiceList = medicalServiceService.findAll();
        LOG.info("{}", medicalServiceList);
        model.addAttribute("patient", patient);
        model.addAttribute("medicalServiceList", medicalServiceList);
        return "officer/payment";
    }
}
