package View;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;

public class RVousView extends JPanel {
    private JTable rendezVousTable;
    private JButton addButton, editButton, deleteButton, logoutButton;
    public RVousView(CardLayout cardLayout, JPanel mainPanel) {
        JLabel titleLabel = new JLabel("Manage Appointments", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 70, 140));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Patient Name", "Doctor", "Date", "Time", "Status"};
        Object[][] data = {
                {"John Doe", "Dr. Smith", "2025-04-01", "10:00 AM", "Scheduled"},
                {"Jane Smith", "Dr. Adams", "2025-04-02", "11:30 AM", "Completed"},
                {"Mike Brown", "Dr. Lee", "2025-04-03", "01:00 PM", "Canceled"}
        };

        rendezVousTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(rendezVousTable);

        JPanel buttonPanel = new JPanel();
        JButton addButton = createStyledButton("Add App");
        JButton editButton = createStyledButton("Edit App");
        JButton deleteButton = createStyledButton("Delete App");
        JButton backButton = createStyledButton("Back to Menu");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "choice"));
        add(titleLabel, BorderLayout.NORTH);
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
}
