package com.sisbd.confirmationdb.controllers;

import com.sisbd.confirmationdb.MySqlConnect;
import com.sisbd.confirmationdb.javacard.EmployeeTask;
import com.sisbd.confirmationdb.javacard.IdEmployeTask;
import com.sisbd.confirmationdb.models.Job;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.sisbd.confirmationdb.MySqlConnect.ConnectDb;

public class ConfirmController implements Initializable {

    @FXML
    private TableColumn<Job, Button> col_action;

    @FXML
    private TableColumn<Job, Integer> col_jobid;

    @FXML
    private TableColumn<Job, Integer> col_jobprice;

    @FXML
    private TableColumn<Job, String> col_jobtitle;

    @FXML
    private Label employeName;

    @FXML
    private Label employePhone;

    @FXML
    private Label employeSpeciality;

    @FXML
    private Label employeSolde;

    @FXML
    private TableView<Job> table_jobs;

    @FXML
    private Label totalJobsLabel;

    @FXML
    private Label totalSoldLabel;


    ObservableList<Job> listM = FXCollections.observableArrayList();;
    int index = -1;
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    private final IdEmployeTask getidtASK = new IdEmployeTask();
    Integer idEmp = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(getidtASK);

        getidtASK.setOnSucceeded(event -> {
            try {
                idEmp = getidtASK.getValue();// Get the idEmp value from the task
                System.out.println("idEmp from idtask: " + idEmp);

                // After getting the idEmp, set it in the line below
            //  listM = MySqlConnect.getDataJobs(idEmp);

                // Rest of the code...
              //  System.out.println(listM.toString());
              //  table_jobs.setItems(listM);



                setEmployeeData(idEmp);
                loadJobsData();
                col_jobid.setCellValueFactory(new PropertyValueFactory<Job, Integer>("id"));
                col_jobtitle.setCellValueFactory(new PropertyValueFactory<Job, String> ("title") );
                col_jobprice.setCellValueFactory(new PropertyValueFactory<Job, Integer> ("price")) ;
                listM = MySqlConnect.getDataJobs(Integer.parseInt(String.valueOf(idEmp)));
                System.out.println(listM.toString());
                table_jobs.setItems(listM);

              /*  // Calculate total number of jobs and total sold
                int totalJobs = listM.size();
                double totalSold = listM.stream().mapToDouble(Job::getPrice).sum();

                // Update labels with totals
                // Update labels with totals
                totalJobsLabel.setText(String.valueOf(totalJobs));
                totalSoldLabel.setText(String.valueOf(totalSold));*/

                col_action.setCellFactory(param -> new TableCell<Job, Button>() {
                    final Button confirmButton = new Button("Confirm");

                    {
                        confirmButton.setOnAction(event -> {
                            Job job = getTableRow().getItem();
                            int jobId = job.getId();
                            int balance = (int) job.getPrice();
                            boolean deleteResult = MySqlConnect.deleteAffectationByJobId(idEmp,jobId);
                            int updateSolde = MySqlConnect.updateEmployeeBalance(idEmp,balance);
                            if(deleteResult){
                                listM.remove(job);
                                System.out.println("job with this id is confirmed  and affectation deleted : " + jobId);
                                setEmployeeData(idEmp);
                                loadJobsData();
                            }else{
                                System.out.println("error deleting the affectation of concerned job: " + jobId);
                            }

                                System.out.println("job with this id is confirmed  and affectation deleted : " + updateSolde);


                        });
                    }

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(confirmButton);
                        }
                    }
                });
                // ...
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



    }

    public void setEmployeeData(int employeeId) {
        Connection conn = ConnectDb();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM employer WHERE id_employer = ?");
            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("firstname");
                String phone = rs.getString("phone");
                String speciality = rs.getString("speciality");
                String balance = rs.getString("balance_month");

                employeName.setText(name);
                employePhone.setText(phone);
                employeSpeciality.setText(speciality);
                employeSolde.setText(balance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close the connection
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadJobsData() {
        listM = MySqlConnect.getDataJobs(Integer.parseInt(String.valueOf(idEmp)));
        System.out.println(listM.toString());
        table_jobs.setItems(listM);

        // Calculate total number of jobs and total sold
        int totalJobs = listM.size();
        double totalSold = listM.stream().mapToDouble(Job::getPrice).sum();

        // Update labels with totals
        totalJobsLabel.setText(String.valueOf(totalJobs));
        totalSoldLabel.setText(String.valueOf(totalSold));
    }



}
