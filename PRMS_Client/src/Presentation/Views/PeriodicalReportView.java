package Presentation.Views;

import Utils.Listing;
import Utils.User;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Gary Wu
 */
public class PeriodicalReportView extends javax.swing.JFrame {

    private javax.swing.JButton closeReportButton;
    private javax.swing.JLabel periodicalReportTitleLabel;
    private javax.swing.JLabel numHousesListedLabel;
    private javax.swing.JLabel dateLab;
    private javax.swing.JLabel numHousesRentedLabel;
    private javax.swing.JLabel numOfActiveListingsCurrentlyLabel;
    private javax.swing.JLabel listOfhouseRentLab;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JTable listTable;
    private DefaultTableModel listTableModel;
    private int numOfActiveListings;
    private int numOfHousesListed;
    private int numOfRentedListings;

    public PeriodicalReportView(int activeListings, int housesListed, int rentedListings, ArrayList<Listing> rentedListingsArray, ArrayList<User> landlords){
        updateTableModel(rentedListingsArray, landlords);
        numOfActiveListings = activeListings;
        numOfHousesListed = housesListed;
        numOfRentedListings = rentedListings;
        initComponents();
    }

    /**
     * Creates new form PeriodicalReportView
     */
    public PeriodicalReportView() {
        numOfActiveListings = 0;
        numOfHousesListed = 0;
        numOfRentedListings = 0;
        listTableModel = new DefaultTableModel();
        initComponents();
    }

    public void display() {
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        periodicalReportTitleLabel = new javax.swing.JLabel();
        numHousesListedLabel = new javax.swing.JLabel();
        dateLab = new javax.swing.JLabel();
        numHousesRentedLabel = new javax.swing.JLabel();
        numOfActiveListingsCurrentlyLabel = new javax.swing.JLabel();
        listOfhouseRentLab = new javax.swing.JLabel();
        tableScrollPane = new javax.swing.JScrollPane();
        listTable = new javax.swing.JTable();
        closeReportButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        periodicalReportTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        periodicalReportTitleLabel.setText("Periodical Report");

        numHousesListedLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numHousesListedLabel.setText("Total number of houses listed in the period: " + numOfHousesListed);

        dateLab.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dateLab.setText("for Date - Date");

        numHousesRentedLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numHousesRentedLabel.setText("Total number of houses rented in the period: " + numOfRentedListings);

        numOfActiveListingsCurrentlyLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numOfActiveListingsCurrentlyLabel.setText("Total number of active listings currently: " + numOfActiveListings);

        listOfhouseRentLab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        listOfhouseRentLab.setText("List of houses rented in the period ");

        listTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        listTable.setModel(listTableModel);
        tableScrollPane.setViewportView(listTable);

        closeReportButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        closeReportButton.setText("Close Report");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(numHousesRentedLabel)
                                        .addComponent(numHousesListedLabel)
                                        .addComponent(numOfActiveListingsCurrentlyLabel))
                                .addContainerGap(225, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(periodicalReportTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(10, 10, 10)
                                                                .addComponent(dateLab)))
                                                .addGap(174, 174, 174))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(listOfhouseRentLab)
                                                .addGap(150, 150, 150))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(46, 46, 46))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(closeReportButton)
                                                .addGap(212, 212, 212))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(periodicalReportTitleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateLab)
                                .addGap(18, 18, 18)
                                .addComponent(numHousesListedLabel)
                                .addGap(18, 18, 18)
                                .addComponent(numHousesRentedLabel)
                                .addGap(18, 18, 18)
                                .addComponent(numOfActiveListingsCurrentlyLabel)
                                .addGap(18, 18, 18)
                                .addComponent(listOfhouseRentLab)
                                .addGap(18, 18, 18)
                                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(closeReportButton)
                                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void updateTableModel(ArrayList<Listing> listings, ArrayList<User> landlords){
        String[][] data = new String[listings.size()][3];
        String[] header = {"Landlord Name", "House Address", "House ID"};

        Listing listing;
        for(int i = 0; i < listings.size(); i++){
            listing = listings.get(i);
            data[i][0] = landlords.get(i).getName().toString();
            data[i][1] = listing.getState();
            data[i][2] = String.valueOf(listing.getListingID());
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, header){
            public boolean isCellEditable(int rowIndex, int mColIndex){
                return false;
            }
        };
        listTableModel = tableModel;

        repaint();
    }

    // Action Listeners
    /**
     * adds action listener to the edit listing button
     * @param listenForCloseReportButton
     */
    public void addCloseReportButtonListener(ActionListener listenForCloseReportButton){
        closeReportButton.addActionListener(listenForCloseReportButton);
    }
}
