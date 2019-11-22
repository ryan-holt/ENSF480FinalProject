package Domain.Controllers;

import Presentation.Views.*;
import Utils.Listing;

import java.util.ArrayList;

public class ListingController extends Controller{

    private ListingView listingView;

    public ListingController(ListingView lv, ClientCommunicationController ccc){
        super(ccc);
        listingView = lv;

        listingView.addBackToMenuListener(e -> backToMenuListen());
    }

    public void backToMenuListen(){
        listingView.setVisible(false);
        clientCommunicationController.getMainController().displayView();
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
