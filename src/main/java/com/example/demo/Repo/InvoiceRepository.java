package com.example.demo.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}