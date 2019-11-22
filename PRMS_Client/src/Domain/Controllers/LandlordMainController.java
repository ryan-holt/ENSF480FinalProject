package Domain.Controllers;

import Presentation.Views.LandlordMainView;

public class LandlordMainController extends Controller{

    private LandlordMainView landlordMainView;

    public LandlordMainController(LandlordMainView lmv, ClientCommunicationController ccc){
        super(ccc);
        landlordMainView = lmv;

        landlordMainView.addCreateListingListener(e -> createListingListen());
    }

    public void createListingListen(){
        clientCommunicationController.getMainController().hideView();
        clientCommunicationController.getCreateListingController().displayView();
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
