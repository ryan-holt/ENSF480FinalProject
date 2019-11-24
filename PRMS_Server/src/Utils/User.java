package Utils;

import Database.DatabaseModel;
import Database.Observer;
import Domain.Controllers.EmailSender;

import javax.mail.MessagingException;
import java.io.Serializable;

public class User implements UserTypes, Serializable, Observer {

    private static final long serialVersionUID = 3L;
    private String username;
    private String password;
    private Name name;
    private String address;
    private String userType;
    private String email;
    private boolean subscribed;

    public User(String username, String password, Name name, String userType, String address, String email, DatabaseModel dm, boolean sub){
        this.username = username;
        this.password = password;
        this.name = name;
        this.userType = userType;
        this.address = address;
        this.email = email;
        subscribed = sub;
    }

    @Override
    public void update(Listing newListing) {
        EmailSender emailSender = new EmailSender();
        try {
            emailSender.sendMail(email, "New Listing matching your searches!", "PRMS_Notifier", newListing);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

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
