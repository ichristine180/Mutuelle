package com.mutuelle.demo.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mutuelle.demo.model.HealthFacility;
import com.mutuelle.demo.model.PaymentLog;
import com.mutuelle.demo.model.PaymentTransaction;
import com.mutuelle.demo.service.IHealthFacilityService;
import com.mutuelle.demo.service.IPaymentService;
import com.mutuelle.demo.utils.PayRequest;


@Controller
@RequestMapping("/rssb")
public class RssbController
{
    @Autowired
    private IPaymentService paymentService;

    private final Logger LOG = LoggerFactory.getLogger(RssbController.class);

    @Autowired
    private IHealthFacilityService iHealthFacilitySe;

    @GetMapping("/healthcenter/pay/{centerId}")
    public String startHealthCenterPayment(@PathVariable final long centerId, final Model model)
    {
        final HealthFacility healthFacility = iHealthFacilitySe.findOne(centerId);
        final PaymentLog paymentLog = paymentService.findPaymentLog(healthFacility);
        final List<PaymentTransaction> paymentTransactionList = paymentService.showPaymentTransactionHistory(healthFacility);

        model.addAttribute("paymentLog", paymentLog);
        model.addAttribute("transactionList", paymentTransactionList);
        model.addAttribute("transaction", new PayRequest());
        return "rssb/pay";
    }

    @PostMapping("/healthcenter/pay")
    public String processHealthCenterPayment(final PayRequest payRequest, final Model model, final Principal principal)
    {

        LOG.info("Transaction to be processed: {}", payRequest);
        final PaymentTransaction transaction = new PaymentTransaction();
        transaction.setPaymentDate(LocalDate.now());
        transaction.setHealthFacility(iHealthFacilitySe.findOne(payRequest.getCenterId()));
        transaction.setAmountPaid(payRequest.getAmount());
        final PaymentTransaction processedPayment = paymentService.processPayment(transaction);
        return "rssb/index";
    }
}
