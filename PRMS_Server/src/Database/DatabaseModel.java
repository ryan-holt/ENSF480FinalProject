package Database;

import Utils.Address;
import Utils.Name;
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
                                    new Name(rs.getString("firstName"), rs.getString("lastName")),
                                    rs.getString("userType"),
                                    new Address(rs.getInt("houseNum"), rs.getString("streetName"), rs.getString("quadrant")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByUsername(User user){
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_GET_USER_BY_USERNAME)) {
            pStmt.setString(1, user.getUsername());
            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString("username"),
                            rs.getString("password"),
                            new Name(rs.getString("firstName"), rs.getString("lastName")),
                            rs.getString("userType"),
                            new Address(rs.getInt("houseNum"), rs.getString("streetName"), rs.getString("quadrant")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createUser(User user){
        User queriedUser = getUserByUsername(user);

        if(queriedUser == null) {   // user does not exists already
            try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_ADD_USER)) {
                pStmt.setString(1, user.getUsername());
                pStmt.setString(2, user.getPassword());
                pStmt.setString(3, user.getName().getFirstName());
                pStmt.setString(4, user.getName().getLastName());
                pStmt.setInt(5, user.getAddress().getHouseNum());
                pStmt.setString(6, user.getAddress().getStreetName());
                pStmt.setString(7, user.getUserType());
                pStmt.setString(8, user.getAddress().getQuadrant());
                pStmt.executeUpdate();

                System.out.println("New User " + user.getUsername() + " created!");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("User " + queriedUser.getUsername() + " already exists...");
        }

        return false;
    }

}
