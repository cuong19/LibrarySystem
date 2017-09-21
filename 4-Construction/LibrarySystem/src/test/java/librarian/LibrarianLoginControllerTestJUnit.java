/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarian;

import java.sql.SQLException;
import librarysystem.librarian.controller.LibrarianLoginController;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author pdc
 */
public class LibrarianLoginControllerTestJUnit {

    LibrarianLoginController librarianLoginController = new LibrarianLoginController();

    @Test
    public void test() throws SQLException, ClassNotFoundException {

        int result;
        result = librarianLoginController.checkLogin("chienpham", "12345");
        assertEquals(result, 0);

        result = librarianLoginController.checkLogin("chienpham", "123456");
        assertEquals(result, 1);

        result = librarianLoginController.checkLogin("chien", "12345");
        assertEquals(result, 2);

    }
}
