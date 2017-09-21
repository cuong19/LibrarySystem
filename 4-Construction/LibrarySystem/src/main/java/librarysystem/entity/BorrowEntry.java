/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.entity;

/**
 * The Model for Borrow Entry
 *
 * @author Chien Pham
 */
public class BorrowEntry {

    private int borrowerCardNumber;
    private int bookCopyNumber;
    private String borrowDate;
    private String expectedReturnDate;
    private int status;
    private String bookTitle;
    private String bookAuthor;

    /**
     *
     * @return
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     *
     * @param bookTitle
     */
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     *
     * @return
     */
    public String getBookAuthor() {
        return bookAuthor;
    }

    /**
     *
     * @param bookAuthor
     */
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

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
    public String getBorrowDate() {
        return borrowDate;
    }

    /**
     *
     * @param borrowDate
     */
    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    /**
     *
     * @return
     */
    public String getExpectedReturnDate() {
        return expectedReturnDate;
    }

    /**
     *
     * @param expectedReturnDate
     */
    public void setExpectedReturnDate(String expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
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
