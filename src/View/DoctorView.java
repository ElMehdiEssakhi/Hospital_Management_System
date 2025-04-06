package View;

import javax.swing.*;
import java.awt.*;

public class DoctorView extends JFrame {
    private JTable appointmentsTable;
    private JButton logoutButton;
    private JTextField doctorNameField;
    private JButton addDoctorButton;

    public DoctorView() {
        setTitle("Doctor - Appointments");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Doctor's Appointments", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 70, 140));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new FlowLayout());
        doctorNameField = new JTextField(20);
        addDoctorButton = new JButton("Add Doctor");
        topPanel.add(new JLabel("Doctor Name: "));
        topPanel.add(doctorNameField);
        topPanel.add(addDoctorButton);

        String[] columnNames = {"Patient Name", "Date", "Time", "Status"};
        Object[][] data = {
                {"John Doe", "2025-04-01", "10:00 AM", "Scheduled"},
                {"Jane Smith", "2025-04-02", "11:30 AM", "Completed"},
                {"Mike Brown", "2025-04-03", "01:00 PM", "Canceled"}
        };

        appointmentsTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(appointmentsTable);

        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(200, 0, 0));
        logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        logoutButton.setFocusPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(logoutButton);

        add(titleLabel, BorderLayout.NORTH);
        add(topPanel, BorderLayout.BEFORE_FIRST_LINE);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
