package Presentation.Views;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * This class is responsible for the Manager Main View GUI
 * @author  Harsohail Brar
 * @version 1.0.0
 * @since November 10, 2019
 */
public class ManagerMainView extends MainView{

    // Member Variables

    private JButton changeFeeButton, getReportButton, getEntityInfoButton, editListingStateButton;

    public ManagerMainView(){
        super(200, 250);

        changeFeeButton = new JButton("Change Fee");
        getReportButton = new JButton("Get Report");
        getEntityInfoButton = new JButton("Get Entity Info");
        editListingStateButton = new JButton("Edit Listing State");

        centerPanel.add(changeFeeButton);
        centerPanel.add(getReportButton);
        centerPanel.add(getEntityInfoButton);
        centerPanel.add(editListingStateButton);
        addLogoutButton();
    }

    /**
     * adds action listener to the change fee button
     * @param listenForChangeFeeButton
     */
    public void addChangeFeeListener(ActionListener listenForChangeFeeButton){
        changeFeeButton.addActionListener(listenForChangeFeeButton);
    }

    /**
     * adds action listener to the get report button
     * @param listenForGetReportButton
     */
    public void addGetReportListener(ActionListener listenForGetReportButton){
        getReportButton.addActionListener(listenForGetReportButton);
    }

    /**
     * adds action listener to the get entity info button
     * @param listenForGetEntityInfoButton
     */
    public void addGetEntityInfoListener(ActionListener listenForGetEntityInfoButton){
        getEntityInfoButton.addActionListener(listenForGetEntityInfoButton);
    }

    /**
     * adds action listener to the edit listing button
     * @param listenForEditListingStateButton
     */
    public void addEditListingStateListener(ActionListener listenForEditListingStateButton){
        editListingStateButton.addActionListener(listenForEditListingStateButton);
    }
}
