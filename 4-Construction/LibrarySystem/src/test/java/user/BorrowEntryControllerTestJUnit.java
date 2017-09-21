/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import librarysystem.entity.Book;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import librarysystem.user.controller.BorrowEntryController;

/**
 *
 * @author Owner
 */
public class BorrowEntryControllerTestJUnit {

    BorrowEntryController borrowEntryController = new BorrowEntryController();

    @Test
    public void test() throws SQLException {

        boolean result;
        Book book = new Book();

        book.setTitle("The Killing Joke");
        book.setAuthor("Alan Moore");
        result = borrowEntryController.createNewBorrowEntry("cuong", book);
        assertEquals(result, true);

//        book.setTitle("The Killing Joke");
//        book.setAuthor("Frank Miller");
//        result = borrowEntryController.createNewBorrowEntry("cuong", book);
//        assertEquals(result, false);
    }
}
