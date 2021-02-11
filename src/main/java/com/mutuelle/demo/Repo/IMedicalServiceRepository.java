package com.mutuelle.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.mutuelle.demo.Model.MedicalService;

@Repository
public interface IMedicalServiceRepository extends JpaRepository<MedicalService, Long> {

}
