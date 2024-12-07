package com.mycompany.customermanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerManagementForm extends JFrame {
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private CustomerDAO customerDAO = new CustomerDAO();

    public CustomerManagementForm() {
        setTitle("Customer Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                new CustomerMgtMain().setVisible(true); // Reopen main selection form
                dispose(); // Close the customer management form
            }
        });

        // Initialize table and model
        tableModel = new DefaultTableModel(new String[]{"ID", "First Name", "Last Name", "Email"}, 0);
        customerTable = new JTable(tableModel);
        styleTable(customerTable);
        loadCustomers();

        JButton addButton = createButton("Add");
        JButton editButton = createButton("Edit");
        JButton deleteButton = createButton("Delete");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddCustomerDialog();
                loadCustomers();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editCustomer();
                loadCustomers();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
                loadCustomers();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(220, 220, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(addButton, gbc);
        gbc.gridy++;
        panel.add(editButton, gbc);
        gbc.gridy++;
        panel.add(deleteButton, gbc);

        add(new JScrollPane(customerTable), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Center the frame on screen
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void loadCustomers() {
        tableModel.setRowCount(0);
        List<Customer> customers = customerDAO.getAllCustomers();
        for (Customer customer : customers) {
            tableModel.addRow(new Object[]{customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail()});
        }
    }

    private void showAddCustomerDialog() {
        JTextField firstNameField = new JTextField(10);
        JTextField lastNameField = new JTextField(10);
        JTextField emailField = new JTextField(10);

        JPanel addPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        addPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        addPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        addPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        addPanel.add(emailField, gbc);

        int result = JOptionPane.showConfirmDialog(null, addPanel, "Add New Customer", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();

            if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty()) {
                Customer customer = new Customer();
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setEmail(email);
                customerDAO.addCustomer(customer);
                JOptionPane.showMessageDialog(this, "Customer added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Retrieve selected customer's information
        int customerId = (int) tableModel.getValueAt(selectedRow, 0);
        String currentFirstName = (String) tableModel.getValueAt(selectedRow, 1);
        String currentLastName = (String) tableModel.getValueAt(selectedRow, 2);
        String currentEmail = (String) tableModel.getValueAt(selectedRow, 3);

        // Create input fields pre-filled with current customer details
        JTextField firstNameField = new JTextField(currentFirstName, 10);
        JTextField lastNameField = new JTextField(currentLastName, 10);
        JTextField emailField = new JTextField(currentEmail, 10);

        JPanel editPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        editPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        editPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        editPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        editPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        editPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        editPanel.add(emailField, gbc);

        int result = JOptionPane.showConfirmDialog(null, editPanel, "Edit Customer", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String newFirstName = firstNameField.getText();
            String newLastName = lastNameField.getText();
            String newEmail = emailField.getText();

            if (!newFirstName.isEmpty() && !newLastName.isEmpty() && !newEmail.isEmpty()) {
                // Update customer object and database
                Customer customer = new Customer();
                customer.setId(customerId);
                customer.setFirstName(newFirstName);
                customer.setLastName(newLastName);
                customer.setEmail(newEmail);

                customerDAO.updateCustomer(customer); // Calls update method in DAO
                JOptionPane.showMessageDialog(this, "Customer updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int customerId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this customer?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirmation == JOptionPane.YES_OPTION) {
            customerDAO.deleteCustomer(customerId);
            JOptionPane.showMessageDialog(this, "Customer deleted successfully!");
        }
    }

    private void styleTable(JTable table) {
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
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
        new CustomerManagementForm();
    }
}
