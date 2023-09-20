package com.sisbd.admin_gui.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Job {


	private SimpleIntegerProperty id;
	private SimpleStringProperty title;
	private SimpleStringProperty description;
	private SimpleDoubleProperty price;
	
	public Job(int id, String title, String description, double price) {
		this.setId(id);
		this.setTitle(title);
		this.setDescription(description);
		this.setPrice(price);
	}
	
	public Job(String title, String description, double price) {
		this.setTitle(title);
		this.setDescription(description);
		this.setPrice(price);
	}
	
	public Job() {
		// TODO Auto-generated constructor stub
	}

	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	} 
	public void setTitle(String title) {
		this.title = new SimpleStringProperty(title);
	} 
	public void setDescription(String description) {
		this.description = new SimpleStringProperty(description);
	} 
	public void setPrice(double price) {
		this.price = new SimpleDoubleProperty(price);
	} 
	
	public int getId() {
		return id.get();
	}
	public String getTitle() {
		return title.get();
	}
	public String getDescription() {
		return description.get();
	}
	public double getPrice() {
		return price.get();
	}

}
