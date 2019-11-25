package Domain.Controllers;

import Database.*;
import Utils.Fee;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is responsible for managing the thread pool as well as
 * the ServerCommunicationController. Everytime, a new client connects,
 * this class makes an instance of the ServerCommunicationController
 * for the client in a new thread
 * @author Harsohail Brar
 * @since April 12, 2019
 */
public class ManagementSystemController{

    // Member Variables
    private static final int PORT = 7000;
    private ServerSocket serverSocket;
    private DatabaseController databaseController;
    private ExecutorService pool;

    /**
     * Creates a ManagementSystemController that holds the main() function and starts the server
     */
    public ManagementSystemController() {
        try {
            serverSocket = new ServerSocket(PORT);
            databaseController = DatabaseController.getDatabaseControllerInstance();
            pool = Executors.newFixedThreadPool(10);
            System.out.println("Server is running");
            printIPInfo();
            System.out.println("********");
        } catch (IOException e) {
            System.out.println("ServerController: Create a new socket error");
            e.printStackTrace();
        }
    }

    /**
     * Main Functions that starts the server
     * @param args string arguments from command line
     */
    public static void main(String[] args) {
        ManagementSystemController myServer = new ManagementSystemController();
        myServer.communicateWithClient();
    }

    /**
     * Forever loop to accept new connection from clients
     */
    public void communicateWithClient() {
        try {
            while (true) {
                ServerCommunicationController scc = new ServerCommunicationController(serverSocket.accept(), this);

                System.out.println("New Client Connected");

                pool.execute(scc);
            }
        } catch (IOException e) {
            System.out.println("ServerController: CommunicateWithClient error");
            e.printStackTrace();
        }
    }

    /**
     * Prints server IP Info
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

    //GETTERS AND SETTERS

    public DatabaseController getDatabaseController() {
        return databaseController;
    }

}
