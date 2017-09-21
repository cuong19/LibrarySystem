/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Establish a connection to the SQLite Database
 *
 * @author Cuong Nguyen
 */
public class DatabaseConnection {

    /**
     *
     */
    public Connection connection;
    private Statement stmt;

    /**
     * Establish the connection
     */
    public void getConnection() {
        String url = "jdbc:sqlite:./db/database.db";
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(url);
            }
            System.out.println(connection);
            stmt = connection.createStatement();
            stmt.setQueryTimeout(30); // set timeout to 30 sec.
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Return the Statement to query
     *
     * @return the statement to query
     */
    public Statement getStmt() {
        return stmt;
    }

    /**
     * Close the connection
     */
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
        }
    }

    /**
     * Use for executing UPDATE like queries
     *
     * @param updateQuery
     * @return
     * @throws SQLException
     */
    public boolean updateQuery(String updateQuery) throws SQLException {
        getConnection();
        try {
            stmt.executeUpdate(updateQuery);
            closeConnection();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
