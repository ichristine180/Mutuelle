package com.example.demo.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Model.Treatment;



public interface TreatmentRepository  extends CrudRepository<Treatment, Long> {

}
