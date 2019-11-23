package Database;

import Utils.*;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseModel implements DatabaseAccessQueries, Messages, UserTypes {

    private Connection myConnection;
    private static int listingID = 5;
    private Fee fee;

    public DatabaseModel(Connection c){
        myConnection = c;
        fee = new Fee(5, 7);
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
                                    rs.getString("address"),
                                    rs.getString("email"));
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
                            rs.getString("address"),
                            rs.getString("email"));
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
                pStmt.setString(5, user.getUserType());
                pStmt.setString(6, user.getAddress());
                pStmt.setString(7, user.getUsername() + "@gmail.com");
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
                                             new Fee(rs.getDouble("fee")),
                                             rs.getString("landlordEmail"),
                                             rs.getInt("listingID")));
                }
            }

            return listings;
        } catch (SQLException e) {
            System.out.println("Getting items from DB error");
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> queryAllUsers(){
        ArrayList<User> users = new ArrayList<>();

        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_GET_ALL_USERS)) {
            try (ResultSet rs = pStmt.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(rs.getString("username"),
                                        rs.getString("password"),
                                        new Name(rs.getString("firstName"), rs.getString("lastName")),
                                        rs.getString("userType"),
                                        rs.getString("address"),
                                        rs.getString("email")));
                }
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Getting items from DB error");
            e.printStackTrace();
        }
        return null;
    }

    public void editListing(Listing listingToBeEdited){
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_EDIT_LISTING)) {
            pStmt.setString(1, listingToBeEdited.getType());
            pStmt.setInt(2, listingToBeEdited.getNumOfBedrooms());
            pStmt.setInt(3, listingToBeEdited.getNumOfBathrooms());
            pStmt.setString(4, listingToBeEdited.getQuadrant());
            pStmt.setBoolean(5, listingToBeEdited.isFurnished());
            pStmt.setString(6, listingToBeEdited.getState());
            pStmt.setInt(7, listingToBeEdited.getListingID());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Listing> queryListingsByLandlord(String landlordEmail){
        ArrayList<Listing> listings = new ArrayList<>();

        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_GET_LISTINGS_BY_LANDLORD)) {
            pStmt.setString(1, landlordEmail);
            try (ResultSet rs = pStmt.executeQuery()) {
                while (rs.next()) {
                    listings.add(new Listing(rs.getString("type"),
                            rs.getInt("bedrooms"),
                            rs.getInt("bathrooms"),
                            rs.getBoolean("furnished"),
                            rs.getString("quadrant"),
                            rs.getString("state"),
                            new Fee(rs.getDouble("fee")),
                            rs.getString("landlordEmail"),
                            rs.getInt("listingID")));
                }
            }

            return listings;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void activateListing(int id){
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_ACTIVATE_LISTING)) {
            pStmt.setInt(1, id);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double queryListingFeeByID(int id){
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_GET_LISTING_BY_ID)) {
            pStmt.setInt(1, id);
            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("fee");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void addListing(Listing listing){
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_ADD_LISTING)) {
            pStmt.setInt(1, listing.getNumOfBedrooms());
            pStmt.setInt(2, listing.getNumOfBathrooms());
            pStmt.setString(3, listing.getType());
            pStmt.setString(4, listing.getQuadrant());
            pStmt.setBoolean(5, listing.isFurnished());
            pStmt.setString(6, listing.getState());
            pStmt.setDouble(7, fee.getFeeAmount());
            pStmt.setString(8, listing.getLandlordEmail());
            pStmt.setInt(9, listingID);
            listingID++;
            pStmt.executeUpdate();

            System.out.println("New listing created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int queryNumOfActiveListings(){
        int numOfActiveListings = 0;

        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_GET_ACTIVE_LISTINGS)) {
            try (ResultSet rs = pStmt.executeQuery()) {
                while (rs.next()) {
                    numOfActiveListings++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numOfActiveListings;
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
        System.out.println(furnishing);
        boolean furnished = furnishing.equals("Furnished");

        ArrayList<Listing> filteredListings = new ArrayList<>();

        for(Listing listing: listings){
            if(listing.isFurnished() == furnished){
                filteredListings.add(listing);
            }
        }

        return filteredListings;
    }

    public Fee getFee() {
        return fee;
    }
}
