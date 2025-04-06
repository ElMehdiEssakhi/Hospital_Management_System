package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.EmptyBorder;

public class ManageDoctorsView extends JPanel {
    private JTable doctorTable;
    private JTextField nameField, specialtyField,schedualField;
    private JButton addDoctorButton,editButton,deleteButton;
    public ManageDoctorsView(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(230, 240, 255)); // Light Blue Hospital Theme

        JLabel headerLabel = new JLabel("Doctor Management", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(new Color(0, 70, 140));
        headerLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Input Panel for Adding Doctors
        JPanel inputPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        inputPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        inputPanel.setBackground(new Color(230, 240, 255));

        nameField = new JTextField();
        specialtyField = new JTextField();
        schedualField = new JTextField();
        addDoctorButton = createStyledButton("Add");

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Specialty:"));
        inputPanel.add(specialtyField);
        inputPanel.add(new JLabel("Schedual:"));
        inputPanel.add(schedualField);
        inputPanel.add(addDoctorButton);

        doctorTable = new JTable();
        doctorTable.setFont(new Font("SansSerif", Font.PLAIN, 16));
        doctorTable.setRowHeight(25);
        doctorTable.setSelectionBackground(new Color(180, 210, 255));
        doctorTable.setGridColor(Color.LIGHT_GRAY);

        JTableHeader tableHeader = doctorTable.getTableHeader();
        tableHeader.setFont(new Font("SansSerif", Font.BOLD, 16));
        tableHeader.setBackground(new Color(0, 70, 140));
        tableHeader.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(doctorTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel buttonPanel = new JPanel(new GridLayout(1,3 , 10, 0));
        buttonPanel.setBackground(new Color(230, 240, 255));
        buttonPanel.setBorder(new EmptyBorder(10, 20, 20, 20));

        editButton = createStyledButton("Edit Doctor");
        deleteButton = createStyledButton("Delete Doctor");
        JButton backButton = createStyledButton("Back to Menu");

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        add(headerLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.BEFORE_FIRST_LINE);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "choice"));
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 100, 200));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    public JButton getAddDoctorButton() {
        return addDoctorButton;
    }
    public JButton getEditButton() {
        return editButton;
    }
    public JButton getDeleteButton() {
        return deleteButton;
    }
    public JTextField getNameField() {
        return nameField;
    }
    public JTextField getSpecialtyField() {
        return specialtyField;
    }
    public JTextField getSchedualField() {
        return schedualField;
    }
    public JTable getTable() {
        return doctorTable;
    }
    public int get_id(){
        int selected_row=doctorTable.getSelectedRow();
        if(selected_row==-1){
            return -1;
        }
        return (int) doctorTable.getValueAt(selected_row, 0);
    }
}