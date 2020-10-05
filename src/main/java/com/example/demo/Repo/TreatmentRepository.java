package com.example.demo.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Treatment;

@Repository
public interface TreatmentRepository extends CrudRepository<Treatment, Long> {

}