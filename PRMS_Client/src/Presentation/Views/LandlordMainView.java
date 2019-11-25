package Presentation.Views;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * This class is responsible for the Landlord Main View GUI
 * @author  Gary Wu
 * @since November 25, 2019
 */
public class LandlordMainView extends MainView{

    // Member Variables

    private JButton createListingButton, makePaymentButton, editListingButton;

    /**
     * Constructor for the LandlordMainView and makes
     * the Buttons and sets the panels
     */
    public LandlordMainView(){
        super(200, 200);

        createListingButton = new JButton("Create Listing");
        makePaymentButton = new JButton("Make Payment");
        editListingButton = new JButton("Edit Listing");

        centerPanel.add(createListingButton);
        centerPanel.add(makePaymentButton);
        centerPanel.add(editListingButton);
        addLogoutButton();
    }

    /**
     * adds action listener to the create listing button
     * @param listenForCreateListingButton
     */
    public void addCreateListingListener(ActionListener listenForCreateListingButton){
        createListingButton.addActionListener(listenForCreateListingButton);
    }

    /**
     * adds action listener to the make payment button
     * @param listenForMakePaymentButton
     */
    public void addMakePaymentListener(ActionListener listenForMakePaymentButton){
        makePaymentButton.addActionListener(listenForMakePaymentButton);
    }

    /**
     * adds action listener to the edit listing button
     * @param listenForEditListingButton
     */
    public void addEditListingListener(ActionListener listenForEditListingButton){
        editListingButton.addActionListener(listenForEditListingButton);
    }
}
