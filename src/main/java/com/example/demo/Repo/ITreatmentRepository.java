package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Treatment;



public interface ITreatmentRepository  extends JpaRepository<Treatment, Long>{

}
