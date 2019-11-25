package Domain.Controllers;

/**
 * This class is an abstract class which provides the connection between
 * the view controllers so they can access the sockets
 * @author Harsohail Brar
 *  * @version 4.10.0
 *  * @since November 25, 2019
 */
public abstract class Controller {

    /**
     * Client Controller consisting of sockets
     */
    protected ClientCommunicationController clientCommunicationController;

    /**
     * Constructor for the class
     * @param c Client Controller
     */
    public Controller(ClientCommunicationController c){
        setClientCommunicationController(c);
    }

    // Visibility of view functions
    public abstract void displayView();
    public abstract void hideView();

    /**
     * Client controller setter which creates a
     * 2-way association between the classes
     * @param c client controller
     */
    public void setClientCommunicationController(ClientCommunicationController c){
        clientCommunicationController = c;   // 2-way association with CC
    }
}
