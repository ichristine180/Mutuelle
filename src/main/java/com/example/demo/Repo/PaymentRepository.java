package com.example.demo.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

}