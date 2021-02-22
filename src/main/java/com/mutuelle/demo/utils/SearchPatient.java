package com.mutuelle.demo.utils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SearchPatient {

	@NotNull(message = " This field cant be null")
	@Pattern(regexp = "[0-9]+", message = "Invalid characters. Use digits only")
	@Size(min = 16, max = 16, message = "Valid National Identification is 16 Digits")	
private String idnb;

public String getIdnb() {
	return idnb;
}

public void setIdnb(String idnb) {
	this.idnb = idnb;
}
}
