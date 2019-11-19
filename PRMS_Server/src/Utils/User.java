package Utils;

import java.io.Serializable;

public class User implements UserTypes, Serializable {

    private static final long serialVersionUID = 3L;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String userType;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String firstName, String lastName, String userType){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserType() {
        return userType;
    }
}
