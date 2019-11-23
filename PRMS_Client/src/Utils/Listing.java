package Utils;

import java.io.Serializable;

public class Listing implements Serializable{

    private static final long serialVersionUID = 6L;
    private String type;
    private int numOfBedrooms;
    private int numOfBathrooms;
    private boolean furnished;
    private String quadrant;
    private String state;
    private Fee fee;
    private String landlordEmail;
    private int listingID;

    public Listing(int id, String type, int bedrooms, int bathrooms, boolean furnished, String quadrant, String state, String landlordEmail){
        listingID = id;
        this.type = type;
        numOfBedrooms = bedrooms;
        numOfBathrooms = bathrooms;
        this.furnished = furnished;
        this.quadrant = quadrant;
        this.state = state;
        this.landlordEmail = landlordEmail;
    }

    public Listing(String type, int bedrooms, int bathrooms, boolean furnished, String quadrant, String state, Fee fee, String landlordEmail){
        this.type = type;
        numOfBedrooms = bedrooms;
        numOfBathrooms = bathrooms;
        this.furnished = furnished;
        this.quadrant = quadrant;
        this.state = state;
        this.fee = fee;
        this.landlordEmail = landlordEmail;
    }

    public Listing(String type, int bedrooms, int bathrooms, boolean furnished, String quadrant, String state, Fee fee, String landlordEmail, int id){
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

    public String isFurnishedString() {
        if(furnished){
            return "Yes";
        }else{
            return "No";
        }
    }

    public String getQuadrant() {
        return quadrant;
    }

    public String getState() {
        return state;
    }

    public Fee getFee() {
        return fee;
    }

    public String getLandlordEmail() {
        return landlordEmail;
    }

    public int getListingID() {
        return listingID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumOfBedrooms(int numOfBedrooms) {
        this.numOfBedrooms = numOfBedrooms;
    }

    public void setNumOfBathrooms(int numOfBathrooms) {
        this.numOfBathrooms = numOfBathrooms;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public void setFurnishedString(String furnishing) {
        furnished = furnishing.equals("Yes");
    }

    public void setQuadrant(String quadrant) {
        this.quadrant = quadrant;
    }

    public void setState(String state) {
        this.state = state;
    }
}
