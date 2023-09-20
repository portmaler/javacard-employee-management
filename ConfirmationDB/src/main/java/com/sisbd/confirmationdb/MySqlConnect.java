package com.sisbd.confirmationdb;

import com.sisbd.confirmationdb.models.Job;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class MySqlConnect {

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javacard", "root", "");
            System.out.println("Connection Established");


            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public static ObservableList<Job>  getDataJobs(int employeeId) {
        Connection conn = ConnectDb();
        ObservableList<Job> list = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT j.* FROM job j " +
                    "JOIN affectation a ON j.id = a.idjob " +
                    "JOIN employer e ON e.id = a.idemp " +
                    "WHERE e.id_employer = ?");
            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Job(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("price")
                ));
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

        return list;
    }

    public static boolean deleteAffectationByJobId(int empId, int jobId) {
        Connection conn = ConnectDb();

        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM affectation WHERE idemp = (SELECT id from employer where id_employer = ?) and idjob = ?");
            ps.setInt(1, empId);
            ps.setInt(2, jobId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
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

    public static int  updateEmployeeBalance(int empId, int solde) {
        Connection conn = ConnectDb();
        int previousBalance = 0;
        try {
            // Get the previous balance of the employee
            PreparedStatement selectPs = conn.prepareStatement("SELECT balance_month FROM employer WHERE id_employer = ?");
            selectPs.setInt(1, empId);
            ResultSet rs = selectPs.executeQuery();

            // Update the balance by adding the given solde
            if (rs.next()) {
                previousBalance = rs.getInt("balance_month");
               int newBalance = previousBalance + solde;

                // Update the balance in the employee table
                PreparedStatement updatePs = conn.prepareStatement("UPDATE employer SET balance_month = ? WHERE id_employer = ?");
                updatePs.setDouble(1, newBalance);
                updatePs.setInt(2, empId);
                int affectedRows = updatePs.executeUpdate();

                return newBalance;
            } else {
                // Employee with the specified ID not found
                return 0;
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

    public static void updateJobColumn(int employeeId, String columnName, String newValue) {
        Connection conn = ConnectDb();

        try {
            PreparedStatement updateStatement = conn.prepareStatement("UPDATE job SET  = ? WHERE id_employee = ?");
            updateStatement.setString(1, newValue);
            updateStatement.setInt(2, employeeId);
            updateStatement.executeUpdate();
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

}
