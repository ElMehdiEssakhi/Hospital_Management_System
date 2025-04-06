package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ManagePatientsView extends JPanel {
    private JTable patientTable;
    private JTextField firstnameField,lastNameField, dobField,phoneNumField,fileNumField,searchField;
    private JButton addPatientButton, editPatientButton,deletePatientButton,searchButton,refreshButton;
    public ManagePatientsView(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(230, 240, 255)); // Light Blue Hospital Theme

        JLabel headerLabel = new JLabel("Patient Management", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(new Color(0, 70, 140));
        headerLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Input Panel for Adding Doctors
        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 10, 0));
        inputPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        inputPanel.setBackground(new Color(230, 240, 255));

        firstnameField = createStyledTextField();
        lastNameField = createStyledTextField();
        dobField = createStyledTextField();
        phoneNumField = createStyledTextField();
        fileNumField = createStyledTextField();
        addPatientButton = createStyledButton("Add");

        inputPanel.add(new JLabel("First Name:"));
        inputPanel.add(firstnameField);
        inputPanel.add(new JLabel("Last Name:"));
        inputPanel.add(lastNameField);
        inputPanel.add(new JLabel("Date of Birth:"));
        inputPanel.add(dobField);
        inputPanel.add(new JLabel("Phone Number:"));
        inputPanel.add(phoneNumField);
        inputPanel.add(new JLabel("File Number:"));
        inputPanel.add(fileNumField);
        new JLabel("    ");
        inputPanel.add(addPatientButton);

        // Panel for search functionality
        JPanel datadisplayPanel = new JPanel(new BorderLayout());
        datadisplayPanel.setBackground(new Color(230, 240, 255));

        JPanel searchPanel = new JPanel();
        searchField = createStyledTextField();
        searchField.setPreferredSize(new Dimension(400, 30)); // Adjust size
        searchButton = createStyledButton("Search");
        refreshButton = createStyledButton("Refresh");

        datadisplayPanel.add(new JLabel("Search by File Number:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);

        //table to dispaly

        patientTable = new JTable();
        patientTable.setFont(new Font("SansSerif", Font.PLAIN, 16));
        patientTable.setRowHeight(25);
        patientTable.setSelectionBackground(new Color(180, 210, 255));
        patientTable.setGridColor(Color.LIGHT_GRAY);

        JTableHeader tableHeader = patientTable.getTableHeader();
        tableHeader.setFont(new Font("SansSerif", Font.BOLD, 16));
        tableHeader.setBackground(new Color(0, 70, 140));
        tableHeader.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(patientTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        datadisplayPanel.add(searchPanel, BorderLayout.NORTH);
        datadisplayPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        buttonPanel.setBackground(new Color(230, 240, 255));
        buttonPanel.setBorder(new EmptyBorder(10, 20, 20, 20));

        editPatientButton = createStyledButton("Edit Patient");
        deletePatientButton = createStyledButton("Delete Patient");
        JButton backButton = createStyledButton("Back to Menu");

        buttonPanel.add(editPatientButton);
        buttonPanel.add(deletePatientButton);
        buttonPanel.add(backButton);

        add(headerLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.BEFORE_FIRST_LINE);
        add(datadisplayPanel, BorderLayout.CENTER);
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
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 16)); // Larger font
        textField.setPreferredSize(new Dimension(100, 20)); // Set size
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 200), 2), // Blue border
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding inside the text field
        ));
        textField.setBackground(new Color(245, 250, 255)); // Light blue background
        return textField;
    }
    public JButton getAddPatientButton() {
        return addPatientButton;
    }
    public JButton getEditPatientButton() {
        return editPatientButton;
    }
    public JButton getDeletePatientButton() {
        return deletePatientButton;
    }
    public JTextField getFirstnameField() {
        return firstnameField;
    }
    public JTextField getLastNameField() {
        return lastNameField;
    }
    public JTextField getDobField() {
        return dobField;
    }
    public JTextField getPhoneNumField() {
        return phoneNumField;
    }
    public JTextField getFileNumField() {
        return fileNumField;
    }
    public JTable getTable() {
        return patientTable;
    }
    public int get_id(){
        int selected_row=patientTable.getSelectedRow();
        if(selected_row==-1){
            return -1;
        }
        return (int) patientTable.getValueAt(selected_row, 0);
    }
    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}
