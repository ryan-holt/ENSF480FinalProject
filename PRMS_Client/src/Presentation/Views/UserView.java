package Presentation.Views;

import Utils.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The mainView class holds the entire GUI after you log in
 * The main purpose of this class is to create the GUI as well as the
 * different buttons
 * @author  Gary Wu
 * @version 4.10.0
 * @since April 5, 2019
 */
public class UserView extends JFrame {

    //MEMBER VARIABLES
    private JPanel titlePanel, centrePanel, buttonPanel;

    private JButton backToMenuButton;

    private DefaultTableModel userTableModel;
    private JScrollPane userScrollPane;
    private JTable userTable;

    private int width = 900;
    private int height = 550;

    /**
     * This is the constructor for the MainView Class
     * which starts by making the GUI by adding JPanel and then adding values
     * to different aspects of the GUI such as button
     */
    public UserView() {
        titlePanel = new JPanel();
        centrePanel = new JPanel();
        buttonPanel = new JPanel();

        backToMenuButton = new JButton("Back To Menu");

        setTitle("User View");
        setSize(width, height);
        setLayout(new BorderLayout());
        add("North", titlePanel);
        add("Center", centrePanel);
        add("South", buttonPanel);

        titlePanel.add(new Label("Users"));

        buttonPanel.add(backToMenuButton);

        pack();
        setSize(width, height);

        setLocationRelativeTo(null);
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Updates table
     */
    public void updateUserTable(ArrayList<User> users){
        updateUserTableModel(users);

        if(centrePanel != null)
            remove(centrePanel);

        centrePanel = new JPanel();
        add("Center", centrePanel);

        userTable = new JTable(userTableModel);
        userTable.setPreferredSize(new Dimension(800, 900));
        userTable.setPreferredScrollableViewportSize(userTable.getPreferredSize());
        userTable.getTableHeader().setReorderingAllowed(false);
        userScrollPane = new JScrollPane(userTable);

        centrePanel.add(userScrollPane, BorderLayout.LINE_END);
        userTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        userTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        userTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        userTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        userTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        userTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        revalidate();
    }

    /**
     * Updates the table model with needed users
     * @param users All the people who have accounts
     */
    public void updateUserTableModel(ArrayList<User> users){
        String[][] data = new String[users.size()][7];
        String[] header = {"Username", "Password", "First Name", "Last Name", "User Type", "Address", "Email"};

        User user;
        for(int i = 0; i < users.size(); i++){
            user = users.get(i);
            data[i][0] = user.getUsername();
            data[i][1] = user.getPassword();
            data[i][2] = user.getName().getFirstName();
            data[i][3] = user.getName().getLastName();
            data[i][4] = user.getUserType();
            data[i][5] = user.getAddress();
            data[i][6] = user.getEmail();
            System.out.println(user.getEmail());
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, header){
            public boolean isCellEditable(int rowIndex, int mColIndex){
                return false;
            }
        };
        setListingTableModel(tableModel);
    }

    /**
     * displays the listing view
     */
    public void display(){
        setVisible(true);
    }

    /**
     * Adds an action listener to the browse button
     * @param listenForBackToMenuButton
     */
    public void addBackToMenuListener(ActionListener listenForBackToMenuButton){
        backToMenuButton.addActionListener(listenForBackToMenuButton);
    }


    // Getters and setters

    public void setListingTableModel(DefaultTableModel listingTableModel) {
        this.userTableModel = listingTableModel;
    }

    public JTable getUserTable() {
        return userTable;
    }

    public DefaultTableModel getListingTableModel() {
        return userTableModel;
    }
}