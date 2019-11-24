package Domain.Controllers;

import Presentation.Views.RenterMainView;

import javax.swing.*;
import java.io.IOException;

public class RenterMainController extends Controller implements Messages{

    private RenterMainView renterMainView;

    public RenterMainController(RenterMainView rmv, ClientCommunicationController ccc){
        super(ccc);
        renterMainView = rmv;

        renterMainView.addLogoutListener(e -> logoutListen());
        renterMainView.addSearchListingListener(e -> searchListingListen());
        renterMainView.addUnsubscribeListener(e -> unsubscribeListen());
    }

    public void unsubscribeListen(){
        try {
            // Send action to server
            clientCommunicationController.getSocketOut().writeObject(UNSUBSCRIBE);

            JOptionPane.showMessageDialog(null, "Unsubscribed!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void searchListingListen(){
        clientCommunicationController.getMainController().hideView();
        clientCommunicationController.getSearchListingController().displayView();
    }

    public void logoutListen(){
        System.exit(1);
    }

    @Override
    public void displayView() {
        renterMainView.display();
    }

    @Override
    public void hideView() {
        renterMainView.hide();
    }
}
