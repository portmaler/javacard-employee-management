package com.sisbd.admin_gui.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employer {

	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleIntegerProperty id;
	private SimpleDoubleProperty balance;
	private SimpleDoubleProperty balance_m;
	private SimpleStringProperty phone;
	private SimpleStringProperty speciality;
	
	public Employer(int id, String fn, String ln, double b, double bm, String phone, String speciality) {
		this.setId(id);
		this.setFirstName(fn);
		this.setLastName(ln);
		this.setBalance(b);
		this.setBalance_m(bm);
		this.setPhone(phone);
		this.setSpeciality(speciality);
	}
	
	public Employer(String fn, String ln, double b, double bm, String phone, String speciality) {
		this.setFirstName(fn);
		this.setLastName(ln);
		this.setBalance(b);
		this.setBalance_m(bm);
		this.setPhone(phone);
		this.setSpeciality(speciality);
	}

	public Employer() {
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public double getBalance() {
		return balance.get();
	}

	public void setBalance(Double balance) {
		this.balance = new SimpleDoubleProperty(balance);
	}
	
	public double getBalance_m() {
		return balance_m.get();
	}
	
	public void setBalance_m(Double balance_m) {
		this.balance_m = new SimpleDoubleProperty(balance_m);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}

	public String getSpeciality() {
		return speciality.get();
	}

	public void setSpeciality(String speciality) {
		this.speciality = new SimpleStringProperty(speciality);
	}
	
	
	
	
	
}
