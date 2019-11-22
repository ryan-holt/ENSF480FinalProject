package Database;

import Utils.Address;
import Utils.Listing;
import Utils.Name;
import Utils.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseModel implements DatabaseAccessQueries, Messages{

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

    public ArrayList<Listing> queryListings(ArrayList<String> listingsQuery){
        ArrayList<Listing> listings = queryAllListings();

        listings = filterListingsByBedroom(listings, listingsQuery.get(0));
        listings = filterListingsByBathroom(listings, listingsQuery.get(1));
        listings = filterListingsByType(listings, listingsQuery.get(2));
        listings = filterListingsByQuadrant(listings, listingsQuery.get(3));
        listings = filterListingsByFurnishing(listings, listingsQuery.get(4));

        return listings;
    }

    public ArrayList<Listing> queryAllListings(){
        ArrayList<Listing> listings = new ArrayList<>();

        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_GET_ALL_LISTINGS)) {
            try (ResultSet rs = pStmt.executeQuery()) {
                while (rs.next()) {
                    listings.add(new Listing(rs.getString("type"),
                                             rs.getInt("bedrooms"),
                                             rs.getInt("bathrooms"),
                                             rs.getBoolean("furnished"),
                                             rs.getString("quadrant"),
                                             rs.getString("state"),
                                             rs.getDouble("fee"),
                                             rs.getString("landlordEmail")));
                }
            }

            return listings;
        } catch (SQLException e) {
            System.out.println("Getting items from DB error");
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Listing> filterListingsByBedroom(ArrayList<Listing> listings, String bedrooms){
        if(bedrooms.equals(NO_INPUT)) return listings;
        ArrayList<Listing> filteredListings = new ArrayList<>();

        for(Listing listing: listings){
            if(listing.getNumOfBedrooms() == Integer.parseInt(bedrooms)){
                filteredListings.add(listing);
            }
        }

        return filteredListings;
    }

    public ArrayList<Listing> filterListingsByBathroom(ArrayList<Listing> listings, String bathrooms){
        if(bathrooms.equals(NO_INPUT)) return listings;
        ArrayList<Listing> filteredListings = new ArrayList<>();

        for(Listing listing: listings){
            if(listing.getNumOfBathrooms() == Integer.parseInt(bathrooms)){
                filteredListings.add(listing);
            }
        }

        return filteredListings;
    }

    public ArrayList<Listing> filterListingsByType(ArrayList<Listing> listings, String type){
        if(type.equals(NO_INPUT)) return listings;
        ArrayList<Listing> filteredListings = new ArrayList<>();

        for(Listing listing: listings){
            if(listing.getType().equals(type)){
                filteredListings.add(listing);
            }
        }

        return filteredListings;
    }

    public ArrayList<Listing> filterListingsByQuadrant(ArrayList<Listing> listings, String quadrant){
        if(quadrant.equals(NO_INPUT)) return listings;
        ArrayList<Listing> filteredListings = new ArrayList<>();

        for(Listing listing: listings){
            if(listing.getQuadrant().equals(quadrant)){
                filteredListings.add(listing);
            }
        }

        return filteredListings;
    }

    public ArrayList<Listing> filterListingsByFurnishing(ArrayList<Listing> listings, String furnishing){
        if(furnishing.equals(NO_INPUT)) return listings;

        boolean furnished;
        if(furnishing.equals("Yes")){
            furnished = true;
        }else{
            furnished = false;
        }

        ArrayList<Listing> filteredListings = new ArrayList<>();

        for(Listing listing: listings){
            if(listing.isFurnished() == furnished){
                filteredListings.add(listing);
            }
        }

        return filteredListings;
    }
}
