package Domain.Controllers;

import Presentation.Views.*;
import Utils.Listing;

import javax.swing.*;
import java.util.ArrayList;

public class ListingController extends Controller{

    private ListingView listingView;

    public ListingController(ListingView lv, ClientCommunicationController ccc){
        super(ccc);
        listingView = lv;

        listingView.addBackToMenuListener(e -> backToMenuListen());
        listingView.addEmailLandlordListener(e -> emailLandlordListen());
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

    // Getters and Setters

    public ListingView getListingView() {
        return listingView;
    }
}
