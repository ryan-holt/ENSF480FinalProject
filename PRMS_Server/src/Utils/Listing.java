package Utils;

import java.io.Serializable;

public class Listing implements Serializable {

    private static final long serialVersionUID = 6L;
    private String type;
    private int numOfBedrooms;
    private int numOfBathrooms;
    private boolean furnished;
    private String quadrant;
    private String state;
    private double fee;
    private String landlordEmail;
    private int listingID;

    public Listing(String type, int bedrooms, int bathrooms, boolean furnished, String quadrant, String state, double fee, String landlordEmail, int id){
        this.type = type;
        numOfBedrooms = bedrooms;
        numOfBathrooms = bathrooms;
        this.furnished = furnished;
        this.quadrant = quadrant;
        this.state = state;
        this.fee = fee;
        this.landlordEmail = landlordEmail;
        listingID = id;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public int getNumOfBedrooms() {
        return numOfBedrooms;
    }

    public int getNumOfBathrooms() {
        return numOfBathrooms;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public String getQuadrant() {
        return quadrant;
    }

    public String getState() {
        return state;
    }

    public double getFee() {
        return fee;
    }

    public String getLandlordEmail() {
        return landlordEmail;
    }

    public void setListingID(int listingID) {
        this.listingID = listingID;
    }
}
