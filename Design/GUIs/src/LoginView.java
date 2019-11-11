import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class is responsible for the Login GUI
 * @author  Harsohail Brar
 * @version 1.0.0
 * @since November 10, 2019
 */
public class LoginView extends JFrame {

    //MEMBER VARIABLES

    private JPanel titlePanel, centrePanel, buttonPanel;

    private JTextField usernameField;
    private JPasswordField passwordFeild;

    private JButton loginButton;

    private final int width = 250;
    private final int height = 175;

    /**
     * Creates a LoginView object
     */
    public LoginView(){
        titlePanel = new JPanel();
        centrePanel = new JPanel();
        buttonPanel = new JPanel();

        loginButton = new JButton("Login");

        setTitle("Login Window");
        setSize(width, height);
        setLayout(new BorderLayout());

        add("North", titlePanel);
        add("Center", centrePanel);
        add("South", buttonPanel);

        // Title Panel
        titlePanel.add(new Label("Please enter username and password:"));

        // Center Panel
        usernameField = new JTextField(10);
        passwordFeild = new JPasswordField(10);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setLabelFor(usernameField);
        centrePanel.add(usernameLabel);
        centrePanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setLabelFor(passwordFeild);
        centrePanel.add(passwordLabel);
        centrePanel.add(passwordFeild);

        // Button Panel
        buttonPanel.add(loginButton);

        pack();
        setSize(width, height);
        setLocationRelativeTo(null);

        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * adds action listener to the login button
     * @param listenForLoginButton
     */
    public void addLoginListener(ActionListener listenForLoginButton){
        loginButton.addActionListener(listenForLoginButton);
    }

    /**
     * displays the login view
     */
    public void display(){
        setVisible(true);
    }


    //GETTERS AND SETTERS
    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getPasswordFeild() {
        return passwordFeild;
    }

}