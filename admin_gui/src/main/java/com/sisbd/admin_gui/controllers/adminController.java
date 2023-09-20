package com.sisbd.admin_gui.controllers;

import com.sisbd.admin_gui.connection.mysqlconnect;
import com.sisbd.admin_gui.models.Affectation;
import com.sisbd.admin_gui.models.Employer;
import com.sisbd.admin_gui.models.Job;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Window;

import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;

public class adminController implements Initializable {

    @FXML
    private Button btnAddAff;

    @FXML
    private Button btnAddEmp;

    @FXML
    private Button btnAddJob;
    
    @FXML
    private Button btnAff;

    @FXML
    private Button btnEmp;

    @FXML
    private Button btnJob;
    
    @FXML
    private Text L1;

    @FXML
    private Text L2;
    
    @FXML
    private Pane statusPane;

    @FXML
    private TableView<Employer> empTable;
    
    @FXML
    private TableView<Job> jobTable;

    @FXML
    private TableView<Affectation> affTable;

    @FXML
    private GridPane pnAff;

    @FXML
    private GridPane pnEmp;

    @FXML
    private GridPane pnJob;
    
    
    
    @FXML
    private TableColumn<Employer, Double> colBA0;
    @FXML
    private TableColumn<Employer, Double> colBM0;
    @FXML
    private TableColumn<Employer, String> colFN0;
    @FXML
    private TableColumn<Employer, Integer> colID0;
    @FXML
    private TableColumn<Employer, String> colLN0;
    @FXML
    private TableColumn<Employer, String> colPH0;
    @FXML
    private TableColumn<Employer, String> colSP0;
    
    
    
    
    @FXML
    private TableColumn<Job, String> colDescr1;
    @FXML
    private TableColumn<Job, Integer> colID1;
    @FXML
    private TableColumn<Job, Double> colPrice1;
    @FXML
    private TableColumn<Job, String> colTitle1;
    
    
    
    @FXML
    private TableColumn<Affectation, Integer> colIDemp2;
    @FXML
    private TableColumn<Affectation, Integer> colIDjob2;
    @FXML
    private TableColumn<Affectation, String> colNemp2;
    @FXML
    private TableColumn<Affectation, String> colTjob2;
    @FXML
    private TableColumn<Affectation, Integer> colID2;
    
    
    Window window;
	
    Connection conn = null;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		conn = mysqlconnect.ConnectDb();
		
		obl = mysqlconnect.getEmployers(conn);
		obl1 = mysqlconnect.getJobs(conn);
		obl2 = mysqlconnect.getAffectations(conn);
		
		colID0.setCellValueFactory(new PropertyValueFactory<Employer, Integer>("Id"));
		colFN0.setCellValueFactory(new PropertyValueFactory<Employer, String>("firstName"));
		colLN0.setCellValueFactory(new PropertyValueFactory<Employer, String>("lastName"));
		colSP0.setCellValueFactory(new PropertyValueFactory<Employer, String>("speciality"));
		colPH0.setCellValueFactory(new PropertyValueFactory<Employer, String>("phone"));
		colBA0.setCellValueFactory(new PropertyValueFactory<Employer, Double>("balance"));
		colBM0.setCellValueFactory(new PropertyValueFactory<Employer, Double>("balance_m"));
		empTable.setItems(obl);
		
		
		colID1.setCellValueFactory(new PropertyValueFactory<>("Id"));
		colTitle1.setCellValueFactory(new PropertyValueFactory<>("Title"));
		colDescr1.setCellValueFactory(new PropertyValueFactory<>("Description"));
		colPrice1.setCellValueFactory(new PropertyValueFactory<>("Price"));
		jobTable.setItems(obl1);
		
		
		
		colID2.setCellValueFactory(new PropertyValueFactory<>("Id"));
		colIDemp2.setCellValueFactory(new PropertyValueFactory<>("IdEmp"));
		colIDjob2.setCellValueFactory(new PropertyValueFactory<>("IdJob"));
		colNemp2.setCellValueFactory(new PropertyValueFactory<>("nomEmp"));
		colTjob2.setCellValueFactory(new PropertyValueFactory<>("titleJob"));
		affTable.setItems(obl2);
		
	}
	
	public adminController() {
		
	}

	//ObservableList<Employer> obl = FXCollections.observableArrayList(new Employer(1, "aissame", "abousalim", 0, 0,"06","enginner"), new Employer(2, "aissame", "abousalim", 0, 0,"06","enginner"));
	ObservableList<Employer> obl = null;
	//ObservableList<Job> obl1 = FXCollections.observableArrayList(new Job(1, "front site web", "realisation du front end", 2000.00), new Job(2, "ui/ux site web", "realisation du ui/ux with figma", 1000.00));
	ObservableList<Job> obl1 = FXCollections.observableArrayList();
	//ObservableList<Affectation> obl2 = FXCollections.observableArrayList(new Affectation(1, 1, 1, "aissame", "front site web"), new Affectation(2, 1, 2, "aissame", "ui/ux site web"));
	ObservableList<Affectation> obl2 = FXCollections.observableArrayList();
	
	@FXML
	private void handleClicks(ActionEvent event) {
		if(event.getSource() == btnEmp) {
			L1.setText("E");
			L2.setText("mployers");
			pnEmp.toFront();
			obl = mysqlconnect.getEmployers(conn);
			empTable.setItems(obl);
			
			//empTable.setItems(null);
		}
		if(event.getSource() == btnJob) {
			L1.setText("J");
			L2.setText("obs");
			pnJob.toFront();
			obl1 = mysqlconnect.getJobs(conn);
			jobTable.setItems(obl1);
		}
		if(event.getSource() == btnAff) {
			L1.setText("A");
			L2.setText("ffectation");
			pnAff.toFront();
			obl2 = mysqlconnect.getAffectations(conn);
			affTable.setItems(obl2);
		}
		if(event.getSource() == btnAddEmp) {
			addEmpDialog();
		}
		if(event.getSource() == btnAddJob) {
			addJobDialog();
		}
		if(event.getSource() == btnAddAff) {
			addAffDialog();
		}
		
	}
	

	private void addAffDialog() {
	
		Dialog<Affectation> dialog = new Dialog<>();
        dialog.setTitle("Add Affectation");
        dialog.setHeaderText("Enter the details of the Affectation");
        dialog.setResizable(false);
        
        ButtonType addBtn = new ButtonType("Affect", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addBtn, ButtonType.CANCEL);
        
        Label idjob = new Label("Id Job: ");
        
        
        ChoiceBox titlejobCB = new ChoiceBox();
        for(Job jj : obl1) {
        	titlejobCB.getItems().add(jj.getTitle());
        }
        
  
    
        Label idemp = new Label("Id Employer: ");
      //add observable list that get first and last name ..
        ObservableList<String> nameEmp = FXCollections.observableArrayList();
        ChoiceBox nameempCB = new ChoiceBox();
        for(Employer e:obl) {
        	nameempCB.getItems().add(e.getFirstName()+" "+e.getLastName());
        }
        
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, idjob, titlejobCB);
        grid.addRow(1, idemp, nameempCB);
        dialog.getDialogPane().setContent(grid);
        
        dialog.setResultConverter(button -> {
        	if(button == addBtn) {
        		
        		String fullname = (String) nameempCB.getSelectionModel().getSelectedItem();
        		String[] fulname = fullname.split(" ", 2);
        		String v_firstname = fulname[0];
        		String v_lastname = fulname[1];
        		
        		String titleJ = (String) titlejobCB.getSelectionModel().getSelectedItem();

        		// get id job and employer from obl1 obl with title job and first name,last name
        		Employer eee = new Employer();
        		for(Employer ee : obl) {
        			if(ee.getFirstName().equals(v_firstname) && ee.getLastName().equals(v_lastname)) {
        				eee = ee;
        			}
        		}
        		
        		
        		Job jj = new Job();
        		for(Job j : obl1) {
        			if(j.getTitle().equals(titleJ)) {
        				jj = j;
        			}	
        		}
        		
        		
        		
        		return new Affectation(eee.getId(), jj.getId());
        	}
        	return null;
        });
        
        Optional<Affectation> result = dialog.showAndWait();
        result.ifPresent(aff -> {
            
        	//add Job to data base SQL     
        	mysqlconnect.addAffectation(aff, conn);
        	
        	//refresh the table from database
        	obl2 = mysqlconnect.getAffectations(conn);
        	affTable.setItems(obl2);
        	
        });
		
	}

	private void addJobDialog() {
		
     	Dialog<Job> dialog = new Dialog<>();
        dialog.setTitle("Add Job");
        dialog.setHeaderText("Enter the details of the Job");
        dialog.setResizable(false);
        
        ButtonType addBtn = new ButtonType("Add", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addBtn, ButtonType.CANCEL);
        
        Label title = new Label("Title: ");
        TextField titleField = new TextField();
        
        Label description = new Label("Description: ");
        TextArea descriptionArea = new TextArea();
        
        Label price = new Label("Price: ");
        TextField priceField = new TextField();
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, title, titleField);
        grid.addRow(1, description, descriptionArea);
        grid.addRow(2, price, priceField);
        dialog.getDialogPane().setContent(grid);
		
        dialog.setResultConverter(button -> {
        	if(button == addBtn) {
        		return new Job(titleField.getText(), descriptionArea.getText(), Double.parseDouble(priceField.getText()));
        	}
        	return null;
        });
        
        Optional<Job> result = dialog.showAndWait();
        result.ifPresent(job -> {
            
        	//add Job to data base SQL     
        	mysqlconnect.addJob(job, conn);
        	
        	//refresh the table from database
        	obl1 = mysqlconnect.getJobs(conn);
        	jobTable.setItems(obl1);
        	
        });
        
	}

	private void addEmpDialog() {
		
        	Dialog<Employer> dialog = new Dialog<>();
            dialog.setTitle("Add Employer");
            dialog.setHeaderText("Enter the details of the Employer");
            dialog.setResizable(false);
            
            ButtonType addBtn = new ButtonType("Add", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addBtn, ButtonType.CANCEL);
            
            Label firstName = new Label("First Name: ");
            TextField firstNameField = new TextField();
            Label lastName = new Label("Last Name: ");
            TextField lastNameField = new TextField();
            Label phoneName = new Label("Phone: ");
            TextField phoneField = new TextField();
            Label speciality = new Label("Speciality: ");
            TextField specialityField = new TextField();
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.addRow(0, firstName, firstNameField);
            grid.addRow(1, lastName, lastNameField);
            grid.addRow(2, phoneName, phoneField);
            grid.addRow(3, speciality, specialityField);
            dialog.getDialogPane().setContent(grid);
            
            dialog.setResultConverter(button -> {
            	if(button == addBtn) {
            		return new Employer(firstNameField.getText(), lastNameField.getText(), 0, 0, phoneField.getText(), specialityField.getText());
            	}
            	return null;
            	});
            
            Optional<Employer> result = dialog.showAndWait();
            result.ifPresent(emp -> {
                
            	//add Employer to data base SQL     
            	mysqlconnect.addEmployer(emp, conn);
            	
            	//refresh the table from database
            	obl = mysqlconnect.getEmployers(conn);
            	empTable.setItems(obl);
            	
            });	
	}
	
	
	
	

	
	
}
