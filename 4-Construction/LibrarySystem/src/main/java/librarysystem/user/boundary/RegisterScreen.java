/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.user.boundary;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import librarysystem.user.controller.UserController;

/**
 *
 * @author Cuong Nguyen
 */
public class RegisterScreen extends javax.swing.JFrame {

    /**
     * Creates new form RegisterScreen
     */
    public RegisterScreen() {
        initComponents();
        initComponentsDetails();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponentsDetails() {
        studentidLabel.setEnabled(false);
        studentidTextField.setEnabled(false);
        studyperiodLabel.setEnabled(false);
        studyperiodTextField.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderButtonGroup = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        contactLabel = new javax.swing.JLabel();
        studentLabel = new javax.swing.JLabel();
        studentidLabel = new javax.swing.JLabel();
        studyperiodLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        maleRadioButton = new javax.swing.JRadioButton();
        femaleRadioButton = new javax.swing.JRadioButton();
        contactTextField = new javax.swing.JTextField();
        studentCheckBox = new javax.swing.JCheckBox();
        studentidTextField = new javax.swing.JTextField();
        studyperiodTextField = new javax.swing.JTextField();
        registeraccountButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Register new account"));

        usernameLabel.setText("Username:");

        passwordLabel.setText("Password:");

        emailLabel.setText("Email:");

        genderLabel.setText("Gender:");

        contactLabel.setText("Contact:");

        studentLabel.setText("HUST Student:");

        studentidLabel.setText("Student ID:");

        studyperiodLabel.setText("Study Period:");

        genderButtonGroup.add(maleRadioButton);
        maleRadioButton.setText("Male");

        genderButtonGroup.add(femaleRadioButton);
        femaleRadioButton.setText("Female");

        studentCheckBox.setText("Yes");
        studentCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                studentCheckBoxStateChanged(evt);
            }
        });

        registeraccountButton.setText("Register New Account");
        registeraccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registeraccountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(emailLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(genderLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(contactLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(studentLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(studentidLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(studyperiodLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameTextField)
                            .addComponent(passwordTextField)
                            .addComponent(emailTextField)
                            .addComponent(contactTextField)
                            .addComponent(studentidTextField)
                            .addComponent(studyperiodTextField)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(maleRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(femaleRadioButton))
                                    .addComponent(studentCheckBox))
                                .addGap(0, 159, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(registeraccountButton)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(maleRadioButton)
                        .addComponent(genderLabel))
                    .addComponent(femaleRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactLabel)
                    .addComponent(contactTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentLabel)
                    .addComponent(studentCheckBox))
                .addGap(6, 6, 6)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentidLabel)
                    .addComponent(studentidTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studyperiodLabel)
                    .addComponent(studyperiodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(registeraccountButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            // TODO add your handling code here:
            HomeScreen.getInstance().setEnabled(true);
            HomeScreen.getInstance().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void studentCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_studentCheckBoxStateChanged
        // TODO add your handling code here:
        if (studentCheckBox.isSelected()) {
            studentidLabel.setEnabled(true);
            studentidTextField.setEnabled(true);
            studyperiodLabel.setEnabled(true);
            studyperiodTextField.setEnabled(true);
        } else {
            studentidLabel.setEnabled(false);
            studentidTextField.setEnabled(false);
            studyperiodLabel.setEnabled(false);
            studyperiodTextField.setEnabled(false);
        }
    }//GEN-LAST:event_studentCheckBoxStateChanged

    private void registeraccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registeraccountButtonActionPerformed
        // TODO add your handling code here:
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String email = emailTextField.getText();
        String contact = contactTextField.getText();
        int gender = 1;
        if (femaleRadioButton.isSelected()) {
            gender = 0;
        }
        String studentID = studentidTextField.getText();
        String studyPeriod = studyperiodTextField.getText();
        if (!username.isEmpty() && !password.isEmpty()) {
            try {
                boolean existed = new UserController().checkUserExistence(username);
                if (existed) {
                    JOptionPane.showMessageDialog(rootPane, "User with this username existed", "Re-enter username", JOptionPane.WARNING_MESSAGE);
                } else {
                    new UserController().createNewUser(username, password, email, contact, gender, studentID, studyPeriod);
                    this.dispose();
                    HomeScreen.getInstance().setEnabled(true);
                    HomeScreen.getInstance().setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RegisterScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Please enter the required Username and Password field", "Fields not entered", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_registeraccountButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel contactLabel;
    private javax.swing.JTextField contactTextField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JRadioButton femaleRadioButton;
    private javax.swing.ButtonGroup genderButtonGroup;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JRadioButton maleRadioButton;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JButton registeraccountButton;
    private javax.swing.JCheckBox studentCheckBox;
    private javax.swing.JLabel studentLabel;
    private javax.swing.JLabel studentidLabel;
    private javax.swing.JTextField studentidTextField;
    private javax.swing.JLabel studyperiodLabel;
    private javax.swing.JTextField studyperiodTextField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
