package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class is responsible for the Renter Main View GUI
 * @author  Harsohail Brar
 * @version 1.0.0
 * @since November 10, 2019
 */
public class RenterMainView extends MainView{

    // Member Variables

    private JButton searchListingButton, unsubscribeButton;

    public RenterMainView(){
        super(200, 150);

        searchListingButton = new JButton("Search Listing");
        unsubscribeButton = new JButton("Unsubscribe");

        centerPanel.add(searchListingButton);
        centerPanel.add(unsubscribeButton);
        addLogoutButton();
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
