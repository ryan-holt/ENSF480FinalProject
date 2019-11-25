package Database;

import Utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class DatabaseModel implements DatabaseAccessQueries, Messages, UserTypes, Observable {

    private Connection myConnection;
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
                                    rs.getString("email"), this,
                                    rs.getBoolean("subscribed"));
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
                            rs.getString("email"), this,
                            rs.getBoolean("subscribed"));
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
                pStmt.setString(7, user.getEmail());
                pStmt.executeUpdate();

                System.out.println("New User " + user.getUsername() + " created!");

                if(user.getUserType().equals(REG_RENTER.toLowerCase())) {
                    addObserver(user);
                }

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("User " + queriedUser.getUsername() + " already exists...");
        }

        return false;
    }

    public ArrayList<User> querySubscribedUsers(){
        ArrayList<User> subscribedUsers = new ArrayList<>();

        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_GET_SUBSCRIBED_USERS)){
            try (ResultSet rs = pStmt.executeQuery()) {
                while (rs.next()) {
                    subscribedUsers.add(new User(rs.getString("username"),
                            rs.getString("password"),
                            new Name(rs.getString("firstName"), rs.getString("lastName")),
                            rs.getString("userType"),
                            rs.getString("address"),
                            rs.getString("email"), this,
                            rs.getBoolean("subscribed")));
                }
            }
            return subscribedUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Listing> querySearchListings(ArrayList<String> listingsQuery, User user){
        if(user != null) {  // user == null if user is regular renter
            saveSearchQuery(listingsQuery, user);
        }
        ArrayList<Listing> listings = queryAllListings();

        listings = filterListingsByBedroom(listings, listingsQuery.get(0));
        listings = filterListingsByBathroom(listings, listingsQuery.get(1));
        listings = filterListingsByType(listings, listingsQuery.get(2));
        listings = filterListingsByQuadrant(listings, listingsQuery.get(3));
        listings = filterListingsByFurnishing(listings, listingsQuery.get(4));

        return listings;
    }

    public void saveSearchQuery(ArrayList<String> listingsQuery, User user){
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_SAVE_QUERY)) {
            pStmt.setString(1, user.getEmail());
            pStmt.setString(2, listingsQuery.get(2));
            if(!listingsQuery.get(0).equals(NO_INPUT))
                pStmt.setInt(3, Integer.parseInt(listingsQuery.get(0)));
            else
                pStmt.setInt(3, -1);
            if(!listingsQuery.get(1).equals(NO_INPUT))
                pStmt.setInt(4, Integer.parseInt(listingsQuery.get(1)));
            else
                pStmt.setInt(4, -1);
            pStmt.setBoolean(5, listingsQuery.get(4).equals("Furnished"));
            pStmt.setString(6, listingsQuery.get(3));
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                                             rs.getInt("listingID"),
                                             rs.getString("address")));
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
                                        rs.getString("email"), this,
                                        rs.getBoolean("subscribed")));
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
        if(listingToBeEdited.getState().equals("Rented")){
            setRentedDate(listingToBeEdited.getListingID());
        }

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

    public void setRentedDate(int listingID){
        Calendar rentedDate = Calendar.getInstance();
        String rentedDateString = rentedDate.get(Calendar.MONTH) + "/" + rentedDate.get(Calendar.DATE) + "/" + rentedDate.get(Calendar.YEAR);

        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_SET_RENTED_DATE)) {
            pStmt.setString(1, rentedDateString);
            pStmt.setInt(2, listingID);
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
                            rs.getInt("listingID"),
                            rs.getString("creationDate"),
                            rs.getString("rentedDate"),
                            rs.getString("expirationDate"),
                            rs.getString("address")));
                }
            }

            return listings;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String email){
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_GET_USER_BY_EMAIL)) {
            pStmt.setString(1, email);
            try (ResultSet rs = pStmt.executeQuery()) {
                while (rs.next()) {
                    return new User(rs.getString("username"),
                                    rs.getString("password"),
                                    new Name(rs.getString("firstName"), rs.getString("lastName")),
                                    rs.getString("userType"),
                                    rs.getString("address"),
                                    rs.getString("email"), this,
                                    rs.getBoolean("subscribed"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void expireListings(){
        Calendar todayDate = Calendar.getInstance();
        String todayDateString = todayDate.get(Calendar.MONTH) + "/" + todayDate.get(Calendar.DATE) + "/" + todayDate.get(Calendar.YEAR);
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_EXPIRE_LISTINGS)) {
            pStmt.setString(1, todayDateString);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activateListing(int id){
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_ACTIVATE_LISTING)) {
            pStmt.setInt(1, id);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        updateExpirationDate(id);
    }

    public void updateExpirationDate(int id){
        Listing listing = queryListingByID(id);
        String expirationDate = listing.setExpirationDate(fee.getFeePeriod());

        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_SET_EXPIRATION_DATE)) {
            pStmt.setString(1, expirationDate);
            pStmt.setInt(2, id);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Listing queryListingByID(int id){
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_GET_LISTING_BY_ID)) {
            pStmt.setInt(1, id);
            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    return new Listing(rs.getString("type"),
                            rs.getInt("bedrooms"),
                            rs.getInt("bathrooms"),
                            rs.getBoolean("furnished"),
                            rs.getString("quadrant"),
                            rs.getString("state"),
                            new Fee(rs.getDouble("fee")),
                            rs.getString("landlordEmail"),
                            rs.getInt("listingID"),
                            rs.getString("address"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createListing(Listing listing){
        notifyAllObservers(listing);

        ArrayList<Listing> allListings = queryAllListings();
        int newListingID = 1;
        if(allListings != null){
            int largestID = -1;
            Listing currListing;
            for(int i = 0; i < allListings.size(); i++){
                currListing = allListings.get(i);
                if(currListing.getListingID() > largestID){
                    largestID = currListing.getListingID();
                }
            }
            newListingID = largestID + 1;
        }

        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_ADD_LISTING)) {
            pStmt.setInt(1, listing.getNumOfBedrooms());
            pStmt.setInt(2, listing.getNumOfBathrooms());
            pStmt.setString(3, listing.getType());
            pStmt.setString(4, listing.getQuadrant());
            pStmt.setBoolean(5, listing.isFurnished());
            pStmt.setString(6, listing.getState());
            pStmt.setDouble(7, fee.getFeeAmount());
            pStmt.setString(8, listing.getLandlordEmail());
            pStmt.setInt(9, newListingID);
            pStmt.setString(10, listing.getDateString(listing.getCreationDate()));
            pStmt.setString(11, listing.getAddress());
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

    public int queryNumOfHousesListedInPeriod(ArrayList<String> dates){
        String startPeriodDate = dates.get(0);
        String endPeriodDate = dates.get(1);

        ArrayList<Listing> allListings = queryAllListings();
        ArrayList<Listing> filteredListings = new ArrayList<>();

        for(Listing currListing: allListings){
            if(currListing.isListingInPeriod(startPeriodDate, endPeriodDate)){
                filteredListings.add(currListing);
            }
        }

        return filteredListings.size();
    }

    public ArrayList<Listing> queryHousesRentedInPeriod(ArrayList<String> dates){
        String startPeriodDate = dates.get(0);
        String endPeriodDate = dates.get(1);

        ArrayList<Listing> allListings = queryAllListings();
        ArrayList<Listing> filteredListings = new ArrayList<>();

        for(Listing currListing: allListings){
            if(currListing.isListingInPeriod(startPeriodDate, endPeriodDate) && currListing.getState().equals("Rented")){
                filteredListings.add(currListing);
            }
        }

        return filteredListings;
    }

    /******* FILTERING *******/

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

    @Override
    public void addObserver(User user) {
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_SUBSCRIBE_USER)) {
            pStmt.setBoolean(1, true);
            pStmt.setString(2, user.getEmail());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeObserver(User user) {
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_UNSUBSCRIBE_USER)) {
            pStmt.setBoolean(1, false);
            pStmt.setString(2, user.getEmail());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyAllObservers(Listing newListing) {
        ArrayList<String> usersWithMatchingQueries = getUsersWithMatchingQueries(newListing);
        ArrayList<User> subscribedUsersToSendEmailTo = new ArrayList<>();
        for(String email: usersWithMatchingQueries){
            if(getUserByEmail(email).isSubscribed())
                subscribedUsersToSendEmailTo.add(getUserByEmail(email));
        }

        for(User user: subscribedUsersToSendEmailTo){
            System.out.println(user.getEmail());
            user.update(newListing);
        }
    }

    public ArrayList<String> getUsersWithMatchingQueries(Listing newListing) {
        ArrayList<String> userEmails = new ArrayList<>();
        try (PreparedStatement pStmt = myConnection.prepareStatement(SQL_GET_ALL_QUERIES)) {
            try (ResultSet rs = pStmt.executeQuery()) {
                while (rs.next()) {
                    String queryType = rs.getString("type");
                    int queryBedrooms = rs.getInt("bedrooms");
                    int queryBathrooms = rs.getInt("bathrooms");
                    boolean queryFurnishing = rs.getBoolean("furnished");
                    String queryQuadrant = rs.getString("quadrant");

                    if(!queryType.equals(newListing.getType()) && !queryType.equals(NO_INPUT)){
                        continue;
                    }
                    if(queryBedrooms != newListing.getNumOfBedrooms() && queryBedrooms != -1){
                        continue;
                    }
                    if(queryBathrooms != newListing.getNumOfBathrooms() && queryBathrooms != -1){
                        continue;
                    }
                    if(!queryQuadrant.equals(newListing.getQuadrant()) && !queryQuadrant.equals(NO_INPUT)){
                        continue;
                    }

                    if(!userEmails.contains(rs.getString("email")))
                        userEmails.add(rs.getString("email"));
                }
            }

            return userEmails;
        } catch (SQLException e) {
            System.out.println("Getting items from DB error");
            e.printStackTrace();
        }

        return null;
    }

    public Fee getFee() {
        return fee;
    }
}
