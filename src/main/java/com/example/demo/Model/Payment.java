package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.Model.security.Users;

@Entity
@Table(name = "payment")
public class Payment {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long payment_id;
	private Long amount;
	private String status;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_code", nullable = false, unique = true)
	private Invoice invoice;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "doneBy")
	private Users doneBy;

	public Long getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Long payment_id) {
		this.payment_id = payment_id;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Payment() {
	}

	
}