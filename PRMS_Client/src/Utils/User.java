package Utils;

import java.io.Serializable;

public class User implements UserTypes, Serializable {

    private static final long serialVersionUID = 3L;
    private String username;
    private String password;
    private Name name;
    private String address;
    private String userType;
    private String email;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Name name, String userType, String address){
        this.username = username;
        this.password = password;
        this.name = name;
        this.userType = userType;
        this.address = address;
    }

    public User(String username, String password, Name name, String userType, String address, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.userType = userType;
        this.address = address;
        this.email = email;
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
