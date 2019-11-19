package Domain.Controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

/**
 * This class is responsible for communicating with the client.
 * A new instance of this class is appointed to each client in an
 * independent thread.
 * @author Harsohail Brar
 * @since April 12, 2019
 */
public class ServerCommunicationController implements Runnable {

    private Socket aSocket;
    private ObjectInputStream socketIn;
    private ObjectOutputStream socketOut;
    private ManagementSystemController managementSystemController;

    public ServerCommunicationController(Socket s, ManagementSystemController managementSystemController) {
        try {
            aSocket = s;
            setManagementSystemController(managementSystemController);

            socketOut = new ObjectOutputStream(aSocket.getOutputStream());

            printIPInfo();
        } catch (IOException e) {
            System.out.println("ServerCommController: Create ServerCommController Error");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        createInputStream();
        verifyLogin();
        communicate();
    }

    public void communicate(){
        while(true){
            // TODO communicate with client
        }
    }

    /**
     * Creates an input socket stream from server
     */
    public void createInputStream() {
        try {
            socketIn = new ObjectInputStream(aSocket.getInputStream());
        } catch (IOException e) {
            System.out.println("Error creating server output stream");
            e.printStackTrace();
        }
    }

    /**
     * Verifies the log in by running an infinite loop that only stops if the user
     * has entered a valid username and password
     */
    public void verifyLogin(){
        // TODO Verify client login
    }

    /**
     * Prints server IP info
     */
    public void printIPInfo() {
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            System.out.println("You current IP address: " + ip);
        } catch (UnknownHostException e) {
            System.out.println("IP Print error");
            e.printStackTrace();
        }
    }

    // Getters and setters
    public void setManagementSystemController(ManagementSystemController managementSystemController) {
        this.managementSystemController = managementSystemController;
    }
}

