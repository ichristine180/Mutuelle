package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Exam;


public interface IExamRepository  extends JpaRepository<Exam, Long> {

}
