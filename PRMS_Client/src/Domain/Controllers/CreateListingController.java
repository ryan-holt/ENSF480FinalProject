package Domain.Controllers;

import Presentation.Views.CreateListingView;
import Utils.Fee;
import Utils.Listing;
import Utils.ListingStates;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class CreateListingController extends Controller implements Messages, ListingStates {

    private CreateListingView createListingView;

    public CreateListingController(CreateListingView clv, ClientCommunicationController ccc){
        super(ccc);
        createListingView = clv;

        createListingView.addCreateListingListener(e -> createListingListen());
    }

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

    @Override
    public void displayView() {
        createListingView.display();
    }

    @Override
    public void hideView() {
        createListingView.hide();
    }
}
