package Domain.Controllers;

import Presentation.Views.MainView;

/**
 * Main Controller class that acts as a parent to the renter, landlord, and manager main controllers
 */
public class MainController extends Controller{

    // Holds the main view
    private MainView mainView;

    /**
     * Creates a MainController object using the main view and ClientCommunicationController
     * @param mv main view
     * @param ccc client communication controller
     */
    public MainController(MainView mv, ClientCommunicationController ccc){
        super(ccc);
        mainView = mv;

        mainView.addLogoutListener(e -> logoutListen());
    }

    /**
     * Exits program when logout button pressed
     */
    public void logoutListen(){
        System.exit(1);
    }

    // Visibility functions
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
