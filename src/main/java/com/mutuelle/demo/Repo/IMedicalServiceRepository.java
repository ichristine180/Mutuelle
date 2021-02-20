package com.mutuelle.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.Model.MedicalService;
import com.mutuelle.demo.utils.EMedicalServiceType;

@Repository
public interface IMedicalServiceRepository extends JpaRepository<MedicalService, Long> {
@Query("select e from MedicalService e where type is 'EXAM' ")
	List<MedicalService> findExams();

List<MedicalService> findByType(EMedicalServiceType type);
}
