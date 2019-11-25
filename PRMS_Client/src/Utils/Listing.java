package Utils;

import java.io.Serializable;
import java.util.Calendar;

/**
 * The listing class has all the information of the
 * listing that is created by the landlords and is shown
 * inside of the list of listings.  They implements Serializable
 * so that we can turn it into an object when needed
 * @author  Gary Wu
 * @since November 25, 2019
 */
public class Listing implements Serializable{

    //Member variables
    private static final long serialVersionUID = 6L;
    private String type;
    private int numOfBedrooms;
    private int numOfBathrooms;
    private boolean furnished;
    private String quadrant;
    private String state;
    private String address;

    private Fee fee;
    private String landlordEmail;
    private int listingID;

    private Calendar creationDate;
    private Calendar rentedDate;

    /**
     * The listing constructor which needs the different parameters
     * listed below to create the listing object
     * @param type type of house
     * @param bedrooms number of bedrooms
     * @param bathrooms number of bathrooms
     * @param furnished tells us if it is furnished
     * @param quadrant where it is
     * @param state what the current state it
     * @param fee the fee to put it up
     * @param landlordEmail the email associated to this listing
     * @param id the id of the listing
     * @param address the address of the listing
     */
    public Listing(String type, int bedrooms, int bathrooms, boolean furnished, String quadrant, String state, Fee fee, String landlordEmail, int id, String address){
        this.type = type;
        numOfBedrooms = bedrooms;
        numOfBathrooms = bathrooms;
        this.furnished = furnished;
        this.quadrant = quadrant;
        this.state = state;
        this.fee = fee;
        this.landlordEmail = landlordEmail;
        listingID = id;
        creationDate = Calendar.getInstance();
        this.address = address;
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

    public String getAddress() {
        return address;
    }
}
