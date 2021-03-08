package com.mutuelle.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.model.HealthFacility;
import com.mutuelle.demo.model.Invoice;
import com.mutuelle.demo.model.MedicalAct;
import com.mutuelle.demo.model.Patient;
import com.mutuelle.demo.model.PaymentLog;
import com.mutuelle.demo.model.security.User;
import com.mutuelle.demo.repository.InvoiceRepository;
import com.mutuelle.demo.utils.DetailedInvoice;
import com.mutuelle.demo.utils.EUbudeheCategory;


@Service
public class InvoiceService implements IInvoiceService
{
    @Autowired
    private IMedicalActService mActService;

    @Autowired
    private InvoiceRepository iRepository;

    @Autowired
    private IMedicalActService medicalActService;

    @Autowired
    private IPaymentService paymentService;

    /*
     *
     * @see rw.mutuelle.service.IInvoiceService#calculateInvoiceAmount(rw.mutuelle.model.MedicalAct)
     */
    @Override
    public long calculateInvoiceAmount(final Patient patient, final LocalDate date)
    {
        try
        {
            long amount = 0;
            final List<MedicalAct> medicalActList = mActService.findOneDateActByPatient(patient, date);
            for (final MedicalAct act : medicalActList)
            {
                final double actPrice = act.getAmount() * act.getService().getUnitPrice();
                amount += actPrice;
            }
            return amount;
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public Invoice createInvoice(final Invoice invoice)
    {
        try
        {
            return iRepository.save(invoice);
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public DetailedInvoice savePatientTreatementTransaction(final List<MedicalAct> treatementList, final Patient patient, final User processedBy)
    {
        final HealthFacility healthFacility = processedBy.getHealthFacility();
        final DetailedInvoice detailedInvoice = new DetailedInvoice();
        final List<MedicalAct> servedServiceList = new ArrayList<>();
        long totalAmount = 0;
        //Save MedicalActs
        for (final MedicalAct medicalAct : treatementList)
        {
            final double actPrice = medicalAct.getAmount() * medicalAct.getService().getUnitPrice();
            totalAmount += actPrice;
            servedServiceList.add(medicalActService.createMedicalAct(medicalAct));
        }
        final long patientPercentage = calculatePatientPercentageAmount(totalAmount, patient);
        final long rssbPercentage = totalAmount - patientPercentage;

        //Create Invoice
        createPatientInvoice(patient, processedBy, healthFacility, totalAmount, patientPercentage, rssbPercentage);

        //CreateInvoice
        updatePaymentLog(healthFacility, rssbPercentage);

        //Populated detailed invoice to be returned to the front end
        populateDetailedInvoice(patient, healthFacility, detailedInvoice, servedServiceList, totalAmount, patientPercentage, rssbPercentage);

        detailedInvoice.setInvoiceNumber((patient.getIdnb().substring(patient.getIdnb().length() - 4)
            + RandomStringUtils.randomAlphanumeric(5)).toUpperCase());

        return detailedInvoice;
    }

    private static long calculatePatientPercentageAmount(final long totalAmount, final Patient patient)
    {
        if (patient.getCategory().equals(EUbudeheCategory.SECOND) || patient.getCategory().equals(EUbudeheCategory.THIRD)
            || patient.getCategory().equals(EUbudeheCategory.FOURTH))
        {
            return 200;
        }
        {
            return 0;
        }
    }

    @Override
    public List<Invoice> findInvoice(final Patient patient)
    {
        return iRepository.findByPatient(patient);
    }

    private static void populateDetailedInvoice(final Patient patient,
                                                final HealthFacility healthFacility,
                                                final DetailedInvoice detailedInvoice,
                                                final List<MedicalAct> servedServiceList,
                                                final long totalAmount,
                                                final long patientPercentage,
                                                final long rssbPercentage)
    {
        detailedInvoice.setTreatementList(servedServiceList);
        detailedInvoice.setPatient(patient);
        detailedInvoice.setHealthFacility(healthFacility);
        detailedInvoice.setPatientAmount(patientPercentage);
        detailedInvoice.setInsuranceAmount(rssbPercentage);
        detailedInvoice.setTotalAmount(totalAmount);
        detailedInvoice.setTreatedOn(LocalDate.now());
    }

    private void createPatientInvoice(final Patient patient,
                                      final User processedBy,
                                      final HealthFacility healthFacility,
                                      final long totalAmount,
                                      final long patientPercentage,
                                      final long rssbPercentage)
    {
        final Invoice invoice = new Invoice();
        invoice.setPatient(patient);
        invoice.setHealthFacility(healthFacility);
        invoice.setGeneratedBy(processedBy);
        invoice.setTotal(totalAmount);
        invoice.setRssbPayment(rssbPercentage);
        invoice.setPatientPayment(patientPercentage);
        invoice.setCreatedOn(LocalDate.now());
        iRepository.save(invoice);
    }

    private void updatePaymentLog(final HealthFacility healthFacility, final long rssbPercentage)
    {
        //Create Payment Log/update paymentLog
        final PaymentLog paymentLog = paymentService.findPaymentLog(healthFacility);
        paymentLog.setHealthFacility(healthFacility);
        paymentLog.setTotalBalance(paymentLog.getTotalBalance() + rssbPercentage);
        paymentService.updatePaymentLog(paymentLog);
    }
}
