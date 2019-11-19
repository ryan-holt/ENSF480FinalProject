package Database;

public interface DatabaseAccessQueries {

    static final String SQL_GET_USER = "SELECT * FROM users WHERE username =? and password =?";

}
