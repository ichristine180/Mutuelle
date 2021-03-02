package com.mutuelle.demo.service;

import java.util.List;

import com.mutuelle.demo.model.HealthFacility;
import com.mutuelle.demo.model.PaymentLog;
import com.mutuelle.demo.model.PaymentTransaction;


/**
 * The interface Payment service.
 */
public interface IPaymentService
{
    /**
     * Show payment status list.
     *
     * @return the list
     */
    List<PaymentLog> showPaymentStatus();

    /**
     * Process payment payment transaction.
     *
     * @param paymentTransaction the payment transaction
     * @return the payment transaction
     */
    PaymentTransaction processPayment(final PaymentTransaction paymentTransaction);

    /**
     * Show payment transaction history list.
     *
     * @param healthFacility the health facility
     * @return the list
     */
    List<PaymentTransaction> showPaymentTransactionHistory(final HealthFacility healthFacility);

    /**
     * Find payment log payment log.
     *
     * @param healthFacility the health facility
     * @return the payment log
     */
    PaymentLog findPaymentLog(HealthFacility healthFacility);

    /**
     * Update payment log payment log.
     *
     * @param paymentLog the payment log
     * @return the payment log
     */
    PaymentLog updatePaymentLog(PaymentLog paymentLog);
}
