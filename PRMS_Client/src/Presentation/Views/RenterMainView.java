package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class is responsible for the Renter Main View GUI
 * and is what the renter sees when they log in, also it
 * extends the main view
 * @author  Gary Wu
 * @since November 25, 2019
 */
public class RenterMainView extends MainView{

    // Member Variables

    private JButton searchListingButton, unsubscribeButton;

    /**
     * Constructor for the RenterMainView which creates the
     * different buttons for actions that the renter will have
     * access to.  Also it checks if they are a registered renter
     * and if so they will also have the unsubscribe button
     * @param registeredRenter tells us if they are a registered renter
     */
    public RenterMainView(boolean registeredRenter){
        super(200, 150);

        searchListingButton = new JButton("Search Listing");
        unsubscribeButton = new JButton("Unsubscribe");

        if(!registeredRenter){
            unsubscribeButton.setEnabled(false);
        }

        centerPanel.add(searchListingButton);
        centerPanel.add(unsubscribeButton);
        addLogoutButton();

        setVisible(false);
    }

    /**
     * adds action listener to the search listing button
     * @param listenForSearchListingButton
     */
    public void addSearchListingListener(ActionListener listenForSearchListingButton){
        searchListingButton.addActionListener(listenForSearchListingButton);
    }

    /**
     * adds action listener to the unsubscribe button
     * @param listenForUnsubscribeButton
     */
    public void addUnsubscribeListener(ActionListener listenForUnsubscribeButton){
        unsubscribeButton.addActionListener(listenForUnsubscribeButton);
    }
}
