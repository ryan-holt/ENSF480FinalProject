package Domain.Controllers;

import Presentation.Views.*;
import Utils.Listing;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class ListingController extends Controller implements Messages{

    private ListingView listingView;

    public ListingController(ListingView lv, ClientCommunicationController ccc){
        super(ccc);
        listingView = lv;

        listingView.addBackToMenuListener(e -> backToMenuListen());
        listingView.addEmailLandlordListener(e -> emailLandlordListen());
        listingView.addMakePaymentListener(e -> makePaymentListen());
        listingView.addEditListingListener(e -> editListingListen());
    }

    public void makePaymentListen(){
        int selectedRow = listingView.getListingTable().getSelectedRow();

        if(selectedRow < 0){    // nothing selected
            JOptionPane.showMessageDialog(null, "Please select a listing!");
            return;
        }else{
            int selectedListingID = Integer.parseInt((String)listingView.getListingTableModel().getValueAt(selectedRow, 0));
            try {
                // Send action to server
                clientCommunicationController.getSocketOut().writeObject(GET_LISTING_FEE);
                // Send listing id to server
                clientCommunicationController.getSocketOut().writeObject(selectedListingID);
                // Receive listing fee
                double paymentDue = (Double) clientCommunicationController.getSocketIn().readObject();

                double paymentEntered;
                if(paymentDue < 0){
                    JOptionPane.showMessageDialog(null, "Listing doesn't exist...");
                    return;
                }else{
                    paymentEntered = doubleInputPrompt("Enter payment amount: (Payment due: $" + paymentDue + ")");
                }

                if(paymentEntered < paymentDue){
                    JOptionPane.showMessageDialog(null, "Please pay the full amount!");
                    return;
                }else if(paymentEntered > paymentDue){
                    JOptionPane.showMessageDialog(null, "Thank you! We refunded overpay!");
                }else{
                    JOptionPane.showMessageDialog(null, "Thank you!");
                }

                // Send action to server
                clientCommunicationController.getSocketOut().writeObject(ACTIVATE_LISTING);
                // Send listing id to server
                clientCommunicationController.getSocketOut().writeObject(selectedListingID);
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    public void editListingListen(){
        int selectedRow = listingView.getListingTable().getSelectedRow();

        if(selectedRow < 0){    // nothing selected
            JOptionPane.showMessageDialog(null, "Please select a listing!");
            return;
        }else {
            Listing selectedListing = getSelectedListing(selectedRow);
            Listing editedListing = editListing(selectedListing);
            try {
                // Send action to server
                clientCommunicationController.getSocketOut().writeObject(EDIT_LISTING);
                // Send edited listing to server
                clientCommunicationController.getSocketOut().writeObject(editedListing);

                JOptionPane.showMessageDialog(null, "Edited Listing!");
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public Listing editListing(Listing listing){
        String editListing = "Edit listing";
        String[] type = new String[]{"Apartment", "Attached", "Detached", "Townhouse"};
        String[] bedrooms = { "1", "2", "3", "4", "5" };
        String[] bathrooms = { "1", "2", "3", "4", "5" };
        String[] furnished = { "Yes", "No" };
        String[] quadrant = { "SW", "NW", "NE", "SE" };

        JComboBox typeBox = new JComboBox(type);
        typeBox.setSelectedIndex(getIndex(type, listing.getType()));
        JComboBox bedroomsBox = new JComboBox(bedrooms);
        bedroomsBox.setSelectedIndex(getIndex(bedrooms, String.valueOf(listing.getNumOfBedrooms())));
        JComboBox bathroomsBox = new JComboBox(bathrooms);
        bathroomsBox.setSelectedIndex(getIndex(bathrooms, String.valueOf(listing.getNumOfBathrooms())));
        JComboBox furnishedBox = new JComboBox(furnished);
        furnishedBox.setSelectedIndex(getIndex(furnished, listing.isFurnishedString()));
        JComboBox quadrantBox = new JComboBox(quadrant);
        quadrantBox.setSelectedIndex(getIndex(quadrant, listing.getQuadrant()));

        String editType = (String) JOptionPane.showInputDialog(null, "Select Type:",
                                                                editListing, JOptionPane.QUESTION_MESSAGE, null,
                                                                type, type[getIndex(type, listing.getType())]);
        String editBedrooms = (String) JOptionPane.showInputDialog(null, "Select Bedrooms:",
                                                                editListing, JOptionPane.QUESTION_MESSAGE, null,
                                                                bedrooms, bedrooms[getIndex(bedrooms, String.valueOf(listing.getNumOfBedrooms()))]);
        String editBathrooms = (String) JOptionPane.showInputDialog(null, "Select Bathrooms:",
                                                                editListing, JOptionPane.QUESTION_MESSAGE, null,
                                                                bathrooms, bathrooms[getIndex(bathrooms, String.valueOf(listing.getNumOfBathrooms()))]);
        String editFurnished = (String) JOptionPane.showInputDialog(null, "Select Furnishing:",
                                                                editListing, JOptionPane.QUESTION_MESSAGE, null,
                                                                furnished, furnished[getIndex(furnished, listing.isFurnishedString())]);
        String editQuadrant = (String) JOptionPane.showInputDialog(null, "Select Quadrant:",
                                                                editListing, JOptionPane.QUESTION_MESSAGE, null,
                                                                quadrant, quadrant[getIndex(quadrant, listing.getQuadrant())]);

        listing.setType(editType);
        listing.setNumOfBedrooms(Integer.parseInt(editBedrooms));
        listing.setNumOfBathrooms(Integer.parseInt(editBathrooms));
        listing.setFurnishedString(editFurnished);
        listing.setQuadrant(editQuadrant);

        return listing;
    }

    public Listing getSelectedListing(int selectedRow){
        int selectedListingID = Integer.parseInt((String)listingView.getListingTableModel().getValueAt(selectedRow, 0));
        String type = (String)listingView.getListingTableModel().getValueAt(selectedRow, 1);
        int bedrooms = Integer.parseInt((String)listingView.getListingTableModel().getValueAt(selectedRow, 2));
        int bathrooms = Integer.parseInt((String)listingView.getListingTableModel().getValueAt(selectedRow, 3));
        String quadrant = (String)listingView.getListingTableModel().getValueAt(selectedRow, 4);
        boolean furnished = listingView.getListingTableModel().getValueAt(selectedRow, 5).equals("Yes");
        String state = (String)listingView.getListingTableModel().getValueAt(selectedRow, 6);
        String email = (String)listingView.getListingTableModel().getValueAt(selectedRow, 7);

        return new Listing(selectedListingID, type,  bedrooms, bathrooms, furnished, quadrant, state, email);
    }

    public void backToMenuListen(){
        listingView.setVisible(false);
        clientCommunicationController.getMainController().displayView();
    }

    public void emailLandlordListen(){
        int selectedRow = listingView.getListingTable().getSelectedRow();

        if(selectedRow < 0){    // nothing selected
            JOptionPane.showMessageDialog(null, "Please select a listing!");
            return;
        }else{
            // TODO Get input and send email
            System.out.println("WILL SEND EMAIL AFTER IMPLEMENTATION");
        }
    }

    @Override
    public void displayView() {
        listingView.display();
    }

    @Override
    public void hideView() {
        listingView.hide();
    }

    /**
     * Gets a double input from the user with error checking
     *
     * @param n message being displayed for input
     * @return integer entered by user
     */
    public double doubleInputPrompt(String n) {
        String input = null;
        double num = 0;
        while (input == null || num < 0) {

            try {
                input = JOptionPane.showInputDialog(n);
                num = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Try again!");
                input = null;
            }

        }

        return num;
    }

    public int getIndex(String[] list, String s){
        for(int i = 0; i < list.length; i++){
            if(s.equals(list[i])){
                return i;
            }
        }
        return -1;
    }

    // Getters and Setters

    public ListingView getListingView() {
        return listingView;
    }
}
