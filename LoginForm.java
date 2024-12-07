package com.mycompany.customermanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;

    public LoginForm() {
        setTitle("Admin Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userField = new JTextField(15);
        passField = new JPasswordField(15);
        loginButton = createButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                String pass = new String(passField.getPassword());
                if (user.equals("root") && pass.equals("2100")) {
                    new CustomerMgtMain().setVisible(true); // Navigate to main selection form
                    dispose(); // Close the login form
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login!");
                }
            }
        });

        addComponents();

        // Center the frame on screen
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void addComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(220, 220, 220)); // Match background color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding

        // Username label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userField, gbc);

        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passField, gbc);

        // Login button
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        add(panel);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180)); // Background color
        button.setForeground(Color.WHITE); // Text color
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginForm();
            }
        });
    }
}
