package Database;

public interface DatabaseAccessQueries {

    static final String SQL_GET_USER = "SELECT * FROM users WHERE username =? and password =?";
    static final String SQL_ADD_USER = "INSERT INTO users (username, password, firstName, " +
                                                            "lastName, houseNum, streetName," +
                                                            "userType, quadrant) values(?,?,?,?,?,?,?,?)";

}
