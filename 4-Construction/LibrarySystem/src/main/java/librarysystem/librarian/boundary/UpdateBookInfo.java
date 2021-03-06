/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.librarian.boundary;

import java.awt.Frame;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import librarysystem.librarian.controller.CRUDBookController;

/**
 *
 * @author pdc
 */
public class UpdateBookInfo extends javax.swing.JFrame {

    private static Integer booknumber;
    Frame frame = new Frame();

    /**
     *
     * @return
     */
    public Frame getFrame() {
        return frame;
    }

    /**
     *
     * @param frame
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    /**
     *
     * @return
     */
    public static Integer getBooknumber() {
        return booknumber;
    }

    /**
     *
     * @param booknumber
     */
    public static void setBooknumber(Integer booknumber) {
        UpdateBookInfo.booknumber = booknumber;
    }

    /**
     * Creates new form UpdateBookInfo
     *
     * @param booknumber
     * @param title
     * @param category
     * @param author
     * @param publisher
     * @param ISBN
     * @param frame
     */
    public UpdateBookInfo(Integer booknumber, String title, String category, String author, String publisher, String ISBN, Frame frame) {
        this.booknumber = booknumber;
        this.frame = frame;
        initComponents();
        jTextFieldTitleUpdate.setText(title);
        jTextFieldCategoryUpdate.setText(category);
        jTextFieldAuthorUpdate.setText(author);
        jTextFieldPublisherUpdate.setText(publisher);
        jTextFieldISBNUpdate.setText(ISBN);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTitleUpdate = new javax.swing.JTextField();
        jTextFieldAuthorUpdate = new javax.swing.JTextField();
        jTextFieldCategoryUpdate = new javax.swing.JTextField();
        jTextFieldPublisherUpdate = new javax.swing.JTextField();
        jButtonSubmittoUpdate = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jButtonCancelUpdate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldISBNUpdate = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Book Information");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Title");

        jLabel5.setText("Author");

        jLabel6.setText("Publisher");

        jLabel7.setText("Category");

        jTextFieldTitleUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTitleUpdateActionPerformed(evt);
            }
        });

        jTextFieldCategoryUpdate.setMaximumSize(new java.awt.Dimension(12, 29));

        jButtonSubmittoUpdate.setText("Submit");
        jButtonSubmittoUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmittoUpdateActionPerformed(evt);
            }
        });

        jButtonClear.setText("Clear");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jButtonCancelUpdate.setText("Cancel");
        jButtonCancelUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelUpdateActionPerformed(evt);
            }
        });

        jLabel2.setText("ISBN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTitleUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAuthorUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPublisherUpdate, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldCategoryUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldISBNUpdate)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jButtonCancelUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSubmittoUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonCancelUpdate, jButtonClear, jButtonSubmittoUpdate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldTitleUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldAuthorUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldCategoryUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldPublisherUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldISBNUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSubmittoUpdate)
                    .addComponent(jButtonClear)
                    .addComponent(jButtonCancelUpdate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldTitleUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTitleUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTitleUpdateActionPerformed

    private void jButtonSubmittoUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmittoUpdateActionPerformed
        if (jTextFieldAuthorUpdate.getText().isEmpty() || jTextFieldCategoryUpdate.getText().isEmpty() || jTextFieldISBNUpdate.getText().isEmpty() || jTextFieldPublisherUpdate.getText().isEmpty() || jTextFieldTitleUpdate.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please Fill Data at All Fields !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                // TODO add your handling code here:
                new CRUDBookController().updateBook(booknumber, jTextFieldTitleUpdate.getText(), jTextFieldCategoryUpdate.getText(), jTextFieldAuthorUpdate.getText(), jTextFieldPublisherUpdate.getText(), jTextFieldISBNUpdate.getText());
            } catch (SQLException ex) {
                Logger.getLogger(UpdateBookInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            super.dispose();
            frame.setAlwaysOnTop(true);
            frame.setEnabled(true);
        }
    }//GEN-LAST:event_jButtonSubmittoUpdateActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // TODO add your handling code here:
        jTextFieldAuthorUpdate.setText("");
        jTextFieldCategoryUpdate.setText("");
        jTextFieldPublisherUpdate.setText("");
        jTextFieldTitleUpdate.setText("");
        jTextFieldISBNUpdate.setText("");
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonCancelUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelUpdateActionPerformed
        // TODO add your handling code here:
        super.dispose();
        frame.setEnabled(true);
        frame.setAlwaysOnTop(true);
    }//GEN-LAST:event_jButtonCancelUpdateActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        frame.setEnabled(true);
        frame.setAlwaysOnTop(true);
    }//GEN-LAST:event_formWindowClosed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(UpdateBookInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(UpdateBookInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(UpdateBookInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(UpdateBookInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new UpdateBookInfo(booknumber).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelUpdate;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonSubmittoUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextFieldAuthorUpdate;
    private javax.swing.JTextField jTextFieldCategoryUpdate;
    private javax.swing.JTextField jTextFieldISBNUpdate;
    private javax.swing.JTextField jTextFieldPublisherUpdate;
    private javax.swing.JTextField jTextFieldTitleUpdate;
    // End of variables declaration//GEN-END:variables
}
