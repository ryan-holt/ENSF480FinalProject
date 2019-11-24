package Utils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Listing implements Serializable{

    // TODO fee period
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
    private Calendar creationDate;
    private Calendar rentedDate;
    private Calendar expirationDate;
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

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
        creationDate = Calendar.getInstance();
    }

    public Listing(String type, int bedrooms, int bathrooms, boolean furnished, String quadrant, String state, Fee fee, String landlordEmail, int id, String cDate, String rDate, String eDate){
        this.type = type;
        numOfBedrooms = bedrooms;
        numOfBathrooms = bathrooms;
        this.furnished = furnished;
        this.quadrant = quadrant;
        this.state = state;
        this.fee = fee;
        this.landlordEmail = landlordEmail;
        listingID = id;
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

    public String setExpirationDate(int period){
        System.out.println(period);
        expirationDate = Calendar.getInstance();
        expirationDate.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE) + period);
        return getDateString(expirationDate);
    }

    public String getDateString(Calendar date){
        return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
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

    public Calendar getCreationDate() {
        return creationDate;
    }
}
