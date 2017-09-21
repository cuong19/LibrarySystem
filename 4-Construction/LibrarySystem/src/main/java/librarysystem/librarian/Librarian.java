/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.librarian;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import librarysystem.librarian.boundary.LoginForm;

/**
 * The class use for running the main Interface for Librarian
 * @author pdc
 */
public class Librarian {

    public static void main(String[] args) {
       /* Set the System look and feel */
        try {
            // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
             // Handle exception
        }

        java.awt.EventQueue.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        });
    }
}
