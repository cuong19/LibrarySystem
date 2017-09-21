/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.entity;

/**
 * The Model for Book
 *
 * @author Cuong Nguyen
 */
public class Book {

    private int bookNumber;
    private String title;
    private String classification;
    private String author;
    private String publisher;
    private String iSBN;

    /**
     *
     */
    public Book() {
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
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return
     */
    public String getClassification() {
        return classification;
    }

    /**
     *
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @return
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     *
     * @return
     */
    public String getiSBN() {
        return iSBN;
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
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @param classification
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @param publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     *
     * @param iSBN
     */
    public void setISBN(String iSBN) {
        this.iSBN = iSBN;
    }
}
