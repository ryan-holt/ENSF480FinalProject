package Utils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Calendar expirationDate;
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

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
     * @param cDate The current date
     * @param rDate The rented date
     * @param eDate The expiration date
     */
    public Listing(String type, int bedrooms, int bathrooms, boolean furnished, String quadrant, String state, Fee fee, String landlordEmail, int id, String cDate, String rDate, String eDate, String address){
        this.type = type;
        numOfBedrooms = bedrooms;
        numOfBathrooms = bathrooms;
        this.furnished = furnished;
        this.quadrant = quadrant;
        this.state = state;
        this.fee = fee;
        this.landlordEmail = landlordEmail;
        listingID = id;
        this.address = address;
        try {
            creationDate = Calendar.getInstance();
            rentedDate = Calendar.getInstance();
            expirationDate = Calendar.getInstance();
            if(cDate != null)
                creationDate.setTime(df.parse(cDate));
            if(rDate != null)
                rentedDate.setTime(df.parse(rDate));
            if(eDate != null)
                expirationDate.setTime(df.parse(eDate));
        }catch (ParseException p){
            p.printStackTrace();
        }
    }

    /**
     * Checks to see if the listing is in a specific period
     * for the periodic report
     * @param startPeriodDate the start date
     * @param endPeriodDate the end date
     * @return returns true if the listing is within the dates
     * otherwise returns false
     */
    public boolean isListingInPeriod(String startPeriodDate, String endPeriodDate){
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        try {
            startDate.setTime(df.parse(startPeriodDate));
            endDate.setTime(df.parse(endPeriodDate));
        }catch (ParseException e){
            e.printStackTrace();
        }

        return creationDate.after(startDate) && creationDate.before(endDate);
    }

    // Getters and Setters

    public String setExpirationDate(int period){
        System.out.println(period);
        expirationDate = Calendar.getInstance();
        expirationDate.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE) + period);
        return getDateString(expirationDate);
    }

    public String getDateString(Calendar date){
        return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
    }

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

    public Calendar getCreationDate() {
        return creationDate;
    }

    public String getAddress() {
        return address;
    }
}
