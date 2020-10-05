package com.example.demo.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.MedicalTest;

@Repository
public interface MedicalTestRepository extends CrudRepository<MedicalTest, Long> {

}
