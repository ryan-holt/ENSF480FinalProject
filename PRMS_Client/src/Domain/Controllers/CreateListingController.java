package Domain.Controllers;

import Presentation.Views.CreateListingView;
import Utils.Listing;
import Utils.ListingStates;

import javax.swing.*;
import java.io.IOException;

/**
 * This class is responsible for controlling the create listing view
 * @author Harsohail Brar
 * @version 4.10.0
 * @since November 25, 2019
 */
public class CreateListingController extends Controller implements Messages, ListingStates {

    // Create Listing View
    private CreateListingView createListingView;

    /**
     * Constructor to create the CreateListingController object
     * @param clv CreateListingView object
     * @param ccc ClientCommunicationController object
     */
    public CreateListingController(CreateListingView clv, ClientCommunicationController ccc){
        super(ccc);
        createListingView = clv;

        createListingView.addCreateListingListener(e -> createListingListen());
    }

    /**
     * Sends create listing view field info to the server when the create listing button is pressed
     */
    public void createListingListen(){
        String houseType = (String)createListingView.getHouseTypeJBox().getSelectedItem();
        int numOfBedrooms = Integer.parseInt((String)createListingView.getNumBedroomsBox().getSelectedItem());
        int numOfBathrooms = Integer.parseInt((String)createListingView.getNumBathroomsBox().getSelectedItem());
        boolean furnished = createListingView.getFurnishedSelectorItem();
        String cityQuadrant = (String)createListingView.getCityQuadBox().getSelectedItem();
        String landlordEmail = clientCommunicationController.getUser().getEmail();
        String address = createListingView.getAddressField().getText();

        Listing newListing = new Listing(houseType, numOfBedrooms, numOfBathrooms, furnished, cityQuadrant, NOT_ACTIVE, null, landlordEmail, 0, address);

        try {
            // Send action to server
            clientCommunicationController.getSocketOut().writeObject(CREATE_LISTING);
            // Send listing to server
            clientCommunicationController.getSocketOut().writeObject(newListing);
        }catch (IOException e){
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Listing Created!");
        createListingView.hide();
        clientCommunicationController.getMainController().displayView();
    }

    // View visibility functions
    @Override
    public void displayView() {
        createListingView.display();
    }

    @Override
    public void hideView() {
        createListingView.hide();
    }
}
