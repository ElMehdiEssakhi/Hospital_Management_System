package Controller;

import Model.AppModel;
import Model.Doctor;
import View.ManageDoctorsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageDoctorsController implements ActionListener {
    ManageDoctorsView manageDoctorsView;
    AppModel appModel;
    public ManageDoctorsController(ManageDoctorsView view, AppModel model) {
        this.manageDoctorsView = view;
        this.appModel = model;
        appModel.updateDoctorTable();
        manageDoctorsView.getAddDoctorButton().addActionListener(this::add);
        manageDoctorsView.getEditButton().addActionListener(this::edit);
        manageDoctorsView.getDeleteButton().addActionListener(this::delete);
        manageDoctorsView.getTable().setModel(appModel.getDoctorModel());
        manageDoctorsView.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                show_up(e);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    private void add(ActionEvent e) {
        // Action Listener for Adding Doctor
            JTextField name = manageDoctorsView.getNameField();
            JTextField specialty = manageDoctorsView.getSpecialtyField();
            JTextField schedule = manageDoctorsView.getSchedualField();

            if ( !name.getText().isEmpty() && !specialty.getText().isEmpty() && !schedule.getText().isEmpty() ) {
                appModel.addDoctor(new Doctor(name.getText(),specialty.getText(), schedule.getText()));
                name.setText("");
                specialty.setText("");
                schedule.setText("");
            } else {
                JOptionPane.showMessageDialog(manageDoctorsView, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
    private void edit(ActionEvent e) {
        int selected = manageDoctorsView.get_id();
        if ( selected == -1 ) {
            JOptionPane.showMessageDialog(manageDoctorsView, "Please select doctor", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
            JTextField name = manageDoctorsView.getNameField();
            JTextField specialty = manageDoctorsView.getSpecialtyField();
            JTextField schedule = manageDoctorsView.getSchedualField();
            if (!name.getText().isEmpty() && !specialty.getText().isEmpty() && !schedule.getText().isEmpty()) {
                appModel.editDoctor(new Doctor(selected, name.getText(), specialty.getText(), schedule.getText()));
                name.setText("");
                specialty.setText("");
                schedule.setText("");
            } else {
                JOptionPane.showMessageDialog(manageDoctorsView, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void delete(ActionEvent e) {
        int selected = manageDoctorsView.get_id();
        if ( selected == -1 ) {
            JOptionPane.showMessageDialog(manageDoctorsView, "Please select doctor", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
            appModel.deleteDoctor(selected);
        }
    }
    private void show_up(MouseEvent mouseEvent) {
        int selected = manageDoctorsView.getTable().getSelectedRow();
        JTextField name = manageDoctorsView.getNameField();
        JTextField specialty = manageDoctorsView.getSpecialtyField();
        JTextField schedule = manageDoctorsView.getSchedualField();
        name.setText(manageDoctorsView.getTable().getValueAt(selected, 1).toString());
        specialty.setText(manageDoctorsView.getTable().getValueAt(selected, 2).toString());
        schedule.setText(manageDoctorsView.getTable().getValueAt(selected, 3).toString());
    }
}
