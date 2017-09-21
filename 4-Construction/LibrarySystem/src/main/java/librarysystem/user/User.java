/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.user;

import librarysystem.user.boundary.HomeScreen;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The class use for running the main Interface for User/Borrower/Guest
 *
 * @author Cuong
 */
public class User {

    /**
     * Main method for the User Interface
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String args[]) throws SQLException {
        /* Set the System look and feel */
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // Handle exception
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                HomeScreen userUI = HomeScreen.getInstance();
                userUI.setVisible(true);
                userUI.setLocationRelativeTo(null);
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
}
