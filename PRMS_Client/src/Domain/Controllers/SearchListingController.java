package Domain.Controllers;

import Presentation.Views.SearchListingView;
import Utils.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class SearchListingController extends Controller implements Messages{

    private SearchListingView searchListingView;

    public SearchListingController(SearchListingView slv, ClientCommunicationController ccc){
        super(ccc);
        searchListingView = slv;

        searchListingView.addBackToMenuListener(e -> backToMenuListen());
        searchListingView.addSearchForListingsListener(e -> searchForListingsListen());
    }

    public void backToMenuListen(){
        searchListingView.setVisible(false);
        clientCommunicationController.getMainController().displayView();
    }

    public void searchForListingsListen() {
        // Create listings query from selectors in GUI
        ArrayList<String> listingsQuery = new ArrayList<>();

        listingsQuery.add((String) searchListingView.getNumOfBedroomsList().getSelectedItem());
        listingsQuery.add((String) searchListingView.getNumOfBathroomsList().getSelectedItem());
        listingsQuery.add((String) searchListingView.getTypeOfHousesList().getSelectedItem());
        listingsQuery.add((String) searchListingView.getQuadrantList().getSelectedItem());
        listingsQuery.add((String) searchListingView.getFurnishingList().getSelectedItem());

        try {
            // Send action to server
            clientCommunicationController.getSocketOut().writeObject(SEARCH_LISTINGS);
            // Send query to server
            clientCommunicationController.getSocketOut().writeObject(listingsQuery);
            // Receive listings from server
            ArrayList<Listing> listings = (ArrayList<Listing>) clientCommunicationController.getSocketIn().readObject();

            if(listings.size() == 0){
                JOptionPane.showMessageDialog(null, "No listings found...");
            }else{
                clientCommunicationController.getSearchListingController().hideView();
                clientCommunicationController.getListingController().getListingView().updateListingTable(listings);
                clientCommunicationController.getListingController().displayView();
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void displayView() {
        searchListingView.display();
    }

    @Override
    public void hideView() {
        searchListingView.hide();
    }
}
