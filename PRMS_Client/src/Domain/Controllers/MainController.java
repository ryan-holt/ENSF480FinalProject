package Domain.Controllers;

import Presentation.Views.MainView;

public class MainController extends Controller{

    private MainView mainView;

    public MainController(MainView mv, ClientCommunicationController ccc){
        super(ccc);
        mainView = mv;

        // TODO add action listeners
    }

    @Override
    public void displayView() {
        mainView.display();
    }

    @Override
    public void hideView() {
        mainView.hide();
    }
}
