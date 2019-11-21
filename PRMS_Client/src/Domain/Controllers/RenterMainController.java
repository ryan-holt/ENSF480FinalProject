package Domain.Controllers;

import Presentation.Views.RenterMainView;

public class RenterMainController extends Controller{

    private RenterMainView renterMainView;

    public RenterMainController(RenterMainView rmv, ClientCommunicationController ccc){
        super(ccc);
        renterMainView = rmv;

        renterMainView.addLogoutListener(e -> logoutListen());
        renterMainView.addSearchListingListener(e -> searchListingListen());
    }

    public void searchListingListen(){
        clientCommunicationController.getMainController().hideView();
        clientCommunicationController.createSearchListingWindow();
        clientCommunicationController.getSearchListingController().displayView();
    }

    public void logoutListen(){
        // TODO return to login window (server has to also go to verify login)
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
