package librarysystem.user.controller;

import librarysystem.database.DatabaseConnection;
import librarysystem.entity.BorrowerCard;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Controller for User when accessing Borrower Card
 *
 * @author Owner
 */
public class BorrowerCardController {

    private BorrowerCard activeBorrowerCard = new BorrowerCard();

    /**
     * Check if a card with this username has already existed
     *
     * @param username
     * @return
     */
    public boolean checkCardExistence(String username) {
        boolean exist = false;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet;
        try {
            resultSet = databaseConnection.getStmt().executeQuery("SELECT * FROM borrower_card WHERE username = \"" + username + "\"");
            while (resultSet.next()) {
                exist = true;
                this.activeBorrowerCard.setBorrowerCardNumber(resultSet.getInt("borrower_card_number"));
                this.activeBorrowerCard.setUsername(resultSet.getString("username"));
                this.activeBorrowerCard.setActivatedCode(resultSet.getInt("activated_code"));
                this.activeBorrowerCard.setStatus(resultSet.getInt("status"));
                this.activeBorrowerCard.setExpiredDate(resultSet.getString("expired_date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowerCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        databaseConnection.closeConnection();
        return exist;
    }

    /**
     * Check if the activation code provided by user is correct
     *
     * @param username
     * @param activationCode
     * @return
     */
    public boolean checkActivationCode(String username, String activationCode) {
        boolean exist = false;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet;
        try {
            resultSet = databaseConnection.getStmt().executeQuery("SELECT * FROM borrower_card WHERE username = \"" + username + "\" AND activated_code = \"" + activationCode + "\"");
            while (resultSet.next()) {
                exist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowerCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        databaseConnection.closeConnection();
        return exist;
    }

    /**
     * Set the activation status to true
     *
     * @param username
     */
    public void setActivatedStatus(String username) {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();

        try {
            databaseConnection.getStmt().executeUpdate("UPDATE borrower_card set status = 1 WHERE username = \"" + username + "\"");
        } catch (SQLException ex) {
            Logger.getLogger(BorrowerCardController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            databaseConnection.closeConnection();
        }

    }

    /**
     *
     * @return
     */
    public BorrowerCard getActiveBorrowerCard() {
        return this.activeBorrowerCard;
    }

}
