package com.mutuelle.demo.utils;

import java.util.Arrays;

public class ExamData {
	@Override
	public String toString() {
		return "ExamData [exam=" + Arrays.toString(exam) + ", idnb=" + idnb + "]";
	}
	private Exam[] exam;
	
	public Exam[] getExam() {
		return exam;
	}
	public void setExam(Exam[] exam) {
		this.exam = exam;
	}
	private String idnb;
	
	public String getIdnb() {
		return idnb;
	}
	public void setIdnb(String idnb) {
		this.idnb = idnb;
	}
	
	

}
