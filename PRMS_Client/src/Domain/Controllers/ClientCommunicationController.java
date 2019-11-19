package Domain.Controllers;

import Presentation.Views.*;
import Utils.*;

import java.io.*;
import java.net.*;

/**
 * This class is responsible for communicating with the server
 * and holding the LoginController
 * Overall the client controller is used for communication with
 * the server
 *
 * @author Ryan Holt
 * @version 4.10.0
 * @since April 5, 2019
 */
public class ClientCommunicationController implements UserTypes{

    //MEMBER VARIABLES
    private ObjectOutputStream socketOut;
    private Socket aSocket;
    private ObjectInputStream socketIn;

    private Controller loginController, mainController;

    /**
     * Constructs a Client controller object
     *
     * @param serverName name of server
     * @param portNumber port number
     */
    public ClientCommunicationController(String serverName, int portNumber) {
        try {
            aSocket = new Socket(serverName, portNumber);

            socketIn = new ObjectInputStream(aSocket.getInputStream());
            socketOut = new ObjectOutputStream(aSocket.getOutputStream());

            LoginView loginView = new LoginView();

            loginController = new LoginController(loginView, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * runs the client side
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ClientCommunicationController ccc = new ClientCommunicationController("localhost", 7000);
    }

    public void showMainWindow(User user){
        String userType = user.getUserType().toLowerCase();
        switch (userType){
            case REG_RENTER:
                showRenterMainWindow();
                break;
            case MANAGER:
                showManagerMainWindow();
                break;
            case LANDLORD:
                showLandlordMainWindow();
                break;
        }
        mainController.displayView();
    }

    public void showRenterMainWindow(){
        setMainController(new MainController(new RenterMainView(), this));
    }

    public void showManagerMainWindow(){
        setMainController(new MainController(new ManagerMainView(), this));
    }

    public void showLandlordMainWindow(){
        setMainController(new MainController(new LandlordMainView(), this));
    }

    //GETTERS AND SETTERS
    public ObjectOutputStream getSocketOut() {
        return socketOut;
    }

    public ObjectInputStream getSocketIn() {
        return socketIn;
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public Controller getMainController() {
        return mainController;
    }
}
