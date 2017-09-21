/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.SQLException;
import java.util.ArrayList;
import librarysystem.entity.Book;
import librarysystem.user.controller.BookController;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Owner
 */
public class BookControllerTestJUnit {

    BookController BookController = new BookController();

    @Test
    public void test() throws SQLException {
        ArrayList result;
        Book book;
        String bookTitle;

        result = BookController.getAllBooks();
        book = (Book) result.get(0);
        bookTitle = book.getTitle();
        assertEquals("Introduction to Algorithm", bookTitle);

        result = BookController.getBooksByTitle("kill");
        book = (Book) result.get(0);
        bookTitle = book.getTitle();
        assertEquals("The Killing Joke", bookTitle);

    }
}
