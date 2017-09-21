/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.user.controller;

import librarysystem.database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller for User when accessing User
 *
 * @author Cuong Nguyen
 */
public class UserController {

    private String username;

    /**
     * Check if the username and the password is correct
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean checkUserValidity(String username, String password) throws SQLException {
        boolean exist = false;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT * FROM user WHERE username = \"" + username + "\" AND password = \"" + password + "\"");
        while (resultSet.next()) {
            this.username = resultSet.getString("username");
            exist = true;
        }
        databaseConnection.closeConnection();
        return exist;
    }

    /**
     * Check if an username has already existed
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean checkUserExistence(String username) throws SQLException {
        boolean exist = false;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT * FROM user WHERE username = \"" + username + "\"");
        while (resultSet.next()) {
            this.username = resultSet.getString("username");
            exist = true;
        }
        databaseConnection.closeConnection();
        return exist;
    }

    /**
     * Create new user the status not activated
     *
     * @param username
     * @param password
     * @param email
     * @param contact
     * @param gender
     * @param studentID
     * @param studyPeriod
     * @throws SQLException
     */
    public void createNewUser(String username, String password, String email, String contact, int gender, String studentID, String studyPeriod) throws SQLException {
        System.out.println("Creating user...");
        System.out.println(username);
        System.out.println(password);
        System.out.println(email);
        System.out.println(contact);
        System.out.println(gender);
        System.out.println(studentID);
        System.out.println(studyPeriod);
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        databaseConnection.getStmt().executeUpdate("INSERT INTO user VALUES (\"" + username + "\", \"" + password + "\", \"" + email + "\"," + gender + ", \"" + contact + "\", \"" + studentID + "\", \"" + studyPeriod + "\")");
        databaseConnection.closeConnection();
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

}
