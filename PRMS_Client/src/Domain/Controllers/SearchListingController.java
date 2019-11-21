package Domain.Controllers;

import Presentation.Views.SearchListingView;

public class SearchListingController extends Controller{

    private SearchListingView searchListingView;

    public SearchListingController(SearchListingView slv, ClientCommunicationController ccc){
        super(ccc);
        searchListingView = slv;
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
