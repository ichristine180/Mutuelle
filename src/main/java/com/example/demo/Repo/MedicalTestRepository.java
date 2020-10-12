package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.MedicalTest;
import com.example.demo.Model.Treatment;

@Repository
public interface MedicalTestRepository extends CrudRepository<MedicalTest, Long> {
	MedicalTest findBytestId(Long testId);

	@Override
	List<MedicalTest> findAll();

}
