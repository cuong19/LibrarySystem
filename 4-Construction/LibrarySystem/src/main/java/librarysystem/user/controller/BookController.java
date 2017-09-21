/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.user.controller;

import librarysystem.database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import librarysystem.entity.Book;

/**
 * Controller for User when accessing Book
 *
 * @author Cuong Nguyen
 */
public class BookController {

    /**
     *
     */
    public BookController() {

    }

    /**
     * Return all books from database
     *
     * @return
     * @throws SQLException
     */
    public ArrayList getAllBooks() throws SQLException {
        ArrayList allBook = new ArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT * FROM book");
        while (resultSet.next()) {
            Book book = new Book();
            book.setBookNumber(resultSet.getInt("book_number"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setClassification(resultSet.getString("classification"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setISBN(resultSet.getString("isbn"));
            allBook.add(book);
        }
        databaseConnection.closeConnection();
        return allBook;
    }

    /**
     * Search the book in the database by title
     *
     * @param title the title to search
     * @return
     * @throws SQLException
     */
    public ArrayList getBooksByTitle(String title) throws SQLException {
        ArrayList searchedBook = new ArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        title = title.replace("\"", "");
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT * FROM book WHERE title like " + "\"%" + title + "%\"");
        while (resultSet.next()) {
            Book book = new Book();
            book.setBookNumber(resultSet.getInt("book_number"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setClassification(resultSet.getString("classification"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setISBN(resultSet.getString("isbn"));
            searchedBook.add(book);
        }
        databaseConnection.closeConnection();
        return searchedBook;
    }

}
