package Database;

import Utils.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseModel implements DatabaseAccessQueries{

    private Connection myConnection;

    public DatabaseModel(Connection c){
        myConnection = c;
    }

    /**
     * Checks to see if the user entered is correct
     *
     * @param user the user object to be verified
     * @return returns true if the User exists otherwise false
     */
    public User verifyUser(User user) {
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_GET_USER)) {
            pStmt.setString(1, user.getUsername());
            pStmt.setString(2, user.getPassword());
            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("User is logged in");
                    return new User(rs.getString("username"),
                                    rs.getString("password"),
                                    rs.getString("firstName"),
                                    rs.getString("lastName"),
                                    rs.getString("userType"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
