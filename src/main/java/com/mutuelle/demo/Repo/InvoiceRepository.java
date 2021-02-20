package com.mutuelle.demo.Repo;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.mutuelle.demo.Model.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}