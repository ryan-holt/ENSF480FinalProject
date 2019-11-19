package Utils;

import java.io.Serializable;

public class User implements UserTypes, Serializable {

    private static final long serialVersionUID = 3L;
    private String username;
    private String password;
    private Name name;
    private Address address;
    private String userType;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Name name, String userType, Address address){
        this.username = username;
        this.password = password;
        this.name = name;
        this.userType = userType;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }
}
