package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.Model.MedicalService;

/**
 * @Author: chabiman
 * @FileName: IMedicalServiceDao.java
 * @Date: Oct 15, 2020
 * @Package: rw.mutuelle.dao
 * @ProjectName: mutuelle-payment-module
 *
 */
@Repository
public interface IMedicalServiceRepository extends JpaRepository<MedicalService, Long> {

}
