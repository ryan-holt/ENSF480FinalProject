package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The AccountCreationView class holds the GUI for the
 * account creation view and its purpose to to provide the
 * view for creating accounts as well as the buttons and
 * different functionality and listeners of them
 * @author Gary Wu
 * @vsince November 25, 2019
 */
public class AccountCreationView extends JFrame{

    //MEMBER VARIABLES
    private JPanel titlePanel, centerPanel, buttonPanel;

    private JButton createAccountButton, backToLoginButton;

    private JComboBox accountTypeList;

    private JTextField usernameField, firstNameField, lastNameField, addressField, emailField;
    private JPasswordField passwordField;

    private final int width = 225;
    private final int height = 325;

    /**
     * This is the constructor for the AccountCreationView
     * which starts by making the JPanels and then
     * adding different aspects such as the title, layout
     * and buttons
     */
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
        emailField = new JTextField(10);

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

        JLabel emailLabel = new JLabel("Email:");
        lastNameLabel.setLabelFor(emailField);
        centerPanel.add(emailLabel);
        centerPanel.add(emailField);

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

    /**
     * Adds an actionlistener for create button
     * @param listenForCreateAccountButton
     */
    public void addCreateAccountListener(ActionListener listenForCreateAccountButton){
        createAccountButton.addActionListener(listenForCreateAccountButton);
    }

    /**
     * Adds an action listener for back to login button
     * @param listenForBackToLoginButton
     */
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

    public JTextField getEmailField() {
        return emailField;
    }
}
