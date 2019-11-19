package Domain.Controllers;

import Presentation.Views.*;

/**
 * This class is responsible for controlling the login view
 * as well as creating the main window after login
 *
 * @author  Harsohail Brar
 * @version 4.10.0
 * @since April 5, 2019
 */
public class LoginController extends Controller{

    //MEMBER VARIABLES

    private LoginView loginView;
    private boolean verified;

    /**
     * Creates a LoginController object and adds the object listener for the
     * log in button
     */
    public LoginController(LoginView l, ClientCommunicationController ccc){
        super(ccc);
        loginView = l;
        verified = false;

        loginView.addLoginListener(e -> loginListen());
        loginView.addRegularRenterListener(e -> regularRenterListen());
    }

    /**
     * Action Listener implementation for Login Button
     */
    public void loginListen(){
        try{
            String username = loginView.getUsernameField().getText();
            String password = loginView.getPasswordField().getText();

            // TODO Communicate login with server
        }catch(Exception f){
            f.printStackTrace();
        }
    }

    public void regularRenterListen(){
        clientCommunicationController.setMainController(new MainController(new RenterMainView(), clientCommunicationController));
        this.hideView();
        clientCommunicationController.showMainWindow();
    }

    @Override
    public void displayView() {
        loginView.display();
    }

    @Override
    public void hideView() {
        loginView.hide();
    }

    //getters and
    public boolean isVerified() {
        return verified;
    }
}