/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.entity;

/**
 * The Model for Borrower Card
 *
 * @author Cuong Nguyen
 */
public class BorrowerCard {

    private int borrowerCardNumber;
    private String username;
    private int activatedCode;
    /**
     * status 0 indicates registered but not activated 1 indicates activated -1
     * indicates expired
     */
    private int status;
    private String expiredDate;

    /**
     *
     * @return
     */
    public int getBorrowerCardNumber() {
        return borrowerCardNumber;
    }

    /**
     *
     * @param borrowerCardNumber
     */
    public void setBorrowerCardNumber(int borrowerCardNumber) {
        this.borrowerCardNumber = borrowerCardNumber;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public int getActivatedCode() {
        return activatedCode;
    }

    /**
     *
     * @param activatedCode
     */
    public void setActivatedCode(int activatedCode) {
        this.activatedCode = activatedCode;
    }

    /**
     *
     * @return
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public String getExpiredDate() {
        return expiredDate;
    }

    /**
     *
     * @param expiredDate
     */
    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

}
