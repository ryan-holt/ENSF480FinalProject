package Presentation.Views;

import Utils.Listing;

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
public class ListingView extends JFrame {

    //MEMBER VARIABLES
    private JPanel titlePanel, centrePanel, buttonPanel;

    private JButton backToMenuButton, emailLandlordButton, editListingButton, makePaymentButton;

    private DefaultTableModel listingTableModel;
    private JScrollPane listingScrollPane;
    private JTable listingTable;

    private int width = 900;
    private int height = 550;

    /**
     * This is the constructor for the MainView Class
     * which starts by making the GUI by adding JPanel and then adding values
     * to different aspects of the GUI such as buttons
     *
     * @param width  width of GUI
     * @param height height of GUI
     */
    public ListingView() {
        titlePanel = new JPanel();
        centrePanel = new JPanel();
        buttonPanel = new JPanel();

        backToMenuButton = new JButton("Back To Menu");
        emailLandlordButton = new JButton("Email Landlord");
        editListingButton = new JButton("Edit Listing");
        makePaymentButton = new JButton("Make Payment");

        setTitle("Main Window");
        setSize(width, height);
        setLayout(new BorderLayout());
        add("North", titlePanel);
        add("Center", centrePanel);
        add("South", buttonPanel);

        titlePanel.add(new Label("Listings"));

        buttonPanel.add(backToMenuButton);
        buttonPanel.add(emailLandlordButton);

        pack();
        setSize(width, height);

        setLocationRelativeTo(null);
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Updates table
     */
    public void updateListingTable(ArrayList<Listing> listings){
        updateListingTableModel(listings);

        if(centrePanel != null)
            remove(centrePanel);

        centrePanel = new JPanel();
        add("Center", centrePanel);

        listingTable = new JTable(listingTableModel);
        listingTable.getTableHeader().setReorderingAllowed(false);
        listingScrollPane = new JScrollPane(listingTable);

        centrePanel.add(listingScrollPane, BorderLayout.LINE_END);
        revalidate();
    }

    public void updateListingTableModel(ArrayList<Listing> listings){
        String[][] data = new String[listings.size()][6];
        String[] header = {"Type", "Bedrooms", "Bathrooms", "Quadrant", "Furnished", "Landlord Email"};

        Listing listing;
        for(int i = 0; i < listings.size(); i++){
            listing = listings.get(i);
            data[i][0] = listing.getType();
            data[i][1] = String.valueOf(listing.getNumOfBedrooms());
            data[i][2] = String.valueOf(listing.getNumOfBathrooms());
            data[i][3] = listing.getQuadrant();
            if(listing.isFurnished()){
                data[i][4] = "Yes";
            }else{
                data[i][4] = "No";
            }
            data[i][5] = listing.getLandlordEmail();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, header){
            public boolean isCellEditable(int rowIndex, int mColIndex){
                return false;
            }
        };
        setListingTableModel(tableModel);
    }

    public void updateListingViewForLandlord(){
        buttonPanel.remove(emailLandlordButton);
        buttonPanel.add(editListingButton);
        buttonPanel.add(makePaymentButton);
        revalidate();
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

    /**
     * Adds an action listener to the email landlord button
     * @param listenForEmailLandlordButton
     */
    public void addEmailLandlordListener(ActionListener listenForEmailLandlordButton){
        emailLandlordButton.addActionListener(listenForEmailLandlordButton);
    }


    // Getters and setters

    public void setListingTableModel(DefaultTableModel listingTableModel) {
        this.listingTableModel = listingTableModel;
    }

    public JTable getListingTable() {
        return listingTable;
    }
}