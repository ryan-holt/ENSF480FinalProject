package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The CreateListingView holds the gui for the
 * create listing which is used by the landlord.
 * The main purpose is to create the GUI and
 * also the different buttons
 * @author Gary Wu
 * @vsince November 25, 2019
 */
public class CreateListingView extends javax.swing.JFrame {

    private JButton createListingButton;
    private JComboBox<String> houseTypeJBox;
    private JComboBox<String> numBedroomsBox;
    private JComboBox<String> numBathroomsBox;
    private JComboBox<String> FurnishedBox;
    private JComboBox<String> cityQuadBox;
    private JTextField addressField;
    private JLabel addressLabel;
    private JLabel createListingLabel;
    private JLabel houseTypeLabel;
    private JLabel numBedroomsLabel;
    private JLabel numBathroomLabel;
    private JLabel Furnished;
    private JLabel cityQuadLabel;
    private JLabel doneStatementLabel;


    /**
     * Creates new form listing
     */
    public CreateListingView() {
        initComponents();
    }

    /**
     * Inits all the different components of the
     * listing view
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        createListingLabel = new JLabel();
        houseTypeJBox = new JComboBox<>();
        houseTypeLabel = new JLabel();
        numBedroomsLabel = new JLabel();
        numBedroomsBox = new JComboBox<>();
        numBathroomLabel = new JLabel();
        numBathroomsBox = new JComboBox<>();
        Furnished = new JLabel();
        FurnishedBox = new JComboBox<>();
        cityQuadLabel = new JLabel();
        cityQuadBox = new JComboBox<>();
        createListingButton = new JButton();
        doneStatementLabel = new JLabel();
        addressLabel = new JLabel();
        addressField = new JTextField(10);


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        createListingLabel.setFont(new Font("Tahoma", Font.BOLD, 18)); // NOI18N
        createListingLabel.setText("Create Listing Form");

        houseTypeJBox.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        houseTypeJBox.setModel(new DefaultComboBoxModel<>(new String[] { "Apartment", "Attached", "Detached", "Townhouse" }));
        houseTypeJBox.addActionListener(this::jComboBox1ActionPerformed);

        houseTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        houseTypeLabel.setText("House type");

        numBedroomsLabel.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        numBedroomsLabel.setText("Number of Bedrooms");

        numBedroomsBox.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        numBedroomsBox.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        numBedroomsBox.addActionListener(this::jComboBox2ActionPerformed);

        numBathroomLabel.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        numBathroomLabel.setText("Number of Bathrooms");

        numBathroomsBox.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        numBathroomsBox.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        numBathroomsBox.addActionListener(this::jComboBox3ActionPerformed);

        Furnished.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        Furnished.setText("Furnished");

        FurnishedBox.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        FurnishedBox.setModel(new DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        FurnishedBox.addActionListener(this::jComboBox4ActionPerformed);

        cityQuadLabel.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        cityQuadLabel.setText("City Quadrant");

        cityQuadBox.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        cityQuadBox.setModel(new DefaultComboBoxModel<>(new String[] { "SW", "NW", "NE", "SE" }));
        cityQuadBox.addActionListener(this::jComboBox5ActionPerformed);

        addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        addressLabel.setText("Address:");

        addressField.setSize(50, 10);
        addressField.addActionListener(this::jComboBox6ActionPerformed);

        createListingButton.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        createListingButton.setText("Create Listing");

        doneStatementLabel.setFont(new Font("Tahoma", Font.BOLD, 14)); // NOI18N
        doneStatementLabel.setText("Click create when finished");

        javax.swing.GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(houseTypeLabel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(houseTypeJBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(Furnished, GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(FurnishedBox, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(numBedroomsLabel, GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(numBedroomsBox, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(createListingLabel))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(numBathroomLabel, GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(numBathroomsBox, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(addressField, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(doneStatementLabel)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(cityQuadLabel, GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(cityQuadBox, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 45, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(createListingButton)
                                .addGap(119, 119, 119))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(createListingLabel)
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(houseTypeJBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(houseTypeLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(numBedroomsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(numBedroomsLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(numBathroomsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(numBathroomLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(FurnishedBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Furnished))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cityQuadBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cityQuadLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addressLabel))
                                .addGap(18, 18, 18)
                                .addComponent(doneStatementLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(createListingButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
    }//GEN-LAST:event_jComboBox5ActionPerformed

    public void display() {
        setVisible(true);
    }

    /**
     * adds action listener to the search button
     * @param listenForCreateListingButton
     */
    public void addCreateListingListener(ActionListener listenForCreateListingButton){
        createListingButton.addActionListener(listenForCreateListingButton);
    }


    public boolean getFurnishedSelectorItem(){
        String furnished = (String)getFurnishedBox().getSelectedItem();
        if(furnished.equals("Yes"))
            return true;
        else
            return false;
    }

    // Getters and setters
    public JComboBox<String> getHouseTypeJBox() {
        return houseTypeJBox;
    }

    public JComboBox<String> getNumBedroomsBox() {
        return numBedroomsBox;
    }

    public JComboBox<String> getNumBathroomsBox() {
        return numBathroomsBox;
    }

    public JComboBox<String> getFurnishedBox() {
        return FurnishedBox;
    }

    public JComboBox<String> getCityQuadBox() {
        return cityQuadBox;
    }

    public JTextField getAddressField() {
        return addressField;
    }
}
