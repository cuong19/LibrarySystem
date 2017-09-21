/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.user.controller;

import librarysystem.database.DatabaseConnection;
import librarysystem.entity.Book;
import librarysystem.entity.BorrowEntry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import librarysystem.user.boundary.HomeScreen;

/**
 * Controller for User when accessing Borrow Entry
 *
 * @author Cuong Nguyen
 */
public class BorrowEntryController {

    /**
     * Create new borrow entry when an user identified by username borrow a book
     *
     * @param username
     * @param book
     * @return
     */
    public boolean createNewBorrowEntry(String username, Book book) {
        boolean isSuccess = false;
        boolean bookCopyInStock = false;
        String bookCopyNumber = null;

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();

        try {
            ResultSet resultSet;
            resultSet = databaseConnection.getStmt().executeQuery("SELECT book_copy_number FROM book_copy WHERE book_number = (SELECT book_number FROM book WHERE title = \"" + book.getTitle() + "\" AND author = \"" + book.getAuthor() + "\")  AND status = 0");
            while (resultSet.next()) {
                bookCopyNumber = resultSet.getString("book_copy_number");
                System.out.println("->" + bookCopyNumber + "<-");
                bookCopyInStock = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowerCardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (bookCopyInStock) {
            try {
                databaseConnection.getStmt().executeUpdate("UPDATE book_copy SET status = 1 WHERE book_copy_number = " + bookCopyNumber);
                databaseConnection.getStmt().executeUpdate("INSERT INTO borrow_entry VALUES ((SELECT borrower_card_number FROM borrower_card WHERE username = \"" + username + "\" AND status = 1)," + bookCopyNumber + ",(SELECT date('now')),(SELECT date('now', '+2 month')), 0)");
                isSuccess = true;
            } catch (SQLException ex) {
                Logger.getLogger(BorrowEntryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                JOptionPane.showMessageDialog(HomeScreen.getInstance(), "Book copy not in stock", "Not instock", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(BorrowEntryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        databaseConnection.closeConnection();

        return isSuccess;
    }

    /**
     * Get borrow entry status status 0 indicates to be borrowed status 1
     * indicates borrowed status 2 indicates overdue status -1 indicate returned
     *
     * @param username
     * @param status
     * @return
     */
    public ArrayList getBorrowEntryByStatus(String username, int status) {
        ArrayList list = new ArrayList();
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.getConnection();
            ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT book.title, book.author, borrow_entry.borrow_date, borrow_entry.expected_return_date FROM book JOIN book_copy ON book.book_number = book_copy.book_number JOIN borrow_entry ON book_copy.book_copy_number = borrow_entry.book_copy_number WHERE borrow_entry.borrower_card_number = (SELECT borrower_card_number FROM borrower_card WHERE username = \"" + username + "\") AND borrow_entry.status =" + status);
            while (resultSet.next()) {
                BorrowEntry book = new BorrowEntry();
                book.setBookTitle(resultSet.getString("title"));
                book.setBookAuthor(resultSet.getString("author"));
                book.setBorrowDate(resultSet.getString("borrow_date"));
                book.setExpectedReturnDate(resultSet.getString("expected_return_date"));
                list.add(book);
            }
            databaseConnection.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowEntryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
