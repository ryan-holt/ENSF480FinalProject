package Domain.Controllers;

import Presentation.Views.LandlordMainView;
import Utils.Listing;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is responsible for controlling the landlord main view
 * @author Harsohail Brar
 * @version 4.10.0
 * @since November 25, 2019
 */
public class LandlordMainController extends Controller implements Messages{

    // Landlord Main View
    private LandlordMainView landlordMainView;

    /**
     * Constructor to create the LandlordMainController object
     * @param clv LandlordMainView object
     * @param ccc ClientCommunicationController object
     */
    public LandlordMainController(LandlordMainView lmv, ClientCommunicationController ccc){
        super(ccc);
        landlordMainView = lmv;

        landlordMainView.addCreateListingListener(e -> createListingListen());
        landlordMainView.addMakePaymentListener(e -> showLandlordListings());
        landlordMainView.addEditListingListener(e -> showLandlordListings());
        landlordMainView.addLogoutListener(e -> logoutListen());
    }

    /**
     * Shows the landlord listings through the listings view when the edit listing button is pressed
     */
    public void showLandlordListings(){
        clientCommunicationController.getMainController().hideView();
        try {
            // Send action to server
            clientCommunicationController.getSocketOut().writeObject(SEARCH_LISTINGS_BY_LANDLORD);
            // Send landlord email to server
            clientCommunicationController.getSocketOut().writeObject(clientCommunicationController.getUser().getEmail());
            // Receive listings from server
            ArrayList<Listing> listings = (ArrayList<Listing>) clientCommunicationController.getSocketIn().readObject();
            clientCommunicationController.getListingController().getListingView().updateListingTable(listings);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        clientCommunicationController.getListingController().getListingView().updateListingViewForLandlord();
        clientCommunicationController.getListingController().displayView();
    }

    /**
     * Makes the create listing view visible when create listing button pressed
     */
    public void createListingListen(){
        clientCommunicationController.getMainController().hideView();
        clientCommunicationController.getCreateListingController().displayView();
    }

    /**
     * Ends the program when user clicks logout button
     */
    public void logoutListen(){
        System.exit(1);
    }

    // Visibility functions
    @Override
    public void displayView() {
        landlordMainView.display();
    }

    @Override
    public void hideView() {
        landlordMainView.hide();
    }
}
