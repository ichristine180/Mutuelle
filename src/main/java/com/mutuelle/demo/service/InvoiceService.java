package com.mutuelle.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.Model.Invoice;
import com.mutuelle.demo.Model.MedicalAct;
import com.mutuelle.demo.Model.Patient;
import com.mutuelle.demo.Repo.InvoiceRepository;


@Service(IInvoiceService.NAME)
public class InvoiceService implements IInvoiceService
{

    @Autowired
    private IMedicalActService mActService;
    @Autowired
    private InvoiceRepository iRepository;

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
    public Invoice createMedicalAct(final Invoice invoice)
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
}
