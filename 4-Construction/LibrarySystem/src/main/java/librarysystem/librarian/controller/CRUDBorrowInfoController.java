/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.librarian.controller;

import librarysystem.database.DatabaseConnection;
import librarysystem.entity.BorrowEntry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class này điểu khiển các chức năng liên quan đến BorrowInfo
 *
 * @author pdc
 */
public class CRUDBorrowInfoController {

    /**
     * Hàm này dùng để lấy 1 list thông tin borrow entry trong Borrow Entry
     *
     * @return ArrayList: list thông tin borrow entry
     * @throws SQLException
     */
    public ArrayList getAllBorrowEnrty() throws SQLException {
        ArrayList allBorrowEntry = new ArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        // ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT * FROM borrow_entry");
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("select borrow_entry.borrower_card_number, username, title, book_copy.book_copy_number, borrow_entry.borrow_date, borrow_entry.expected_return_date, borrow_entry.status  from borrow_entry, borrower_card, book_copy, book WHERE book_copy.book_copy_number = borrow_entry.book_copy_number AND borrower_card.borrower_card_number = borrow_entry.borrower_card_number AND book_copy.book_number = book.book_number");
        while (resultSet.next()) {
            BorrowEntry borrowEntry = new BorrowEntry();
            borrowEntry.setBorrowerCardNumber(resultSet.getInt("borrower_card_number"));
            borrowEntry.setBookCopyNumber(resultSet.getInt("book_copy_number"));
            borrowEntry.setBorrowDate(resultSet.getString("borrow_date"));
            borrowEntry.setExpectedReturnDate(resultSet.getString("expected_return_date"));
            borrowEntry.setStatus(resultSet.getInt("status"));
            allBorrowEntry.add(borrowEntry);
        }
        databaseConnection.closeConnection();
        return allBorrowEntry;
    }

    /**
     * Hàm này dùng để lấy 1 list thông tin borrow entry tương ứng với 1 user
     *
     * @param username
     * @return ArrayList: list thông tin các borrow entry thỏa mãn
     * @throws SQLException
     */
    public ArrayList getBorrowEntrybyUsername(String username) throws SQLException {
        ArrayList allBorrowEntry = new ArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("select borrow_entry.borrower_card_number, username, title, book_copy.book_copy_number, borrow_entry.borrow_date, borrow_entry.expected_return_date, borrow_entry.status  from borrow_entry, borrower_card, book_copy, book WHERE book_copy.book_copy_number = borrow_entry.book_copy_number AND borrower_card.borrower_card_number = borrow_entry.borrower_card_number AND book_copy.book_number = book.book_number AND borrower_card.username like \"" + username + "%\"");
        while (resultSet.next()) {
            BorrowEntry borrowEntry = new BorrowEntry();
            borrowEntry.setBorrowerCardNumber(resultSet.getInt("borrower_card_number"));
            borrowEntry.setBookCopyNumber(resultSet.getInt("book_copy_number"));
            borrowEntry.setBorrowDate(resultSet.getString("borrow_date"));
            borrowEntry.setExpectedReturnDate(resultSet.getString("expected_return_date"));
            borrowEntry.setStatus(resultSet.getInt("status"));
            allBorrowEntry.add(borrowEntry);
        }
        databaseConnection.closeConnection();
        return allBorrowEntry;
    }

    /**
     * Hàm này dùng để cập nhật trạng thái cho 1 borrow entry
     *
     * @param bookcopynumber
     * @param status
     * @param oldstatus
     * @throws SQLException
     */
    public void updateStatus(Integer bookcopynumber, Integer status, Integer oldstatus) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.updateQuery("UPDATE borrow_entry SET status = " + status + " WHERE book_copy_number = " + bookcopynumber + " AND status = " + oldstatus);
        databaseConnection.closeConnection();
    }

    /**
     * Hàm này dùng để cập nhật thông tin cho 1 borrow entry trong Borrow Entry
     *
     * @param bookcopynumber
     * @param expectedreturndate
     * @param newstatus
     * @param oldstatus
     * @throws SQLException
     */
    public void updateBorrowInfo(Integer bookcopynumber, String expectedreturndate, Integer newstatus, Integer oldstatus) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.updateQuery("UPDATE borrow_entry SET expected_return_date = \"" + expectedreturndate + "\", status = " + newstatus + " WHERE book_copy_number = " + bookcopynumber + " AND status = " + oldstatus);
        databaseConnection.closeConnection();
    }

    /**
     * Hàm này dùng để xóa 1 borrow entry tương ứng với bookcopynumber và status
     * của nó trong Borrow Entry
     *
     * @param bookcopynumber
     * @param status
     * @throws SQLException
     */
    public void deleteBorrowInfobyBookcopyBumber(Integer bookcopynumber, Integer status) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        databaseConnection.getStmt().execute("DELETE FROM borrow_entry WHERE book_copy_number = " + bookcopynumber + " AND status = " + status);
        databaseConnection.closeConnection();
    }

}
