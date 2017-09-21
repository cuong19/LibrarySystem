/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.entity;

/**
 * The Model for Book Copy
 *
 * @author Chien Pham
 */
public class BookCopy {

    private int bookCopyNumber;
    private int bookNumber;
    private int price;
    private int status;

    /**
     *
     * @return
     */
    public int getBookCopyNumber() {
        return bookCopyNumber;
    }

    /**
     *
     * @param bookCopyNumber
     */
    public void setBookCopyNumber(int bookCopyNumber) {
        this.bookCopyNumber = bookCopyNumber;
    }

    /**
     *
     * @return
     */
    public int getBookNumber() {
        return bookNumber;
    }

    /**
     *
     * @param bookNumber
     */
    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    /**
     *
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
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

}
