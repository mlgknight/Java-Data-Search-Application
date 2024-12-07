package com.mycompany.customermanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerMgtMain extends JFrame {
    public CustomerMgtMain() {
        setTitle("Main Selection Form");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons without icons
        JButton customerButton = createButton("Customer Management");
        JButton orderButton = createButton("Order Management");
        JButton productButton = createButton("Product Management");
        JButton employeeButton = createButton("Employee Management");
        JButton reportButton = createButton("Reports");
        JButton settingsButton = createButton("Settings");

        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CustomerManagementForm().setVisible(true); // Open Customer Management form
                dispose(); // Close the main selection form
            }
        });

        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Order Management is under construction.");
            }
        });

        productButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Product Management is under construction.");
            }
        });

        employeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Employee Management is under construction.");
            }
        });

        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Reports are under construction.");
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Settings are under construction.");
            }
        });

        // Create a panel with GridBagLayout for better control of component placement
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(customerButton, gbc);
        gbc.gridy++;
        panel.add(orderButton, gbc);
        gbc.gridy++;
        panel.add(productButton, gbc);
        gbc.gridy++;
        panel.add(employeeButton, gbc);
        gbc.gridy++;
        panel.add(reportButton, gbc);
        gbc.gridy++;
        panel.add(settingsButton, gbc);

        // Set a background color for the panel
        panel.setBackground(new Color(220, 220, 220));

        // Add panel to the frame
        add(panel);

        // Center the frame on screen
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        button.setBackground(new Color(70, 130, 180)); // Background color
        button.setForeground(Color.WHITE); // Text color
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        return button;
    }

    public static void main(String[] args) {
        new CustomerMgtMain();
    }
}
