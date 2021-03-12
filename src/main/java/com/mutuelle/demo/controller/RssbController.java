package com.mutuelle.demo.controller;

import java.security.Principal;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
import com.mutuelle.demo.model.Invoice;
import com.mutuelle.demo.model.PaymentLog;
import com.mutuelle.demo.model.PaymentTransaction;
import com.mutuelle.demo.model.security.User;
import com.mutuelle.demo.service.IHealthFacilityService;
import com.mutuelle.demo.service.IInvoiceService;
import com.mutuelle.demo.service.IPaymentService;
import com.mutuelle.demo.service.IUserService;
import com.mutuelle.demo.utils.PayRequest;

@Controller
@RequestMapping("/rssb")
public class RssbController {
	@Autowired
	private IPaymentService paymentService;

	private final Logger LOG = LoggerFactory.getLogger(RssbController.class);

	@Autowired
	private IHealthFacilityService iHealthFacilitySe;
	@Autowired
	private IInvoiceService invoiceService;
	@Autowired
	private IUserService userService;

	@GetMapping("/healthcenter/pay/{centerId}")
	public String startHealthCenterPayment(@PathVariable final long centerId, final Model model) {
		final HealthFacility healthFacility = iHealthFacilitySe.findOne(centerId);
		final PaymentLog paymentLog = paymentService.findPaymentLog(healthFacility);
		final List<PaymentTransaction> paymentTransactionList = paymentService
				.showPaymentTransactionHistory(healthFacility);

		model.addAttribute("paymentLog", paymentLog);
		model.addAttribute("transactionList", paymentTransactionList);
		model.addAttribute("transaction", new PayRequest());
		return "rssb/pay";
	}

	@PostMapping("/healthcenter/pay")
	public String processHealthCenterPayment(final PayRequest payRequest, final Model model,
			final Principal principal) {
		final PaymentLog paymentLog = paymentService
				.findPaymentLog(iHealthFacilitySe.findOne(payRequest.getCenterId()));
		if (payRequest.getAmount() == 0 || payRequest.getAmount() > paymentLog.getTotalBalance()) {
			final HealthFacility healthFacility = iHealthFacilitySe.findOne(payRequest.getCenterId());
			final List<PaymentTransaction> paymentTransactionList = paymentService
					.showPaymentTransactionHistory(healthFacility);

			model.addAttribute("paymentLog", paymentLog);
			model.addAttribute("transactionList", paymentTransactionList);
			model.addAttribute("transaction", new PayRequest());
			model.addAttribute("message", "you have to pay above 0 but less than or equal to  "+ paymentLog.getTotalBalance());
			return "rssb/pay";
		}
		LOG.info("Transaction to be processed: {}", payRequest);
		final PaymentTransaction transaction = new PaymentTransaction();
		transaction.setPaymentDate(LocalDate.now());
		transaction.setHealthFacility(iHealthFacilitySe.findOne(payRequest.getCenterId()));
		transaction.setAmountPaid(payRequest.getAmount());
		User processedBy = userService.findByUsername(principal.getName());
		transaction.setProcessedBy(processedBy);
		final PaymentTransaction processedPayment = paymentService.processPayment(transaction);
		return "redirect:/rssb";
	}

	@GetMapping("/report")
	public String Receptionist(final Model model, final Principal principal) {
		final List<PaymentLog> paymentLogList = paymentService.showPaymentStatus();
		model.addAttribute("paymentLogList", paymentLogList);
		return "rssb/report";
	}

	@GetMapping("paymentDetails/{centerId}")
	public String paymentDetails(final Model model, @PathVariable final long centerId) {
		HealthFacility hf = iHealthFacilitySe.findOne(centerId);
		final List<PaymentLog> paymentLogList = paymentService.showPaymentStatus().stream()
				.filter(log -> log.getHealthFacility() == hf).collect(Collectors.toList());
		final List<PaymentTransaction> paidAmmount = paymentService.showPaymentTransactionHistory(hf);
		model.addAttribute("paidAmmount", paidAmmount);
		model.addAttribute("paymentLogList", paymentLogList);
		return "rssb/paymentDetails";

	}

	@GetMapping("invoice/details/{centerId}")
	public String invoiceDetails(final Model model, @PathVariable final long centerId) {
		HealthFacility hf = iHealthFacilitySe.findOne(centerId);
		final List<Invoice> invoice = invoiceService.findInvoiceByFacility(hf);
		model.addAttribute("invoice", invoice);
		model.addAttribute("hf", hf);
		return "rssb/invoice";

	}
}
