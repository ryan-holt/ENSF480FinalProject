package Utils;

import java.io.Serializable;

public class Address implements Serializable {

    private static final long serialVersionUID = 4L;
    private int houseNum;
    private String streetName;
    private String quadrant;

    public Address(int houseNum, String streetName, String quadrant){
        this.houseNum = houseNum;
        this.streetName = streetName;
        this.quadrant = quadrant;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getQuadrant() {
        return quadrant;
    }
}
