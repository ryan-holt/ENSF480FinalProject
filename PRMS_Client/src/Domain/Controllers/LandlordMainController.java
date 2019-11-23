package Domain.Controllers;

import Presentation.Views.LandlordMainView;
import Utils.Listing;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LandlordMainController extends Controller implements Messages{

    private LandlordMainView landlordMainView;

    public LandlordMainController(LandlordMainView lmv, ClientCommunicationController ccc){
        super(ccc);
        landlordMainView = lmv;

        landlordMainView.addCreateListingListener(e -> createListingListen());
        landlordMainView.addMakePaymentListener(e -> showLandlordListings());
        landlordMainView.addEditListingListener(e -> showLandlordListings());
        landlordMainView.addLogoutListener(e -> logoutListen());
    }

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

    public void createListingListen(){
        clientCommunicationController.getMainController().hideView();
        clientCommunicationController.getCreateListingController().displayView();
    }

    public void logoutListen(){
        System.exit(1);
    }

    @Override
    public void displayView() {
        landlordMainView.display();
    }

    @Override
    public void hideView() {
        landlordMainView.hide();
    }
}
