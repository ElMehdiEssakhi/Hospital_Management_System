package Controller;

import Model.AppModel;
import Model.RendezVous;
import View.ManageAppView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ManageAppController implements ActionListener {
    ManageAppView appview;
    AppModel appModel;
    public ManageAppController(ManageAppView view, AppModel model) {
        this.appview = view;
        this.appModel = model;
        appModel.updateAppointmentTable();
        appview.getRendezVousTable().setModel(appModel.getAppointmentModel());
        // Add listener to get the ID using selected index
        appview.getAddButton().addActionListener(this::addApp);
        //To Fill The ComboBox
        for (int row = 0; row < appModel.getDoctorModel().getRowCount(); row++) {
            String name = (String) appModel.getDoctorModel().getValueAt(row, 1); // Name
            appview.getComboBox().addItem(name); // Name
        }
    }
    private int getSelectedDocId() {
        int selectedIndex = appview.getComboBox().getSelectedIndex();
        int id = (int) appModel.getDoctorModel().getValueAt(selectedIndex, 0); // Get ID from same row
        System.out.println("Selected Doctor ID: " + id);
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
                System.out.println("RendezVous added successfully!");
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


    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
