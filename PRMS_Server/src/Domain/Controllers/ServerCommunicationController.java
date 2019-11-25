package Domain.Controllers;

import Database.Messages;
import Utils.*;

import javax.mail.MessagingException;
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

    // Member Variables
    private Socket aSocket;
    private ObjectInputStream socketIn;
    private ObjectOutputStream socketOut;
    private ManagementSystemController managementSystemController;
    private EmailSender emailSender;
    private User user;

    /**
     * Creates a new ServerCommunicationController object for each new client in a new thread
     */
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

        emailSender = new EmailSender();
    }

    /**
     * Run functions that is ran by the new thread delegated to each new client
     */
    @Override
    public void run() {
        createInputStream();
        verifyLogin();
        communicate();
    }

    /**
     * Forever loop to receive and send messages to clients until client logs off
     */
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
                    case GET_ALL_LISTINGS:
                        getAllListings();
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
                    case EDIT_FEE:
                        editFee();
                        break;
                    case GET_ALL_USERS:
                        getAllUsers();
                        break;
                    case GET_REPORT_DATA:
                        getReportData();
                        break;
                    case SEND_EMAIL_TO_LANDLORD:
                        sendEmailToLandlord();
                        break;
                    case UNSUBSCRIBE:
                        unsubscribe();
                        break;
                }

                expireListings();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("User logged out!");
        }
    }

    /**
     * Unsubscribes client's from email notifications in database
     */
    public void unsubscribe(){
        managementSystemController.getDatabaseController().getDatabaseModel().removeObserver(user);
    }

    /**
     * Expires listings with expiration date as today in database
     */
    public void expireListings(){
        managementSystemController.getDatabaseController().getDatabaseModel().expireListings();
    }

    /**
     * Sends email to landlord by receiving listing, email, and message from client
     */
    public void sendEmailToLandlord(){
        try {
            // Receive listingID from client
            int listingID = (Integer) socketIn.readObject();
            // Receive clientEmail from client
            String clientEmail = (String) socketIn.readObject();
            // Receive clientMessage from client
            String clientMessage = (String) socketIn.readObject();
            // Query listing with listing ID to get landlord email
            Listing listing = managementSystemController.getDatabaseController().getDatabaseModel().queryListingByID(listingID);
            // Send email to landlord
            emailSender.sendMail(listing.getLandlordEmail(), clientMessage, clientEmail, listing);
        }catch (IOException | ClassNotFoundException | MessagingException e){
            e.printStackTrace();
        }
    }

    /**
     * Gets and sends report data from database to the client
     */
    public void getReportData(){
        try {
            // Receive dates from client
            ArrayList<String> dates = (ArrayList<String>) socketIn.readObject();
            // Query total number of houses listed in the period
            int numOfHousesListed = managementSystemController.getDatabaseController().getDatabaseModel().queryNumOfHousesListedInPeriod(dates);
            // Send total number of houses listed to client
            socketOut.writeObject(numOfHousesListed);
            // Query total number houses rented to client
            ArrayList<Listing> rentedListingsInPeriod = managementSystemController.getDatabaseController().getDatabaseModel().queryHousesRentedInPeriod(dates);
            // Send total number of houses rented to client
            socketOut.writeObject(rentedListingsInPeriod);
            // Query total number active listings currently
            int totalActiveListingsCurr = managementSystemController.getDatabaseController().getDatabaseModel().queryNumOfActiveListings();
            // Send total number active listings currently to client
            socketOut.writeObject(totalActiveListingsCurr);

            // Query landlord names for rented listings
            ArrayList<User> landlords = new ArrayList<>();
            for(Listing rentedListing: rentedListingsInPeriod){
                landlords.add(managementSystemController.getDatabaseController().getDatabaseModel().getUserByEmail(rentedListing.getLandlordEmail()));
            }
            // Send landlords to client
            socketOut.writeObject(landlords);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Gets all users from the database
     */
    public void getAllUsers(){
        //Query database
        ArrayList<User> users = managementSystemController.getDatabaseController().getDatabaseModel().queryAllUsers();
        // Send to client
        try{
            socketOut.writeObject(users);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Gets all listings from the database
     */
    public void getAllListings(){
        //Query database
        ArrayList<Listing> listings = managementSystemController.getDatabaseController().getDatabaseModel().queryAllListings();
        try{
            socketOut.writeObject(listings);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Edits the fee object used in creation of new listings
     */
    public void editFee(){
        try{
            // Receive fee variables from client
            double newFeeAmount = (Double)socketIn.readObject();
            int newFeePeriod = (Integer) socketIn.readObject();
            // Edit fee object
            managementSystemController.getDatabaseController().getDatabaseModel().getFee().setFeeAmount(newFeeAmount);
            managementSystemController.getDatabaseController().getDatabaseModel().getFee().setFeePeriod(newFeePeriod);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Activates listing in database after payment is made
     */
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

    /**
     * Edits listing requested by client in the database
     */
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

    /**
     * Gets listing fee from the database
     */
    public void getListingFee(){
        try{
            // Receive listing id from client
            int listingID = (Integer) socketIn.readObject();
            // Query database
            double fee = managementSystemController.getDatabaseController().getDatabaseModel().queryListingByID(listingID).getFee().getFeeAmount();
            // Send response to client
            socketOut.writeObject(fee);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Searches for a listings using landlord's email
     */
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

    /**
     * Searches for a listing using a search query from the client
     */
    public void searchListings(){
        try {
            // Receive query from client
            ArrayList<String> listingsQuery = (ArrayList<String>) socketIn.readObject();
            // Query database
            ArrayList<Listing> listings = managementSystemController.getDatabaseController().getDatabaseModel().querySearchListings(listingsQuery, user);
            // Send queried listings to client
            socketOut.writeObject(listings);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Creates a listing requested by a landlord client
     */
    public void createListing(){
        try{
            // Receive listing from client
            Listing newListing = (Listing) socketIn.readObject();
            // Query database to add the new listing
            managementSystemController.getDatabaseController().getDatabaseModel().createListing(newListing);
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
                            user = databaseResponseUser;
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
            System.out.println("Your current IP address: " + ip);
        } catch (UnknownHostException e) {
            System.out.println("IP Print error");
            e.printStackTrace();
        }
    }

    // Getters and setters
    public void setManagementSystemController(ManagementSystemController managementSystemController) {
        this.managementSystemController = managementSystemController;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

