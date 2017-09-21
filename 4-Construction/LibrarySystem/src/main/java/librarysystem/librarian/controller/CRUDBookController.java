/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.librarian.controller;

import librarysystem.entity.Book;
import librarysystem.database.DatabaseConnection;
import librarysystem.entity.BookCopy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class này điểu khiển các chức năng liên quan đến Book
 *
 * @author pdc
 */
public class CRUDBookController {

    /**
     * Hàm này để lấy ra list tất cả các sách và thông tin của chúng trong Book
     *
     * @return ArrayList: list thông tin các sách
     * @throws SQLException
     */
    public ArrayList getAllBook() throws SQLException {
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
     * Hàm này lấy ra list thông tin các cuốn sách có chứa chuỗi "title" trong
     * tiêu đề ở trong Book
     *
     * @param title
     * @return ArrayList: list thông tin các sách thỏa mãn
     * @throws SQLException
     */
    public ArrayList getBookbyTitle(String title) throws SQLException {
        ArrayList allBook = new ArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT * FROM book WHERE title like " + "\"%" + title + "%\"");
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
     * Hàm này dùng để xóa 1 sách tương ứng với booknumber trong Book
     *
     * @param booknumber
     * @return 0: Xóa thành công 2: Xóa không thành công
     * @throws SQLException
     */
    public int deleteBookbyBookNumber(Integer booknumber) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        return databaseConnection.getStmt().executeUpdate("DELETE FROM book WHERE book_number = " + booknumber);
    }

    /**
     * Hàm này dùng để xóa 1 bookcopy tương ứng với 1 bookcopynumber trong
     * BookCopy
     *
     * @param bookcopynumber
     * @return 1: Xóa thành công 2: Xóa không thành công
     * @throws SQLException
     */
    public int deleteBookCopybyBookCopyNumber(Integer bookcopynumber) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        return databaseConnection.getStmt().executeUpdate("DELETE FROM book_copy WHERE book_copy_number = " + bookcopynumber);
    }

    /**
     * Hàm này dùng để lấy 1 list chứa thông tin các bookcopy tương ứng với 1
     * book number trong BookCopy
     *
     * @param booknumber
     * @return ArrayList: list chứa thông tin các bookcopy
     * @throws SQLException
     */
    public ArrayList getBookCopyListbyBookNumber(Integer booknumber) throws SQLException {
        ArrayList allBook = new ArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT * FROM book_copy WHERE book_number = " + booknumber);
        while (resultSet.next()) {
            BookCopy bookCopy = new BookCopy();
            bookCopy.setBookCopyNumber(resultSet.getInt("book_copy_number"));
            bookCopy.setBookNumber(resultSet.getInt("book_number"));
            bookCopy.setPrice(resultSet.getInt("price"));
            bookCopy.setStatus(resultSet.getInt("status"));

            allBook.add(bookCopy);
        }
        databaseConnection.closeConnection();
        return allBook;
    }

    /**
     * Hàm này dùng để cập nhật thông tin cho 1 cuốn sách trong Book
     *
     * @param booknumber
     * @param title
     * @param category
     * @param author
     * @param publisher
     * @param isbn
     * @return true: cập nhật thành công false: cập nhật không thành công
     * @throws SQLException
     */
    public boolean updateBook(Integer booknumber, String title, String category, String author, String publisher, String isbn) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        return databaseConnection.updateQuery("UPDATE book SET title = \"" + title + "\", classification = \"" + category + "\", author = \"" + author + "\", publisher = \"" + publisher + "\", isbn = \"" + isbn + "\" WHERE book_number = " + booknumber);
    }

    /**
     * Hàm này dùng để cập nhật thông tin cho 1 bookcopy trong BookCopy
     *
     * @param bookcopynumber
     * @param price
     * @param status
     * @return true: cập nhật thành công false: cập nhật không thành công
     * @throws SQLException
     */
    public boolean updateBookCopy(Integer bookcopynumber, Integer price, Integer status) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        return databaseConnection.updateQuery("UPDATE book_copy SET price = " + price + ", status = " + status + " WHERE book_copy_number = " + bookcopynumber);
    }

    /**
     * Hàm này dùng để thêm 1 cuốn sách vào trong Book
     *
     * @param title
     * @param category
     * @param author
     * @param publisher
     * @param isbn
     * @throws SQLException
     */
    public void addNewBook(String title, String category, String author, String publisher, String isbn) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        databaseConnection.getStmt().executeUpdate("INSERT INTO book(title, classification, author, publisher, isbn) VALUES('" + title + "', '" + category + "', '" + author + "', '" + publisher + "', '" + isbn + "')");
        databaseConnection.closeConnection();
    }

    /**
     * Hàm này dùng để thêm lượng bookcopy vào trong BookCopy
     *
     * @param booknumber
     * @param price
     * @param numberofcopy
     * @throws SQLException
     */
    public void addNewBookCopy(Integer booknumber, Integer price, Integer numberofcopy) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        for (int i = 0; i < numberofcopy; i++) {
            databaseConnection.getStmt().execute("INSERT INTO book_copy(book_number, price, status) VALUES(" + booknumber + ", " + price + ", 0" + ")");
        }

        databaseConnection.closeConnection();
    }

    /**
     * Hàm này dùng để lấy Tên sách từ booknumber trong Book
     *
     * @param bookcopynumber
     * @return String: chứa tên sách
     * @throws SQLException
     */
    public String convertBookNumbertoTitle(Integer bookcopynumber) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        ResultSet resultSet = databaseConnection.getStmt().executeQuery("SELECT title FROM book, book_copy, borrow_entry WHERE book.book_number = book_copy.book_number AND book_copy.book_copy_number = borrow_entry.book_copy_number AND borrow_entry.book_copy_number = " + bookcopynumber);
        //resultSet.next();
        String title = resultSet.getString("title");
        databaseConnection.closeConnection();
        return title;
    }
}
