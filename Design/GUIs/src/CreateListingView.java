
/**
 *
 * @author Gary Wu
 */
public class CreateListingView extends javax.swing.JFrame {

    private javax.swing.JButton doneButton;
    private javax.swing.JComboBox<String> houseTypeJBox;
    private javax.swing.JComboBox<String> numBedRoomsBox;
    private javax.swing.JComboBox<String> numBathroomsBox;
    private javax.swing.JComboBox<String> FurnishedBox;
    private javax.swing.JComboBox<String> cityQuadBox;
    private javax.swing.JLabel createListingLabel;
    private javax.swing.JLabel houseTypeLabel;
    private javax.swing.JLabel numBedroomsLabel;
    private javax.swing.JLabel numBathroomLabel;
    private javax.swing.JLabel Furnished;
    private javax.swing.JLabel cityQuadLabel;
    private javax.swing.JLabel doneStatemeneetLabel;

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

        createListingLabel = new javax.swing.JLabel();
        houseTypeJBox = new javax.swing.JComboBox<>();
        houseTypeLabel = new javax.swing.JLabel();
        numBedroomsLabel = new javax.swing.JLabel();
        numBedRoomsBox = new javax.swing.JComboBox<>();
        numBathroomLabel = new javax.swing.JLabel();
        numBathroomsBox = new javax.swing.JComboBox<>();
        Furnished = new javax.swing.JLabel();
        FurnishedBox = new javax.swing.JComboBox<>();
        cityQuadLabel = new javax.swing.JLabel();
        cityQuadBox = new javax.swing.JComboBox<>();
        doneButton = new javax.swing.JButton();
        doneStatemeneetLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        createListingLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        createListingLabel.setText("Create Listing Form");

        houseTypeJBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        houseTypeJBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Appartment", "Attached", "Detached", "Townhouse" }));
        houseTypeJBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        houseTypeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        houseTypeLabel.setText("House type");

        numBedroomsLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numBedroomsLabel.setText("Number of Bedrooms");

        numBedRoomsBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numBedRoomsBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5+" }));
        numBedRoomsBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        numBathroomLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numBathroomLabel.setText("Number of Bathrooms");

        numBathroomsBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numBathroomsBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5+" }));
        numBathroomsBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        Furnished.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Furnished.setText("Furnished");

        FurnishedBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        FurnishedBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        FurnishedBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        cityQuadLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cityQuadLabel.setText("City Quadrant");

        cityQuadBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cityQuadBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SW", "NW", "NE", "SE" }));
        cityQuadBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        doneButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        doneButton.setText("Done");

        doneStatemeneetLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doneStatemeneetLabel.setText("Click Done when finished");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(houseTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(houseTypeJBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(Furnished, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(FurnishedBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(numBedroomsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(numBedRoomsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addComponent(createListingLabel))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(numBathroomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(numBathroomsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(doneStatemeneetLabel)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(cityQuadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(cityQuadBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 45, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
