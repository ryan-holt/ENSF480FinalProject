package Domain.Controllers;

import Database.Messages;
import Utils.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

/**
 * This class is responsible for communicating with the client.
 * A new instance of this class is appointed to each client in an
 * independent thread.
 * @author Harsohail Brar
 * @since April 12, 2019
 */
public class ServerCommunicationController implements Runnable, Messages, UserTypes{

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
        try {
            String action;
            while (true) {
                action = (String) socketIn.readObject();

                switch (action){
                    case SEARCH_LISTINGS:
                        searchListings();
                        break;
                    case CREATE_LISTING:
                        createListing();
                        break;
                    case SEARCH_LISTINGS_BY_LANDLORD:
                        searchListingsByLandlord();
                        break;
                    case GET_LISTING_FEE:
                        getListingFee();
                        break;
                    case ACTIVATE_LISTING:
                        activateListing();
                        break;
                    case EDIT_LISTING:
                        editListing();
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void activateListing(){
        try{
            // Receive listing id from client
            int listingID = (Integer) socketIn.readObject();
            // Query database
            managementSystemController.getDatabaseController().getDatabaseModel().activateListing(listingID);
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void editListing(){
        try{
            // Receive listing from client
            Listing listingToBeEdited = (Listing) socketIn.readObject();
            // Query database
            managementSystemController.getDatabaseController().getDatabaseModel().editListing(listingToBeEdited);
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getListingFee(){
        try{
            // Receive listing id from client
            int listingID = (Integer) socketIn.readObject();
            // Query database
            double fee = managementSystemController.getDatabaseController().getDatabaseModel().queryListingFeeByID(listingID);
            // Send response to client
            socketOut.writeObject(fee);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void searchListingsByLandlord(){
        try {
            // Receive landlord email from client
            String landlordEmail = (String) socketIn.readObject();
            // Query database
            ArrayList<Listing> listings = managementSystemController.getDatabaseController().getDatabaseModel().queryListingsByLandlord(landlordEmail);
            // Send listings to client
            socketOut.writeObject(listings);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void searchListings(){
        try {
            // Receive query from client
            ArrayList<String> listingsQuery = (ArrayList<String>) socketIn.readObject();
            // Query database
            ArrayList<Listing> listings = managementSystemController.getDatabaseController().getDatabaseModel().queryListings(listingsQuery);
            // Send queried listings to client
            socketOut.writeObject(listings);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void createListing(){
        try{
            // Receive listing from client
            Listing newListing = (Listing) socketIn.readObject();
            // Query database to add the new listing
            managementSystemController.getDatabaseController().getDatabaseModel().addListing(newListing);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
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
        try {
            boolean verified = false;

            while (!verified) {
                String action = (String) socketIn.readObject();
                switch (action) {
                    case LOGIN:
                        User readUser = (User) socketIn.readObject();
                        User databaseResponseUser = managementSystemController.getDatabaseController().getDatabaseModel().verifyUser(readUser);
                        if (databaseResponseUser != null) {
                            socketOut.writeObject(databaseResponseUser);
                            System.out.println("Login Success!");
                            verified = true;
                            return;
                        } else {
                            socketOut.writeObject(null);
                        }

                        socketOut.flush();
                        break;
                    case CREATE_USER:
                        User newUser = (User) socketIn.readObject();
                        boolean accountCreated = managementSystemController.getDatabaseController().getDatabaseModel().createUser(newUser);
                        if(accountCreated){
                            socketOut.writeObject(SUCCESS);
                        }else{
                            socketOut.writeObject(FAILED);
                        }
                        break;
                    case REGULAR:
                        return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

