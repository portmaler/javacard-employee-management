package com.sisbd.admin_gui.connection;

import com.sisbd.admin_gui.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class mysqlconnect {

	//Connection conn = null;
	
	public static Connection ConnectDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/javacard", "root", "");
			//JOptionPane.showMessageDialog(null, "Connection Established");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	public static ObservableList<Employer> getEmployers(Connection conn){	

		ObservableList<Employer> list = FXCollections.observableArrayList();
		
		try {
			PreparedStatement ps = conn.prepareStatement("select * from employer");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Employer(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getDouble("balance"), rs.getDouble("balance_month"), rs.getString("phone"), rs.getString("speciality")));
			}
		} catch (Exception e) {
			
		}
		return list;
	}
	public static void addEmployer(Employer emp, Connection conn) {

		try {
			PreparedStatement ps = conn.prepareStatement("insert into employer (firstname, lastname, phone, speciality) values(?, ?, ?,?)");
			
			ps.setString(1, emp.getFirstName());
			ps.setString(2, emp.getLastName());
			ps.setString(3, emp.getPhone());
			ps.setString(4, emp.getSpeciality());

			ps.execute();
			JOptionPane.showMessageDialog(null, "Emp: "+emp.getFirstName()+"  "+emp.getLastName()+" Add succes");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public static ObservableList<Job> getJobs(Connection conn){
		
		ObservableList<Job> list = FXCollections.observableArrayList();
		
		try {
			PreparedStatement ps = conn.prepareStatement("select * from job");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Job(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getDouble("price")));
			}
		} catch (Exception e) {
			
		}
		return list;
		
	}
	public static ObservableList<Affectation> getAffectations(Connection conn){
		
		ObservableList<Affectation> list = FXCollections.observableArrayList();
		
		try {
			PreparedStatement ps = conn.prepareStatement("select a.id, a.idjob, a.idemp, firstname, lastname, title from affectation as a join employer as e on e.id=a.idemp join job as j on j.id=a.idjob");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Affectation(rs.getInt("id"), rs.getInt("idemp"), rs.getInt("idjob"), rs.getString("firstname")+""+rs.getString("lastname"), rs.getString("title")));
			}
		} catch (Exception e) {
			
		}
		return list;
		
	}
	
	public static void addJob(Job job, Connection conn) {
		
		try {
			PreparedStatement ps = conn.prepareStatement("insert into job (title, description, price) values(?, ?, ?)");
			
			ps.setString(1, job.getTitle());
			ps.setString(2, job.getDescription());
			ps.setDouble(3, job.getPrice());
			

			ps.execute();
			JOptionPane.showMessageDialog(null, "Job: "+job.getTitle()+" Add succes");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void addAffectation(Affectation aff, Connection conn) {
		
		try {
			PreparedStatement ps = conn.prepareStatement("insert into affectation (idjob, idemp) values(?, ?)");
			
			ps.setInt(1, aff.getIdJob());
			ps.setInt(2, aff.getIdEmp());
			
			ps.execute();
			
			JOptionPane.showMessageDialog(null, "Affectation:  Add succes");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
