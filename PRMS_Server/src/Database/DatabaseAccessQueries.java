package Database;

public interface DatabaseAccessQueries {

    static final String SQL_GET_USER = "SELECT * FROM users WHERE username =? and password =?";
    static final String SQL_GET_USER_BY_USERNAME = "SELECT * FROM users WHERE username =?";
    static final String SQL_ADD_USER = "INSERT INTO users (username, password, firstName, " +
                                                            "lastName, houseNum, streetName," +
                                                            "userType, quadrant) values(?,?,?,?,?,?,?,?)";
    static final String SQL_SEARCH_LISTINGS = "SELECT * FROM listings WHERE bedrooms =? AND bathrooms =? AND type =? AND quadrant =? AND furnished =?";
    static final String SQL_GET_ALL_LISTINGS = "SELECT * FROM listings";
    public static final String SQL_ADD_LISTING = "INSERT INTO listings (bedrooms, bathrooms, type, quadrant, furnished, state, fee, landlordEmail) values(?,?,?,?,?,?,?,?)";

}
