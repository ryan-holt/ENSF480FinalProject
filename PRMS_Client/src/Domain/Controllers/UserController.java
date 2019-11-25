package Domain.Controllers;

import Presentation.Views.UserView;

/**
 * This class is responsible for controlling the user view
 * @author Harsohail Brar
 * @version 4.10.0
 * @since November 25, 2019
 */
public class UserController extends Controller implements Messages {

    // User View
    private UserView userView;

    /**
     * Constructor to create the UserController object
     * @param clv UserView object
     * @param ccc ClientCommunicationController object
     */
    public UserController(UserView uv, ClientCommunicationController ccc){
        super(ccc);
        userView = uv;

        userView.addBackToMenuListener(e -> backToMenuListen());
    }

    /**
     * Prompts menu for user when back to menu button is clicked
     */
    public void backToMenuListen(){
        userView.setVisible(false);
        clientCommunicationController.getMainController().displayView();
    }

    // Visibility functions
    @Override
    public void displayView() {
        userView.display();
    }

    @Override
    public void hideView() {
        userView.hide();
    }

    // Getters and setters
    public UserView getUserView() {
        return userView;
    }
}
