package View;

import Model.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageAppView extends JPanel {
    private JTable rendezVousTable;
    private JButton addButton, editButton, deleteButton,backButton;
    private JTextField PatientFileNumField,dateField,timeField;
    private JComboBox<String> doctorComboBox;
    public ManageAppView(CardLayout cardLayout, JPanel mainPanel) {
        JLabel titleLabel = new JLabel("Manage Appointments", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 70, 140));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(1,4,10,10));
        JLabel FileNumLabel = new JLabel("File Number");
        PatientFileNumField = new JTextField(15);
        JLabel doctorLabel = new JLabel("Doctor");
        doctorComboBox = new JComboBox<>();
        JLabel dateLabel = new JLabel("date");
        dateField = new JTextField(15);
        JLabel timeLabel = new JLabel("time");
        timeField = new JTextField(15);
        inputPanel.add(PatientFileNumField);
        inputPanel.add(doctorComboBox);
        inputPanel.add(dateField);
        inputPanel.add(timeField);

        String[] columnNames = {"Patient Name", "Doctor", "Date", "Time", "Status"};

        rendezVousTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(rendezVousTable);

        JPanel buttonPanel = new JPanel();
        addButton = createStyledButton("Add App");
        editButton = createStyledButton("Edit App");
        deleteButton = createStyledButton("Delete App");
        backButton = createStyledButton("Back to Menu");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "choice"));
        add(titleLabel, BorderLayout.NORTH);
        add(inputPanel,BorderLayout.BEFORE_FIRST_LINE);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
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

    public JComboBox getComboBox() {
        return doctorComboBox;
    }
    public JButton getAddButton() {
        return addButton;
    }
    public JButton getEditButton() {
        return editButton;
    }
    public JButton getDeleteButton() {
        return deleteButton;
    }
    public JTextField getPatientFileNumField(){
        return PatientFileNumField;
    }
    public JTextField getDateField(){
        return dateField;
    }
    public JTextField getTimeField(){
        return timeField;
    }
    public JTable getRendezVousTable(){
        return rendezVousTable;
    }
}
