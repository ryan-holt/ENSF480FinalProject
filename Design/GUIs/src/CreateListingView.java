import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author Gary Wu
 */
public class CreateListingView extends javax.swing.JFrame {

    private JButton doneButton;
    private JComboBox<String> houseTypeJBox;
    private JComboBox<String> numBedRoomsBox;
    private JComboBox<String> numBathroomsBox;
    private JComboBox<String> FurnishedBox;
    private JComboBox<String> cityQuadBox;
    private JLabel createListingLabel;
    private JLabel houseTypeLabel;
    private JLabel numBedroomsLabel;
    private JLabel numBathroomLabel;
    private JLabel Furnished;
    private JLabel cityQuadLabel;
    private JLabel doneStatemeneetLabel;

    /**
     * Creates new form listing
     */
    public CreateListingView() {
        initComponents();
    }

    /**
     * Inits all the different components
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        createListingLabel = new JLabel();
        houseTypeJBox = new JComboBox<>();
        houseTypeLabel = new JLabel();
        numBedroomsLabel = new JLabel();
        numBedRoomsBox = new JComboBox<>();
        numBathroomLabel = new JLabel();
        numBathroomsBox = new JComboBox<>();
        Furnished = new JLabel();
        FurnishedBox = new JComboBox<>();
        cityQuadLabel = new JLabel();
        cityQuadBox = new JComboBox<>();
        doneButton = new JButton();
        doneStatemeneetLabel = new JLabel();

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

        numBedRoomsBox.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        numBedRoomsBox.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5+" }));
        numBedRoomsBox.addActionListener(this::jComboBox2ActionPerformed);

        numBathroomLabel.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        numBathroomLabel.setText("Number of Bathrooms");

        numBathroomsBox.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        numBathroomsBox.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5+" }));
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

        doneButton.setFont(new Font("Tahoma", Font.PLAIN, 14)); // NOI18N
        doneButton.setText("Done");

        doneStatemeneetLabel.setFont(new Font("Tahoma", Font.BOLD, 14)); // NOI18N
        doneStatemeneetLabel.setText("Click Done when finished");

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
                                                                .addComponent(numBedRoomsBox, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(createListingLabel))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(numBathroomLabel, GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(numBathroomsBox, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(doneStatemeneetLabel)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(cityQuadLabel, GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(cityQuadBox, GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 45, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(doneButton)
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
                                        .addComponent(numBedRoomsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(doneStatemeneetLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(doneButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    public void display() {
        setVisible(true);
    }


}
