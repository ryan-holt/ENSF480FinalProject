package Domain.Controllers;

import Presentation.Views.RenterMainView;

import javax.swing.*;
import java.io.IOException;

/**
 * This class is responsible for controlling the renter main view
 * @author Harsohail Brar
 * @version 4.10.0
 * @since November 25, 2019
 */
public class RenterMainController extends Controller implements Messages{

    // Renter Main View
    private RenterMainView renterMainView;

    /**
     * Constructor to create the RenterMainController object
     * @param clv RenterMainView object
     * @param ccc ClientCommunicationController object
     */
    public RenterMainController(RenterMainView rmv, ClientCommunicationController ccc){
        super(ccc);
        renterMainView = rmv;

        renterMainView.addLogoutListener(e -> logoutListen());
        renterMainView.addSearchListingListener(e -> searchListingListen());
        renterMainView.addUnsubscribeListener(e -> unsubscribeListen());
    }

    /**
     * Unsubscribes the user when unsubscribe button is pressed
     */
    public void unsubscribeListen(){
        try {
            // Send action to server
            clientCommunicationController.getSocketOut().writeObject(UNSUBSCRIBE);

            JOptionPane.showMessageDialog(null, "Unsubscribed!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Prompts search listing view upon clicking search listing button
     */
    public void searchListingListen(){
        clientCommunicationController.getMainController().hideView();
        clientCommunicationController.getSearchListingController().displayView();
    }

    /**
     * Exists program when logout button is pressed
     */
    public void logoutListen(){
        System.exit(1);
    }

    // Visibility Functions
    @Override
    public void displayView() {
        renterMainView.display();
    }

    @Override
    public void hideView() {
        renterMainView.hide();
    }
}
