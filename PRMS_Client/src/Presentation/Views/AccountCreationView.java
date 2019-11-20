package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AccountCreationView extends JFrame{

    //MEMBER VARIABLES
    private JPanel titlePanel, centerPanel, buttonPanel;

    private JButton createAccountButton, backToLoginButton;

    private JComboBox accountTypeList;

    private JTextField usernameField, firstNameField, lastNameField, addressField;
    private JPasswordField passwordField;

    private final int width = 250;
    private final int height = 300;

    public AccountCreationView(){
        titlePanel = new JPanel();
        centerPanel = new JPanel();
        buttonPanel = new JPanel();

        setTitle("Search Listings Window");
        setSize(width, height);
        setLayout(new BorderLayout());

        add("North", titlePanel);
        add("Center", centerPanel);
        add("South", buttonPanel);

        // Title Panel
        titlePanel.add(new Label("Please fill in search criteria:"));

        // Center Panel
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        addressField = new JTextField(10);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setLabelFor(usernameField);
        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setLabelFor(passwordField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setLabelFor(firstNameField);
        centerPanel.add(firstNameLabel);
        centerPanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setLabelFor(lastNameField);
        centerPanel.add(lastNameLabel);
        centerPanel.add(lastNameField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setLabelFor(addressField);
        centerPanel.add(addressLabel);
        centerPanel.add(addressField);

        addAccountTypeSelector();

        // Button Panel
        backToLoginButton = new JButton("<");
        createAccountButton = new JButton("Create Account");
        buttonPanel.add(backToLoginButton);
        buttonPanel.add(createAccountButton);

        pack();
        setSize(width, height);
        setLocationRelativeTo(null);

        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addAccountTypeSelector(){
        String[] typeOfAccounts = {"RegisteredRenter", "Landlord"};
        accountTypeList = createSelector(typeOfAccounts, "Type of Account:");
        accountTypeList.setSelectedIndex(0);
        centerPanel.add(accountTypeList);
    }

    /**
     * Creates a list selector
     * @param list array of string to be displayed
     * @param label title for the selector
     * @return JComboBox or the selector
     */
    public JComboBox createSelector(String[] list, String label){
        centerPanel.add(new JLabel(label));
        return new JComboBox(list);
    }

    /**
     * displays the account creation view
     */
    public void display(){
        setVisible(true);
    }

    public void addCreateAccountListener(ActionListener listenForCreateAccountButton){
        createAccountButton.addActionListener(listenForCreateAccountButton);
    }

    public void addBackToLoginListener(ActionListener listenForBackToLoginButton){
        backToLoginButton.addActionListener(listenForBackToLoginButton);
    }

    // Getters and Setters
    public JComboBox getAccountTypeList() {
        return accountTypeList;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }
}
