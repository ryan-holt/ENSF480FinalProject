package Domain.Controllers;

import Presentation.Views.ManagerMainView;
import Presentation.Views.PeriodicalReportView;
import Utils.Listing;
import Utils.User;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class ManagerMainController extends Controller implements Messages{

   private ManagerMainView managerMainView;

   public ManagerMainController(ManagerMainView mmv, ClientCommunicationController ccc){
       super(ccc);
       managerMainView = mmv;

       managerMainView.addChangeFeeListener(e -> changeFeeListen());
       managerMainView.addEditListingStateListener(e -> editListingStateListen());
       managerMainView.addGetEntityInfoListener(e -> getEntityInfoListen());
       managerMainView.addGetReportListener(e -> getReportListen());
       managerMainView.addLogoutListener(e -> logoutListen());
   }

   public void changeFeeListen(){
       double newFeeAmount = doubleInputPrompt("Please enter new fee amount:");
       int newFeePeriod = intInputPrompt("Please enter new fee period:");

       try {
           // Send action to server
           clientCommunicationController.getSocketOut().writeObject(EDIT_FEE);
           // Send fee amount and period to server
           clientCommunicationController.getSocketOut().writeObject(newFeeAmount);
           clientCommunicationController.getSocketOut().writeObject(newFeePeriod);

           JOptionPane.showMessageDialog(null, "Fee changed!");
       }catch (IOException e){
           e.printStackTrace();
       }
   }

   public void editListingStateListen(){
       getListingViewForManager();
   }

   public void getEntityInfoListen(){
       String whichEntity = "Get entity info:";
       String[] entities = new String[]{"User", "Listing"};

       String editState = (String) JOptionPane.showInputDialog(null, "Select Entity:",
               whichEntity, JOptionPane.QUESTION_MESSAGE, null,
               entities, entities[0]);

       if(editState.equals("Listing")){
           getListingViewForManager();
       }else{
           getUsersViewForManager();
       }
   }

   public void getReportListen(){
       clientCommunicationController.getMainController().hideView();

       // Get input from user
       ArrayList<String> dates = getPeriodicalReportDates();
       if(dates == null){
           clientCommunicationController.getMainController().displayView();
           return;
       }

       try {
           // Send action to server
           clientCommunicationController.getSocketOut().writeObject(GET_REPORT_DATA);
           // Send dates to server
            clientCommunicationController.getSocketOut().writeObject(dates);
           // Receive number of houses listed in period
           int numOfHousesListed = (Integer)clientCommunicationController.getSocketIn().readObject();
           // Receive number of houses rented in period
           ArrayList<Listing> rentedListingsInPeriod = (ArrayList<Listing>) clientCommunicationController.getSocketIn().readObject();
           // Receive total number of current active listings
           int currActiveListings = (Integer)clientCommunicationController.getSocketIn().readObject();
           // Update Periodical Report View
           clientCommunicationController.getPeriodicalReportController().setPeriodicalReportView(new PeriodicalReportView(currActiveListings, numOfHousesListed, rentedListingsInPeriod.size(), rentedListingsInPeriod));
           // Display View
           clientCommunicationController.getPeriodicalReportController().displayView();
       }catch (IOException | ClassNotFoundException e) {
           e.printStackTrace();
       }
   }

    public void getListingViewForManager(){
        clientCommunicationController.getMainController().hideView();
        try {
            // Send action to server
            clientCommunicationController.getSocketOut().writeObject(GET_ALL_LISTINGS);
            // Receive listings from server
            ArrayList<Listing> listings = (ArrayList<Listing>) clientCommunicationController.getSocketIn().readObject();
            clientCommunicationController.getListingController().getListingView().updateListingTable(listings);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        clientCommunicationController.getListingController().getListingView().updateListingViewForManager();
        clientCommunicationController.getListingController().displayView();
    }

    public void getUsersViewForManager(){
        clientCommunicationController.getMainController().hideView();
        try{
            // Send action to server
            clientCommunicationController.getSocketOut().writeObject(GET_ALL_USERS);
            // Receive listings from server
            ArrayList<User> users = (ArrayList<User>) clientCommunicationController.getSocketIn().readObject();
            clientCommunicationController.getUserController().getUserView().updateUserTable(users);
            clientCommunicationController.getUserController().displayView();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void logoutListen(){
        System.exit(1);
    }

    public ArrayList<String> getPeriodicalReportDates(){
       ArrayList<String> dates = new ArrayList<>();

       JTextField startDateField = new JTextField();
       JTextField endDateField = new JTextField();
       Object[] message = {
               "Period Start Date: (MM/DD/YY)", startDateField,
               "Period End Date: (MM/DD/YY)", endDateField,
       };
       int option = JOptionPane.showConfirmDialog(null, message, "Periodical Report Criteria", JOptionPane.OK_CANCEL_OPTION);
       if (option == JOptionPane.OK_OPTION)
       {
           dates.add(startDateField.getText());
           dates.add(endDateField.getText());
       }else{
           return null;
       }

       return dates;
    }

    @Override
    public void displayView() {
        managerMainView.display();
    }

    @Override
    public void hideView() {
        managerMainView.hide();
    }

    /**
     * Gets a double input from the user with error checking
     *
     * @param n message being displayed for input
     * @return integer entered by user
     */
    public double doubleInputPrompt(String n) {
        String input = null;
        double num = 0;
        while (input == null || num < 0) {

            try {
                input = JOptionPane.showInputDialog(n);
                num = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Try again!");
                input = null;
            }

        }

        return num;
    }

    /**
     * Gets an integer input from the user with error checking
     *
     * @param n message being displayed for input
     * @return integer entered by user
     */
    public int intInputPrompt(String n) {
        String input = null;
        int num = 0;
        while (input == null || num < 0) {

            try {
                input = JOptionPane.showInputDialog(n);
                num = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Try again!");
                input = null;
            }

        }

        return num;
    }
}
