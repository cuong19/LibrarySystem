/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarian;

import java.sql.SQLException;
import java.util.ArrayList;
import librarysystem.entity.Book;
import librarysystem.entity.BookCopy;
import librarysystem.librarian.controller.CRUDBookController;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Owner
 */
public class CRUDBookControllerTestJUnit {

    CRUDBookController crudBookController = new CRUDBookController();

    @Test
    public void testPrintMessage() throws SQLException {
        ArrayList result = crudBookController.getBookbyTitle("Intro");
        Book book = (Book) result.get(0);
        String bookTitle = book.getTitle();
        assertEquals("Introduction to Algorithm", bookTitle);

        ArrayList result2 = crudBookController.getBookbyTitle("kill");
        Book book2 = (Book) result2.get(0);
        String bookTitle2 = book2.getTitle();
        assertEquals("The Killing Joke", bookTitle2);

//      ArrayList result3 = crudBookController.getAllBook();
//      Book book3 = (Book) result3.get(0);
//      String bookTitle3 = book3.getTitle();
//      assertEquals("Introduction to Algorithm", bookTitle3);
        int result4 = crudBookController.deleteBookbyBookNumber(1);
        assertEquals(1, result4);

        int result5 = crudBookController.deleteBookbyBookNumber(1);
        assertEquals(0, result5);

        int result6 = crudBookController.deleteBookCopybyBookCopyNumber(1);
        assertEquals(1, result6);

        int result7 = crudBookController.deleteBookCopybyBookCopyNumber(1);
        assertEquals(0, result7);

        ArrayList result8 = crudBookController.getBookCopyListbyBookNumber(2);
        BookCopy bookCopy = (BookCopy) result8.get(0);
        int bookcopynumber = bookCopy.getBookCopyNumber();
        assertEquals(2, bookcopynumber);

//        int result9 = crudBookController.updateBook(3, "chien", "chien", "chien", "chien", "chien");
//        assertEquals(1, result9); 
//        
//        int result10 = crudBookController.updateBook(7, "chien", "chien", "chien", "chien", "chien");
//        assertEquals(0, result10);
    }
}
