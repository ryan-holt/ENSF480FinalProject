package Database;

public interface DatabaseAccessQueries {

    // Users table
    static final String SQL_GET_USER = "SELECT * FROM users WHERE username =? and password =?";
    static final String SQL_GET_USER_BY_USERNAME = "SELECT * FROM users WHERE username =?";
    static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email =?";
    static final String SQL_GET_SUBSCRIBED_USERS = "SELECT * FROM users WHERE subscribed = TRUE";
    static final String SQL_ADD_USER = "INSERT INTO users (username, password, firstName, " +
                                                            "lastName, userType, address, email) values(?,?,?,?,?,?,?)";
    static final String SQL_SUBSCRIBE_USER = "UPDATE users SET subscribed = ? WHERE email = ?";
    static final String SQL_UNSUBSCRIBE_USER = "UPDATE users SET subscribed = ? WHERE email = ?";
    // Listings table
    static final String SQL_SEARCH_LISTINGS = "SELECT * FROM listings WHERE bedrooms =? AND bathrooms =? AND type =? AND quadrant =? AND furnished =?";
    static final String SQL_GET_ALL_LISTINGS = "SELECT * FROM listings";
    static final String SQL_ADD_LISTING = "INSERT INTO listings (bedrooms, bathrooms, type, quadrant, furnished, state, fee, landlordEmail, listingID, creationDate) values(?,?,?,?,?,?,?,?,?,?)";
    static final String SQL_GET_LISTINGS_BY_LANDLORD = "SELECT * FROM listings WHERE landlordEmail = ?";
    static final String SQL_GET_LISTING_BY_ID = "SELECT * FROM listings WHERE listingID = ?";
    static final String SQL_GET_ALL_RENTED_LISTINGS = "SELECT * FROM listings WHERE state = 'Rented'";
    static final String SQL_ACTIVATE_LISTING = "UPDATE listings SET state = 'Active' WHERE listingID = ?";
    static final String SQL_EDIT_LISTING = "UPDATE listings SET type = ?, bedrooms =?, bathrooms=?, quadrant=?, furnished=?, state=? WHERE listingID=?";
    static final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    static final String SQL_GET_ACTIVE_LISTINGS = "SELECT * FROM listings WHERE state = 'Active'";
    static final String SQL_SET_RENTED_DATE = "UPDATE listings SET rentedDate = ? WHERE listingID = ?";
    static final String SQL_SET_EXPIRATION_DATE = "UPDATE listings SET expirationDate = ? WHERE listingID = ?";
    static final String SQL_EXPIRE_LISTINGS = "UPDATE listings SET state = 'Not Active' WHERE expirationDate = ?";

    // Queries table
    static final String SQL_SAVE_QUERY = "INSERT INTO queries (email, type, bedrooms, bathrooms, furnished, quadrant) values (?,?,?,?,?,?)";
    static final String SQL_GET_ALL_QUERIES = "SELECT * FROM queries";

}
