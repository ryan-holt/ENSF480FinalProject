package Domain.Controllers;

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
