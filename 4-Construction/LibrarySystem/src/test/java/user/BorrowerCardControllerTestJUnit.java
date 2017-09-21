/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import librarysystem.entity.BorrowerCard;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import librarysystem.user.controller.BorrowerCardController;

/**
 *
 * @author Owner
 */
public class BorrowerCardControllerTestJUnit {

    BorrowerCardController borrowerCardController = new BorrowerCardController();

    @Test
    public void test() throws SQLException {

        boolean result;
        result = borrowerCardController.checkCardExistence("cuong");
        assertEquals(result, true);

        BorrowerCard borrowerCard = new BorrowerCard();
        borrowerCard = borrowerCardController.getActiveBorrowerCard();

        result = borrowerCardController.checkCardExistence("cuongnguyen");
        assertEquals(result, false);

        result = borrowerCardController.checkActivationCode("cuong", "12345");
        assertEquals(result, true);

        result = borrowerCardController.checkActivationCode("cuong", "123");
        assertEquals(result, false);

        borrowerCardController.setActivatedStatus("cuong");

    }
}
