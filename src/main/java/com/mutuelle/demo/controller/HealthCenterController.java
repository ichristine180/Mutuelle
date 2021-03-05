package com.mutuelle.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mutuelle.demo.model.Invoice;
import com.mutuelle.demo.model.MedicalAct;
import com.mutuelle.demo.model.MedicalService;
import com.mutuelle.demo.model.Patient;
import com.mutuelle.demo.service.IInvoiceService;
import com.mutuelle.demo.service.IMedicalActService;
import com.mutuelle.demo.service.IMedicalServiceService;
import com.mutuelle.demo.service.IPatientService;
import com.mutuelle.demo.utils.InvoiceHelper;
import com.mutuelle.demo.utils.MedicalActDraft;


@Controller
@RequestMapping("/healthcenter")
public class HealthCenterController
{
    @Autowired
    private IPatientService patientService;

    @Autowired
    private IMedicalActService medicalActService;

    @Autowired
    private IMedicalServiceService medicalServiceService;

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
        model.addAttribute("medicalActDraft", new MedicalActDraft());
        return "officer/payment";
    }

    @PostMapping(value = "/patient/transaction/draft", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processProvidedMedicalServices(final MedicalActDraft medicalActDraft, final Model model)
    {
        final Patient patient = patientService.findPatientById(medicalActDraft.getPatientId());
        LOG.info("Draft Received: {}", medicalActDraft);

        final List<MedicalAct> medicalActList = new ArrayList<>();
        saveDraft(medicalActList, medicalActDraft.getConsultationServiceList(), patient);
        saveDraft(medicalActList, medicalActDraft.getLaboratoryServiceList(), patient);
        saveDraft(medicalActList, medicalActDraft.getDrugList(), patient);

        model.addAttribute("patient", patient);
        model.addAttribute("medicalActDraftList", medicalActList);
        model.addAttribute("invoiceHelper", new InvoiceHelper());
        return "officer/draft";
    }

    @PostMapping(value = "/patient/transaction/invoice", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String generatePatientInvoice(final InvoiceHelper invoiceHelper, final Model model)
    {
        final Patient patient = patientService.findPatientById(invoiceHelper.getPatientId());
        LOG.info("Draft Received: {}", invoiceHelper);

        model.addAttribute("patient", patient);
        return "officer/invoice";
    }

    private static void saveDraft(final List<MedicalAct> medicalActList,
                                  final List<MedicalService> medicalServiceList,
                                  final Patient patient)
    {
        for (final MedicalService medicalService : medicalServiceList)
        {
            final MedicalAct medicalAct = new MedicalAct();
            medicalAct.setDate(LocalDate.now());
            medicalAct.setService(medicalService);
            medicalAct.setPatient(patient);
            medicalActList.add(medicalAct);
        }
    }
}
