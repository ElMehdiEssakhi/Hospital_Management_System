package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class AppModel {
    private Connection conn;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private DefaultTableModel doctorModel,patientModel;
    public AppModel() {
        try{
            String url = "jdbc:mysql://localhost:3306/HospitalDB";
            String user = "root";
            String password = "root";
            conn= DriverManager.getConnection(url,user,password);
            System.out.println("connection");
        }catch (Exception e){
            System.err.println("echec de la connexion" + e.getMessage());
        }
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
        String[] Docolumns = {"id","name","specialty","schedual"};
        doctorModel = new DefaultTableModel(Docolumns,0);
        String[] Patcolumns = {"id","FirstName","LastName","Date of Birth","Phone Number","File Number"};
        patientModel= new DefaultTableModel(Patcolumns,0);
    }
    public void addPatient(Patient patient) {
        String sql = "insert into patient (Firstname,Lastname,dob,phone,file_number) values(?,?,?,?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2,patient.getLastName());
            stmt.setDate(3,patient.getDateOfBirth());
            stmt.setString(4,patient.getPhoneNumber());
            stmt.setString(5,patient.getFileNum());
            stmt.executeUpdate();
            System.err.println("Patient added successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to add patient" + e.getMessage());
        }
        updatePatientTable();
    }
    public void editPatient(Patient patient) {
        String sql = "UPDATE patient SET Firstname=?, Lastname=?, dob=?, phone=?, file_number=? WHERE id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setDate(3, patient.getDateOfBirth());
            stmt.setString(4, patient.getPhoneNumber());
            stmt.setString(5, patient.getFileNum());
            stmt.setInt(6, patient.getId());  // WHERE condition needs id
            stmt.executeUpdate();
            System.out.println("Patient updated successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to update patient: " + e.getMessage());
        }
        updatePatientTable();
    }
    public void deletePatient(int patientId) {
        String sql = "DELETE FROM patient WHERE id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
            System.out.println("Patient deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to delete patient: " + e.getMessage());
        }
        updatePatientTable();
    }
    public void addDoctor(Doctor doctor) {
        String sql = "insert into doctor (name,specialty,schedule) values(?,?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,doctor.getDoctorName());
            stmt.setString(2,doctor.getSpeciality());
            stmt.setString(3,doctor.getSchedule());
            stmt.executeUpdate();
            System.out.println("Doctor added successfully!");
        }catch (SQLException e){
            System.err.println("❌ Failed to add doctor: " + e.getMessage());
        }
        updateDoctorTable();
    }
    public void editDoctor(Doctor doctor) {
        String sql = "UPDATE doctor SET name=?, specialty=?, schedule=? WHERE id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, doctor.getDoctorName());
            stmt.setString(2, doctor.getSpeciality());
            stmt.setString(3, doctor.getSchedule());
            stmt.setInt(4, doctor.getId());  // ID needed in WHERE clause
            stmt.executeUpdate();
            System.out.println("Doctor updated successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Failed to update doctor: " + e.getMessage());
        }
        updateDoctorTable();
    }
    public void deleteDoctor(int doctorId) {
        String sql = "DELETE FROM doctor WHERE id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, doctorId);
            stmt.executeUpdate();
            System.out.println("Doctor deleted successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Failed to delete doctor: " + e.getMessage());
        }
        updateDoctorTable();
    }
    public void addRendezVous(RendezVous rendezVous) {
        String sql = "insert into appointment (patient_id,doctor_id,date) values(?,?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,rendezVous.getPatientId());
            stmt.setInt(2,rendezVous.getDoctorId());
            stmt.setDate(3,rendezVous.getDate());
            stmt.executeUpdate();
            System.out.println("Rendez Vous added successfully!");
        }catch (SQLException e){
            System.err.println("❌ Failed to add rendezvous: " + e.getMessage());
        }
    }
    public void editRendezVous(RendezVous rendezVous) {
        String sql = "UPDATE appointment SET patient_id=?, doctor_id=?, date=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rendezVous.getPatientId());
            stmt.setInt(2, rendezVous.getDoctorId());
            stmt.setDate(3, rendezVous.getDate());
            stmt.setInt(4, rendezVous.getId());  // ID needed for WHERE condition
            stmt.executeUpdate();
            System.out.println("✅ Rendezvous updated successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Failed to update rendezvous: " + e.getMessage());
        }

    }
    public void deleteRendezVous(int rendezVousId) {
        String sql = "DELETE FROM appointment WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rendezVousId);
            stmt.executeUpdate();
            System.out.println("✅ Rendezvous deleted successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Failed to delete rendezvous: " + e.getMessage());
        }
    }
    public String authenticate(String name, String password) {
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,name);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {return rs.getString("role") ;}
            return "none";
        } catch (SQLException e) {
            System.err.println("Sql Error: " + e.getMessage());
            return null;
        }
    }
    public void updateDoctorTable() {
        doctorModel.setRowCount(0);
        String sql = "SELECT * FROM doctor";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()) {
                doctorModel.addRow(new Object[]{rs.getInt("id"),rs.getString("name"),rs.getString("specialty"),rs.getString("schedule")});
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
    public void updatePatientTable() {
        patientModel.setRowCount(0);
        String sql = "SELECT * FROM patient";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()) {
                patientModel.addRow(new Object[]{rs.getInt("id"),rs.getString("FirstName"),rs.getString("LastName"),rs.getDate("dob"),rs.getString("phone"),rs.getString("file_number")});
            }
        } catch (Exception e) {
            System.err.println("Sql Error: " + e.getMessage());
        }
    }
    public DefaultTableModel getDoctorModel() {
        return this.doctorModel;
    }
    public DefaultTableModel getPatientModel() {
        return this.patientModel;
    }
    public void searchPatient(String fileNum) {
        String sql = "SELECT * FROM patient where file_number=?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,fileNum);
            ResultSet rs= stmt.executeQuery();
            // Check if the ResultSet is not empty before entering the loop
            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                patientModel.setRowCount(0);
                patientModel.addRow(new Object[]{rs.getInt("id"),rs.getString("FirstName"),rs.getString("LastName"),rs.getDate("dob"),rs.getString("phone"),rs.getString("file_number")});
            }
            if (!hasResults) {
                JOptionPane.showMessageDialog(null, "No patient found with file number: " + fileNum,"Search Result", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (Exception e) {
            System.err.println("Sql Error: " + e.getMessage());
        }
    }

}
