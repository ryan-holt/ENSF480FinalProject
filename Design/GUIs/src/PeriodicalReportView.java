

/**
 *
 * @author Gary Wu
 */
public class PeriodicalReportView extends javax.swing.JFrame {


    private javax.swing.JButton closeReportButton;
    private javax.swing.JLabel peridicalDateLab;
    private javax.swing.JLabel dateLab;
    private javax.swing.JLabel numHousesPeriLab;
    private javax.swing.JLabel numHouseRentedLab;
    private javax.swing.JLabel activeListingCurrLab;
    private javax.swing.JLabel listOfhouseRentLab;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable listTable;

    /**
     * Creates new form PeriodicalReportView
     */
    public PeriodicalReportView() {
        initComponents();
    }

    public void display() {
        setVisible(true);
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        peridicalDateLab = new javax.swing.JLabel();
        dateLab = new javax.swing.JLabel();
        numHousesPeriLab = new javax.swing.JLabel();
        numHouseRentedLab = new javax.swing.JLabel();
        activeListingCurrLab = new javax.swing.JLabel();
        listOfhouseRentLab = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listTable = new javax.swing.JTable();
        closeReportButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        peridicalDateLab.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        peridicalDateLab.setText("Periodical Report");

        dateLab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateLab.setText("Total number of houses listed in the period: ");

        numHousesPeriLab.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        numHousesPeriLab.setText("for Date - Date");

        numHouseRentedLab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numHouseRentedLab.setText("Total number of houses rented in the period: ");

        activeListingCurrLab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        activeListingCurrLab.setText("Total number of active listings currently: ");

        listOfhouseRentLab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        listOfhouseRentLab.setText("List of houses rented in the period ");

        listTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        listTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {"Gary",  new Integer(342), "no clure dlfjadfklajdflafdjasdlfjadfadsf"},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "Landlord Name", "House ID Number", "Address"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(listTable);

        closeReportButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        closeReportButton.setText("Close Report");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(numHouseRentedLab)
                                        .addComponent(dateLab)
                                        .addComponent(activeListingCurrLab))
                                .addContainerGap(225, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(peridicalDateLab, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(10, 10, 10)
                                                                .addComponent(numHousesPeriLab)))
                                                .addGap(174, 174, 174))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(listOfhouseRentLab)
                                                .addGap(150, 150, 150))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(46, 46, 46))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(closeReportButton)
                                                .addGap(212, 212, 212))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(peridicalDateLab)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(numHousesPeriLab)
                                .addGap(18, 18, 18)
                                .addComponent(dateLab)
                                .addGap(18, 18, 18)
                                .addComponent(numHouseRentedLab)
                                .addGap(18, 18, 18)
                                .addComponent(activeListingCurrLab)
                                .addGap(18, 18, 18)
                                .addComponent(listOfhouseRentLab)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(closeReportButton)
                                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents



}
