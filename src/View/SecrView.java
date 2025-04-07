package View;

import javax.swing.*;
import java.awt.*;

public class SecrView extends JFrame {
    private JPanel mainPanel;
    private JPanel choicePanel;
    private JButton doctorButton;
    private JButton patientButton;
    private JButton appointmentButton;
    private CardLayout cardLayout;
    private ManageDoctorsView manageDoctorsView;
    private ManagePatientsView managePatientsView;
    private ManageAppView manageAppView;
    public SecrView() {
        setTitle("Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10));

        // Create CardLayout and JPanel to hold multiple views
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Creating welcome panel
        choicePanel = new JPanel(new BorderLayout());
        choicePanel.setBackground(new Color(230, 240, 255)); // Soft hospital blue

        JLabel welcomeLabel = new JLabel("Welcome to Hospital Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(0, 70, 140));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));
        buttonPanel.setBackground(new Color(230, 240, 255));

        doctorButton = createStyledButton("Manage Doctors");
        patientButton = createStyledButton("Manage Patients");
        appointmentButton = createStyledButton("Manage Appointments");

        buttonPanel.add(doctorButton);
        buttonPanel.add(patientButton);
        buttonPanel.add(appointmentButton);

        choicePanel.add(welcomeLabel, BorderLayout.NORTH);
        choicePanel.add(buttonPanel, BorderLayout.CENTER);

        // Creating pages for doctor and patient management
        manageDoctorsView = new ManageDoctorsView(cardLayout, mainPanel);
        managePatientsView = new ManagePatientsView(cardLayout, mainPanel);
        manageAppView = new ManageAppView(cardLayout,mainPanel);
        mainPanel.add(choicePanel, "choice");
        mainPanel.add(manageDoctorsView, "doctor");
        mainPanel.add(managePatientsView, "patient");
        mainPanel.add(manageAppView, "appointment");

        // Button Actions
        doctorButton.addActionListener(e -> cardLayout.show(mainPanel, "doctor"));
        patientButton.addActionListener(e -> cardLayout.show(mainPanel, "patient"));
        appointmentButton.addActionListener(e -> cardLayout.show(mainPanel, "appointment"));

        // Set initial view to the main menu
        cardLayout.show(mainPanel, "choice");

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 100, 200));
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public ManageDoctorsView getDocMView() {
        return manageDoctorsView;
    }
    public ManagePatientsView getPatientView() {return managePatientsView;}
    public ManageAppView getAppView() {return manageAppView;}
}
