package Controller;

import Model.AppModel;
import Model.RendezVous;
import View.ManageAppView;
import View.SecrView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ManageAppController implements ActionListener {
    ManageAppView appview;
    AppModel appModel;
    SecrView secrView;
    public ManageAppController(SecrView secrview,ManageAppView view, AppModel model) {
        this.appview = view;
        this.appModel = model;
        this.secrView = secrview;
        appModel.updateAppointmentTable();
        appview.getRendezVousTable().setModel(appModel.getAppointmentModel());
        //refresh when navigating to appointment view
        secrview.getAppointmentButton().addActionListener(this::refresh);
        // Add listener to get the ID using selected index
        appview.getAddButton().addActionListener(this::addApp);
        appview.getEditButton().addActionListener(this::editApp);
        appview.getDeleteButton().addActionListener(this::deleteApp);
        appview.getRendezVousTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                show_up(e);
            }
        });
        //To Fill The ComboBox
        for (int row = 0; row < appModel.getDoctorModel().getRowCount(); row++) {
            String name = (String) appModel.getDoctorModel().getValueAt(row, 1); // Name
            appview.getComboBox().addItem(name); // Name
        }
    }
    private int getSelectedDocId() {
        int selectedIndex = appview.getComboBox().getSelectedIndex();
        int id = (int) appModel.getDoctorModel().getValueAt(selectedIndex, 0); // Get ID from same row
        return id;
    }
    private void addApp(ActionEvent e) {
        JTextField fileNumField = appview.getPatientFileNumField();
        int DocId = getSelectedDocId();
        JTextField dateField = appview.getDateField();
        JTextField timeField = appview.getTimeField();
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(!fileNumField.getText().isEmpty() && !dateField.getText().isEmpty() && !timeField.getText().isEmpty()) {
            try{
                java.util.Date utilDate = sdf.parse(dateField.getText());
                date=new java.sql.Date(utilDate.getTime());
                appModel.addRendezVous(new RendezVous(fileNumField.getText(), DocId,date, Time.valueOf(timeField.getText())));
                fileNumField.setText("");
                dateField.setText("");
                timeField.setText("");
            }catch (ParseException ex) {
                JOptionPane.showMessageDialog(appview, "Invalid Date! Use format yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(appview, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void editApp(ActionEvent e){
        int selected = appview.get_id();
        if ( selected == -1 ) {
            JOptionPane.showMessageDialog(appview, "Please select doctor", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
            JTextField fileNumField = appview.getPatientFileNumField();
            int DocId = getSelectedDocId();
            JTextField dateField = appview.getDateField();
            JTextField timeField = appview.getTimeField();
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(!fileNumField.getText().isEmpty() && !dateField.getText().isEmpty() && !timeField.getText().isEmpty()) {
                try{
                    java.util.Date utilDate = sdf.parse(dateField.getText());
                    date=new java.sql.Date(utilDate.getTime());
                    appModel.editRendezVous(new RendezVous(selected,fileNumField.getText(), DocId,date, Time.valueOf(timeField.getText())));
                    fileNumField.setText("");
                    dateField.setText("");
                    timeField.setText("");
                }catch (ParseException ex) {
                    JOptionPane.showMessageDialog(appview, "Invalid Date! Use format yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(appview, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void deleteApp(ActionEvent e) {
        int selected = appview.get_id();
        if ( selected == -1 ) {
            JOptionPane.showMessageDialog(appview, "Please select doctor", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            appModel.deleteRendezVous(selected);
        }
    }
    private void show_up(MouseEvent mouseEvent) {
        int selected = appview.getRendezVousTable().getSelectedRow();
        JTextField fileNumField = appview.getPatientFileNumField();
        JComboBox DoctorList = appview.getComboBox();
        JTextField dateField = appview.getDateField();
        JTextField timeField = appview.getTimeField();
        fileNumField.setText(appview.getRendezVousTable().getValueAt(selected, 1).toString());
        DoctorList.setSelectedItem(appview.getRendezVousTable().getValueAt(selected, 3).toString());
        dateField.setText(appview.getRendezVousTable().getValueAt(selected, 4).toString());
        timeField.setText(appview.getRendezVousTable().getValueAt(selected, 5).toString());
    }
    public void refresh(ActionEvent e) {
        appModel.updateAppointmentTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
