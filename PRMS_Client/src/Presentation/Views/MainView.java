package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class is responsible for the Main view GUIs
 * and is essentially the base view which will be
 * extended by the main views for the renters, managers
 * and landlords
 * @author  Gary Wu
 * @since November 25, 2019
 */
public abstract class MainView extends JFrame{

    // Member Variables
    protected JPanel titlePanel, centerPanel;

    protected JButton logoutButton;

    /**
     * The constructor for the main view which
     * makes essentially creates the title and center panel
     * with the login button
     * @param width the width of the view
     * @param height the height of the view
     */
    public MainView(int width, int height){
        titlePanel = new JPanel();
        centerPanel = new JPanel();

        setTitle("Main Menu");
        setSize(width, height);
        setLayout(new BorderLayout());

        add("North", titlePanel);
        add("Center", centerPanel);

        titlePanel.add(new Label("Please select an option:"));

        pack();
        setSize(width, height);
        setLocationRelativeTo(null);

        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * displays the main view
     */
    public void display(){
        setVisible(true);
    }

    /**
     * adds login button to the GUI
     */
    public void addLogoutButton(){
        logoutButton = new JButton("Logout");
        centerPanel.add(logoutButton);
    }

    /**
     * adds action listener to the logout button
     * @param listenForLogoutButton
     */
    public void addLogoutListener(ActionListener listenForLogoutButton){
        logoutButton.addActionListener(listenForLogoutButton);
    }

}
