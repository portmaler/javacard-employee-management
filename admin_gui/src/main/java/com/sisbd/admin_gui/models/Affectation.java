package com.sisbd.admin_gui.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Affectation {
	
	private SimpleIntegerProperty id;
	private SimpleIntegerProperty idEmp;
	private SimpleIntegerProperty idJob;
	private SimpleStringProperty nomEmp;
	private SimpleStringProperty titleJob;
	
	public Affectation(int id, int idEmp, int idJob, String nomemp, String titlejob) {
		this.setId(id);
		this.setIdEmp(idEmp);
		this.setIdJob(idJob);
		this.setNomEmp(nomemp);
		this.setTitleJob(titlejob);
	}
	public Affectation(int idEmp, int idJob) {

		this.setIdEmp(idEmp);
		this.setIdJob(idJob);

	}
	
	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	} 
	public void setIdEmp(int id) {
		this.idEmp = new SimpleIntegerProperty(id);
	} 
	public void setIdJob(int id) {
		this.idJob = new SimpleIntegerProperty(id);
	} 
	public void setNomEmp(String nom) {
		this.nomEmp = new SimpleStringProperty(nom);
	} 
	public void setTitleJob(String title) {
		this.titleJob = new SimpleStringProperty(title);
	} 
	
	public int getId() {
		return id.get();
	}
	public int getIdEmp() {
		return idEmp.get();
	}
	public int getIdJob() {
		return idJob.get();
	}
	public String getNomEmp() {
		return nomEmp.get();
	}
	public String getTitleJob() {
		return titleJob.get();
	}

}
