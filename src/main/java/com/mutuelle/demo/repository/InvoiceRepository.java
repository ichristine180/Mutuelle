package com.mutuelle.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.model.Invoice;


@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long>
{

}