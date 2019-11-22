package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class is responsible for the Search Listing GUI
 * @author  Harsohail Brar
 * @version 1.0.0
 * @since November 10, 2019
 */
public class SearchListingView extends JFrame {

    //MEMBER VARIABLES

    private JPanel titlePanel, centerPanel, buttonPanel;

    private JButton searchForListingsButton, backToMenuButton;

    private JComboBox typeOfHousesList, numOfBedroomsList, numOfBathroomsList, furnishingList, quadrantList;

    private final int width = 275;
    private final int height = 250;

    public SearchListingView(){
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
        addSelectors();

        // Button Panel
        backToMenuButton = new JButton("<");
        searchForListingsButton = new JButton("Search for Listings");
        buttonPanel.add(backToMenuButton);
        buttonPanel.add(searchForListingsButton);

        pack();
        setSize(width, height);
        setLocationRelativeTo(null);

        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * displays the search listing view
     */
    public void display(){
        setVisible(true);
    }

    /**
     * adds all selectors to the center panel
     */
    public void addSelectors(){
        addHouseTypeSelector();
        addNumBedroomsSelector();
        addNumBathroomsSelector();
        addFurnishingSelector();
        addQuadrantSelector();
    }

    /****** Adding all selectors ********/

    public void addHouseTypeSelector(){
        String[] typeOfHouses = {"-", "Apartment", "Attached", "Detached", "Townhouse"};
        typeOfHousesList = createSelector(typeOfHouses, "Type of House:");
        typeOfHousesList.setSelectedIndex(0);
        centerPanel.add(typeOfHousesList);
    }

    public void addNumBedroomsSelector(){
        String[] numOfRooms = {"-", "1", "2", "3", "4", "5"};
        numOfBedroomsList = createSelector(numOfRooms, "Number of Bedrooms:");
        numOfBedroomsList.setSelectedIndex(0);
        centerPanel.add(numOfBedroomsList);
    }

    public void addNumBathroomsSelector(){
        String[] numOfRooms = {"-", "1", "2", "3", "4", "5"};
        numOfBathroomsList = createSelector(numOfRooms, "Number of Bedrooms:");
        numOfBathroomsList.setSelectedIndex(0);
        centerPanel.add(numOfBathroomsList);
    }

    public void addFurnishingSelector(){
        String[] furnishings = {"-", "Furnished", "Unfurnished"};
        furnishingList = createSelector(furnishings, "Furnishing:");
        furnishingList.setSelectedIndex(0);
        centerPanel.add(furnishingList);
    }

    public void addQuadrantSelector(){
        String[] quadrants = {"-", "SW", "NW", "NE", "SE"};
        quadrantList = createSelector(quadrants, "Quadrant:");
        quadrantList.setSelectedIndex(0);
        centerPanel.add(quadrantList);
    }

    /************************************/

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
     * adds action listener to the back to menu button
     * @param listenForBackToMenuButton
     */
    public void addBackToMenuListener(ActionListener listenForBackToMenuButton){
        backToMenuButton.addActionListener(listenForBackToMenuButton);
    }

    /**
     * adds action listener to the search button
     * @param listenForSearchListingButton
     */
    public void addSearchForListingsListener(ActionListener listenForSearchListingButton){
        searchForListingsButton.addActionListener(listenForSearchListingButton);
    }

    // Getters and setters
    public JComboBox getTypeOfHousesList() {
        return typeOfHousesList;
    }

    public JComboBox getNumOfBedroomsList() {
        return numOfBedroomsList;
    }

    public JComboBox getNumOfBathroomsList() {
        return numOfBathroomsList;
    }

    public JComboBox getFurnishingList() {
        return furnishingList;
    }

    public JComboBox getQuadrantList() {
        return quadrantList;
    }
}