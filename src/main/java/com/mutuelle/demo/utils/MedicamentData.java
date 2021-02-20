package com.mutuelle.demo.utils;

public class MedicamentData {
@Override
public String toString() {
	return "MedicamentData [Service=" + Service + ", quantity=" + quantity + ", idnb=" + idnb + "]";
}

private Long Service;
private Double quantity;
private String idnb;


public String getIdnb() {
	return idnb;
}

public void setIdnb(String idnb) {
	this.idnb = idnb;
}

public Double getQuantity() {
	return quantity;
}

public void setQuantity(Double quantity) {
	this.quantity = quantity;
}

public Long getService() {
	return Service;
}

public void setService(Long service) {
	Service = service;
}


}
