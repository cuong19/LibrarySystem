/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.SQLException;
import org.junit.Test;
import librarysystem.user.controller.UserController;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Owner
 */
public class UserControllerTestJUnit {

    UserController userController = new UserController();

    @Test
    public void test() throws SQLException {

        boolean result;
        result = userController.checkUserValidity("cuong", "cuong");
        assertEquals(result, true);

        result = userController.checkUserValidity("cuong", "12345");
        assertEquals(result, false);

    }
}
