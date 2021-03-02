package com.mutuelle.demo.rest;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutuelle.demo.model.MedicalAct;
import com.mutuelle.demo.model.Patient;
import com.mutuelle.demo.model.PaymentLog;
import com.mutuelle.demo.model.security.Users;
import com.mutuelle.demo.service.IInvoiceService;
import com.mutuelle.demo.service.IPatientService;
import com.mutuelle.demo.service.IPaymentService;
import com.mutuelle.demo.service.IUserService;
import com.mutuelle.demo.utils.DetailedInvoice;
import com.mutuelle.demo.utils.MedicalActDto;
import com.mutuelle.demo.utils.PrescriptionData;


@RestController
@RequestMapping("/api")
public class ApiController
{
    private final Logger LOG = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IUserService userService;

    @GetMapping("payment/details")
    public ResponseEntity<List<PaymentLog>> getPaymentDetails()
    {
        LOG.info("Request Received: {}", paymentService.showPaymentStatus());
        return new ResponseEntity<>(paymentService.showPaymentStatus(), HttpStatus.OK);
    }

    @PostMapping("process/patient")
    public ResponseEntity<DetailedInvoice> processPatientServedServices(@RequestBody final PrescriptionData prescriptionData,
                                                                        final Principal principal)
    {
        LOG.info("Request coming successfully: {}", prescriptionData);
        final Patient patient = patientService.findPatientById(prescriptionData.getPatientId());
        LOG.info("Patient: {}", patient);
        final Users processedBy = userService.findByUsername("muganga");

        final List<MedicalAct> medicalActList = new ArrayList<>();
        for (final MedicalActDto medicalActDto : prescriptionData.getMedicalActDtoList())
        {
            final MedicalAct medicalAct = new MedicalAct();
            medicalAct.setPatient(patient);
            medicalAct.setService(medicalActDto.getService());
            medicalAct.setAmount(medicalActDto.getAmount());
            medicalAct.setDate(LocalDate.now());

            medicalActList.add(medicalAct);
        }
        final DetailedInvoice response =
            invoiceService.savePatientTreatementTransaction(medicalActList, patient, processedBy);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
