package Utils;

import Database.DatabaseModel;
import Database.Observer;
import Domain.Controllers.EmailSender;

import javax.mail.MessagingException;
import java.io.Serializable;

/**
 * User class that holds all user information and
 * acts as the observer in the observer pattern used to notify users for
 * new created listings that match their queries
 */
public class User implements UserTypes, Serializable, Observer {

    // Member Variables
    private static final long serialVersionUID = 3L;
    private String username;
    private String password;
    private Name name;
    private String address;
    private String userType;
    private String email;
    private boolean subscribed;

    // Constructors
    public User(String username, String password, Name name, String userType, String address, String email, DatabaseModel dm, boolean sub){
        this.username = username;
        this.password = password;
        this.name = name;
        this.userType = userType;
        this.address = address;
        this.email = email;
        subscribed = sub;
    }

    /**
     * Called from notify all observers in the database model
     * Sends email with the new listing info to the user
     * @param newListing new listing that has to be emailed
     */
    @Override
    public void update(Listing newListing) {
        EmailSender emailSender = new EmailSender();
        try {
            emailSender.sendMail(email, "New Listing matching your searches!", "PRMS_Notifier", newListing);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    /**
     * check if used is subscribed to emails
     * @return true if user is subscribed, false otherwise
     */
    public boolean isSubscribed() {
        return subscribed;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public Name getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
