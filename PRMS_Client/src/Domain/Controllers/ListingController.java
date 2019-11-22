package Domain.Controllers;

import Presentation.Views.*;
import Utils.Listing;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

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
            // TODO communicate with server to make payment, change outstanding fee of lisitng and activate it
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
            // TODO communicate with server to edit the listing
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

    // Getters and Setters

    public ListingView getListingView() {
        return listingView;
    }
}
