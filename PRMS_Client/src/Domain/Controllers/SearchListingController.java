package Domain.Controllers;

import Presentation.Views.SearchListingView;
import Utils.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is responsible for controlling the search listing view
 * @author Harsohail Brar
 * @version 4.10.0
 * @since November 25, 2019
 */
public class SearchListingController extends Controller implements Messages{

    // Search Listing View
    private SearchListingView searchListingView;

    /**
     * Constructor to create the SearchListingController object
     * @param clv SearchListingView object
     * @param ccc ClientCommunicationController object
     */
    public SearchListingController(SearchListingView slv, ClientCommunicationController ccc){
        super(ccc);
        searchListingView = slv;

        searchListingView.addBackToMenuListener(e -> backToMenuListen());
        searchListingView.addSearchForListingsListener(e -> searchForListingsListen());
    }

    /**
     * Prompts menu for user when back to menu button is clicked
     */
    public void backToMenuListen(){
        searchListingView.setVisible(false);
        clientCommunicationController.getMainController().displayView();
    }

    /**
     * Gets listings from server and creates listings view for the user using their search query
     */
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

    // Visibility functions
    @Override
    public void displayView() {
        searchListingView.display();
    }

    @Override
    public void hideView() {
        searchListingView.hide();
    }
}
