import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Gary Wu
 */
public class ListingView extends JFrame {

    private JButton backToMenuButton;
    private JLabel listingLab;
    private JScrollPane jScrollPane1;
    private JTable listingTable;

    /**
     * Creates new form ListingView
     */
    public ListingView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        listingTable = new JTable();
        listingLab = new JLabel();
        backToMenuButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(854, 664));

        listingTable.setFont(new Font("Tahoma", Font.PLAIN, 12)); // NOI18N
        listingTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {"Gary", "attached", 2, 2, "Yes", "garywu@gmail.com"},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}
                },
                new String[]{
                        "Landlord ", "Type of Apartment", "Number of Bedrooms", "Number of Bathrooms", "City Quadrant", "Email"
                }
        ) {
            Class[] types = new Class[]{
                    String.class, String.class, Integer.class, Integer.class, String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(listingTable);
        if (listingTable.getColumnModel().getColumnCount() > 0) {
            listingTable.getColumnModel().getColumn(0).setMaxWidth(100);
            listingTable.getColumnModel().getColumn(1).setMaxWidth(125);
            listingTable.getColumnModel().getColumn(2).setMaxWidth(125);
            listingTable.getColumnModel().getColumn(3).setMaxWidth(125);
            listingTable.getColumnModel().getColumn(4).setMaxWidth(90);
            listingTable.getColumnModel().getColumn(5).setMaxWidth(250);
        }

        listingLab.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        listingLab.setText("Listings");

        backToMenuButton.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        backToMenuButton.setText("Back to Menu");
        backToMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(393, 393, 393)
                                .addComponent(listingLab)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(23, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 812, GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(backToMenuButton)
                                                .addGap(361, 361, 361))))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(listingLab)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(backToMenuButton)
                                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    public void display() {
        setVisible(true);
    }

}
