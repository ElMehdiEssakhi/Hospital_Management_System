package Controller;

import Model.AppModel;
import Model.Patient;
import View.ManagePatientsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;


public class ManagePatientsController implements ActionListener {
    private ManagePatientsView managePatientsView;
    private AppModel appModel;
    public ManagePatientsController( ManagePatientsView PatView,AppModel appModel) {
        this.appModel=appModel;
        this.managePatientsView= PatView;
        appModel.updatePatientTable();
        PatView.getAddPatientButton().addActionListener(this::add);
        PatView.getEditPatientButton().addActionListener(this::edit);
        PatView.getDeletePatientButton().addActionListener(this::delete);
        PatView.getSearchButton().addActionListener(this::search);
        PatView.getRefreshButton().addActionListener(this::refresh);
        PatView.getTable().setModel(appModel.getPatientModel());
        PatView.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                show_up(e);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    private void add(ActionEvent e)  {
        // Action Listener for Adding Doctor
        JTextField firstnameField = managePatientsView.getFirstnameField();
        JTextField lastnameField = managePatientsView.getLastNameField();
        JTextField dobField = managePatientsView.getDobField();
        JTextField phoneNumField = managePatientsView.getPhoneNumField();
        JTextField fileNumber = managePatientsView.getFileNumField();
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        if ( !firstnameField.getText().isEmpty() && !lastnameField.getText().isEmpty() && !dobField.getText().isEmpty() && !phoneNumField.getText().isEmpty() && !fileNumber.getText().isEmpty() ) {
            try{
                java.util.Date utilDate = sdf.parse(dobField.getText());
                date=new java.sql.Date(utilDate.getTime());
            }catch (ParseException ex) {
                JOptionPane.showMessageDialog(managePatientsView, "Invalid Date! Use format yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
            }
            appModel.addPatient(new Patient(firstnameField.getText(),lastnameField.getText(), date,phoneNumField.getText(),fileNumber.getText()));
            firstnameField.setText("");
            lastnameField.setText("");
            dobField.setText("");
            phoneNumField.setText("");
            fileNumber.setText("");
        } else {
            JOptionPane.showMessageDialog(managePatientsView, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void edit(ActionEvent e) {
        int selected = managePatientsView.get_id();
        if ( selected == -1 ) {
            JOptionPane.showMessageDialog(managePatientsView, "Please select doctor", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
            JTextField firstnameField = managePatientsView.getFirstnameField();
            JTextField lastnameField = managePatientsView.getLastNameField();
            JTextField dobField = managePatientsView.getDobField();
            JTextField phoneNumField = managePatientsView.getPhoneNumField();
            JTextField fileNumber = managePatientsView.getFileNumField();
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            if ( !firstnameField.getText().isEmpty() && !lastnameField.getText().isEmpty() && !dobField.getText().isEmpty() && !phoneNumField.getText().isEmpty() && !fileNumber.getText().isEmpty() ) {
                try{
                    java.util.Date utilDate = sdf.parse(dobField.getText());
                    date=new java.sql.Date(utilDate.getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(managePatientsView, "Invalid Date! Use format yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
                }
                appModel.editPatient(new Patient(selected,firstnameField.getText(),lastnameField.getText(), date,phoneNumField.getText(),fileNumber.getText()));
                firstnameField.setText("");
                lastnameField.setText("");
                dobField.setText("");
                phoneNumField.setText("");
                fileNumber.setText("");
            } else {
                JOptionPane.showMessageDialog(managePatientsView, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void delete(ActionEvent e) {
        int selected = managePatientsView.get_id();
        if ( selected == -1 ) {
            JOptionPane.showMessageDialog(managePatientsView, "Please select Patient", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
            appModel.deletePatient(selected);
        }
    }
    private void search(ActionEvent e){
        String fileNum = managePatientsView.getSearchField().getText();
        if (Objects.equals(fileNum, "")) {
            JOptionPane.showMessageDialog(managePatientsView, "Please enter a File Number", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            appModel.searchPatient(fileNum);
        }
    }
    private void refresh(ActionEvent e) {
        appModel.updatePatientTable();
    }
    private void show_up(MouseEvent mouseEvent) {
        int selected = managePatientsView.getTable().getSelectedRow();
        JTextField firstnameField = managePatientsView.getFirstnameField();
        JTextField lastnameField = managePatientsView.getLastNameField();
        JTextField dobField = managePatientsView.getDobField();
        JTextField phoneNumField = managePatientsView.getPhoneNumField();
        JTextField fileNumber = managePatientsView.getFileNumField();
        firstnameField.setText(managePatientsView.getTable().getValueAt(selected, 1).toString());
        lastnameField.setText(managePatientsView.getTable().getValueAt(selected, 2).toString());
        dobField.setText(managePatientsView.getTable().getValueAt(selected, 3).toString());
        phoneNumField.setText(managePatientsView.getTable().getValueAt(selected, 4).toString());
        fileNumber.setText(managePatientsView.getTable().getValueAt(selected, 5).toString());

    }
}
