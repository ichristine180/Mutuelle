package com.mutuelle.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.model.HealthFacility;
import com.mutuelle.demo.model.PaymentLog;
import com.mutuelle.demo.model.PaymentTransaction;
import com.mutuelle.demo.repository.IPaymentLogRepository;
import com.mutuelle.demo.repository.IPaymentTransactionRepository;


@Service
public class PaymentService implements IPaymentService
{
    @Autowired
    private IPaymentLogRepository paymentLogRepository;

    @Autowired
    private IPaymentTransactionRepository paymentTransactionRepository;

    @Override
    public List<PaymentLog> showPaymentStatus()
    {
        try
        {
            return paymentLogRepository.findAll();
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public PaymentTransaction processPayment(final PaymentTransaction paymentTransaction)
    {
        try
        {
            long totalPaidAmount = 0;
            final List<PaymentTransaction> existingTransactions =
                paymentTransactionRepository.findByHealthFacility(paymentTransaction.getHealthFacility());
            for (final PaymentTransaction transaction : existingTransactions)
            {
                totalPaidAmount += transaction.getAmountPaid();
            }
            final long balance = paymentLogRepository.findByHealthFacility(paymentTransaction.getHealthFacility()).getTotalBalance();

            if (totalPaidAmount + paymentTransaction.getAmountPaid() > balance)
            {
                //Do nothing. RSSB is paying more than it should
                return null;
            }
            else
            {
                final PaymentTransaction paidTransacation = paymentTransactionRepository.save(paymentTransaction);
                final PaymentLog paymentLog = paymentLogRepository.findByHealthFacility(paymentTransaction.getHealthFacility());
                final long updatedBalance = paymentLog.getTotalBalance() - paymentTransaction.getAmountPaid();
                paymentLog.setHealthFacility(paymentTransaction.getHealthFacility());
                paymentLog.setTotalBalance(updatedBalance);
                paymentLogRepository.save(paymentLog);
                return paidTransacation;
            }
        }
        catch (final Exception exception)
        {
            throw exception;
        }
    }

    @Override
    public List<PaymentTransaction> showPaymentTransactionHistory(final HealthFacility healthFacility)
    {
        try
        {
            return paymentTransactionRepository.findByHealthFacility(healthFacility);
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public PaymentLog findPaymentLog(final HealthFacility healthFacility)
    {
        try
        {
            final PaymentLog paymentLog = paymentLogRepository.findByHealthFacility(healthFacility);
            if (paymentLog == null)
            {
                return new PaymentLog();
            }
            return paymentLog;
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public PaymentLog updatePaymentLog(final PaymentLog paymentLog)
    {
        try
        {
            return paymentLogRepository.save(paymentLog);
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }
}
