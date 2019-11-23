package Domain.Controllers;

import Presentation.Views.MainView;

public class MainController extends Controller{

    private MainView mainView;

    public MainController(MainView mv, ClientCommunicationController ccc){
        super(ccc);
        mainView = mv;

        mainView.addLogoutListener(e -> logoutListen());
    }

    public void logoutListen(){
        System.exit(1);
    }

    @Override
    public void displayView() {
        mainView.display();
    }

    @Override
    public void hideView() {
        mainView.hide();
    }

    // Getters and setters
    public MainView getMainView() {
        return mainView;
    }
}
