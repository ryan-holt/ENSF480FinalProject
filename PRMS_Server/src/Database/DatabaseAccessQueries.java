package Database;

public interface DatabaseAccessQueries {

    static final String SQL_GET_USER = "SELECT * FROM users WHERE username =? and password =?";
    static final String SQL_GET_USER_BY_USERNAME = "SELECT * FROM users WHERE username =?";
    static final String SQL_ADD_USER = "INSERT INTO users (username, password, firstName, " +
                                                            "lastName, userType, address, email) values(?,?,?,?,?,?,?)";
    static final String SQL_SEARCH_LISTINGS = "SELECT * FROM listings WHERE bedrooms =? AND bathrooms =? AND type =? AND quadrant =? AND furnished =?";
    static final String SQL_GET_ALL_LISTINGS = "SELECT * FROM listings";
    static final String SQL_ADD_LISTING = "INSERT INTO listings (bedrooms, bathrooms, type, quadrant, furnished, state, fee, landlordEmail, listingID) values(?,?,?,?,?,?,?,?,?)";
    static final String SQL_GET_LISTINGS_BY_LANDLORD = "SELECT * FROM listings WHERE landlordEmail = ?";
    static final String SQL_GET_LISTING_BY_ID = "SELECT * FROM listings WHERE listingID = ?";
    static final String SQL_ACTIVATE_LISTING = "UPDATE listings SET state = 'Active' WHERE listingID = ?";
    static final String SQL_EDIT_LISTING = "UPDATE listings SET type = ?, bedrooms =?, bathrooms=?, quadrant=?, furnished=?, state=? WHERE listingID=?";
    static final String SQL_GET_ALL_USERS = "SELECT * FROM users";

}
