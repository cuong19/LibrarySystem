/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.librarian.controller;

import librarysystem.database.DatabaseConnection;
import librarysystem.entity.BorrowerCard;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pdc
 */
public class CRUDCardController {

    public ArrayList getAllCard() throws SQLException {
        ArrayList allCard = new ArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT * FROM borrower_card");
        while (resultSet.next()) {
            BorrowerCard borrowerCard = new BorrowerCard();
            borrowerCard.setBorrowerCardNumber(resultSet.getInt("borrower_card_number"));
            borrowerCard.setUsername(resultSet.getString("username"));
            borrowerCard.setActivatedCode(resultSet.getInt("activated_code"));
            borrowerCard.setStatus(resultSet.getInt("status"));
            borrowerCard.setExpiredDate(resultSet.getString("expired_date"));
            allCard.add(borrowerCard);
        }
        databaseConnection.closeConnection();
        return allCard;
    }

    public ArrayList getCardbyUsername(String uname) throws SQLException {
        ArrayList allCard = new ArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT * FROM borrower_card WHERE username like " + "\"%" + uname + "%\"");
        while (resultSet.next()) {
            BorrowerCard borrowerCard = new BorrowerCard();
            borrowerCard.setBorrowerCardNumber(resultSet.getInt("borrower_card_number"));
            borrowerCard.setUsername(resultSet.getString("username"));
            borrowerCard.setActivatedCode(resultSet.getInt("activated_code"));
            borrowerCard.setStatus(resultSet.getInt("status"));
            borrowerCard.setExpiredDate(resultSet.getString("expired_date"));
            allCard.add(borrowerCard);
        }
        databaseConnection.closeConnection();
        return allCard;
    }

    public void updateCard(Integer borrowercardnumber, String username, Integer activatedcode, Integer selectedIndex, String expireddate) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.updateQuery("UPDATE borrower_card SET username = \"" + username + "\", activated_code = " + activatedcode + ", status = " + convertStatus(selectedIndex) + ", expired_date = \"" + expireddate + "\" WHERE borrower_card_number = " + borrowercardnumber);
        //databaseConnection.updateQuery("UPDATE borrower_card SET username = \"cuong\", activated_code = 2, status =-1, expired_date = \"2017-01-01\" WHERE borrower_card_number = 1");
        databaseConnection.closeConnection();
    }

    public void updateStatus(Integer borrowercardnumber, Integer status) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.updateQuery("UPDATE borrower_card SET status = " + status + " WHERE borrower_card_number = " + borrowercardnumber);
        databaseConnection.closeConnection();
    }

    public String convertBorrowerNumbertoUsername(Integer borrowercardnumber) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT username FROM borrower_card WHERE borrower_card_number = " + borrowercardnumber);
        //resultSet.next();
        String username = resultSet.getString("username");
        databaseConnection.closeConnection();
        return username;
    }

    public Integer convertUserNametoBorrowerNumber(String username) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT borrower_card_number FROM borrower_card WHERE username like \"" + username + "\"");
        //resultSet.next();
        int borrowernumber = resultSet.getInt("borrower_card_number");
        databaseConnection.closeConnection();
        return borrowernumber;
    }

    private Integer convertStatus(Integer status) {
        if (status == 0) {
            return 0;
        } else if (status == 1) {
            return 1;
        }
        return -1;
    }
}
