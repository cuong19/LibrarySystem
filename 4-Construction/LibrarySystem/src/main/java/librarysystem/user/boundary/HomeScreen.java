/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.user.boundary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import librarysystem.entity.Book;
import librarysystem.entity.BorrowEntry;
import librarysystem.entity.BorrowerCard;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import librarysystem.user.controller.BookController;
import librarysystem.user.controller.BorrowEntryController;
import librarysystem.user.controller.BorrowerCardController;

/**
 * Main User Interface screen Shown by default
 *
 * @author Cuong Nguyen
 */
public class HomeScreen extends javax.swing.JFrame {

    private static HomeScreen instance = null;
    private boolean loginState = false;
    private String loggedUser;
    // Table models 
    private final DefaultTableModel searchedbookTableModel = new DefaultTableModel();
    private final DefaultTableModel wishlistTableModel = new DefaultTableModel();
    private final DefaultTableModel tobeborrowedTableModel = new DefaultTableModel();
    private final DefaultTableModel borrowedTableModel = new DefaultTableModel();
    private final DefaultTableModel overdueTableModel = new DefaultTableModel();

    /**
     * Creates new form UserUI
     */
    private HomeScreen() {
        initComponents();
        initComponentsDetails();
    }

    /**
     *
     * @return
     */
    public String getLoggedUser() {
        return loggedUser;
    }

    /**
     * Apply Singleton to the class
     *
     * @return
     * @throws SQLException
     */
    public static synchronized HomeScreen getInstance() throws SQLException {
        if (instance == null) {
            instance = new HomeScreen();
        }
        return instance;
    }

    /**
     * Initiates Component details
     */
    private void initComponentsDetails() {

        submitrequestButton.setEnabled(false);
        activateButton.setVisible(false);

        setColumnToSearchedBookTableModel();
        ArrayList allBooks;
        try {
            allBooks = new BookController().getAllBooks();
            setValueToSearchedBookTableModel(allBooks);
        } catch (SQLException ex) {
            Logger.getLogger(HomeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        searchedbookTable.setDefaultEditor(Object.class, null);

        setColumnToWishlistTableModel();
        wishlistTable.setDefaultEditor(Object.class, null);

        setColumnToToBeBorrowedBookTableModel();
        tobecollectedTable.setDefaultEditor(Object.class, null);
        setColumnToBorrowedBookTableModel();
        borrowedTable.setDefaultEditor(Object.class, null);
        setColumnToOverdueBookTableModel();
        overdueTable.setDefaultEditor(Object.class, null);
        mainTabbedPane.setEnabledAt(2, false);
    }

    private void setBorrowInformation() {
        ArrayList list;
        list = new BorrowEntryController().getBorrowEntryByStatus(loggedUser, 0);
        setValueToToBeBorrowedBookTableModel(list);

        list = new BorrowEntryController().getBorrowEntryByStatus(loggedUser, 1);
        setValueToBorrowedBookTableModel(list);

        list = new BorrowEntryController().getBorrowEntryByStatus(loggedUser, 2);
        setValueToOverdueBookTableModel(list);

    }

    /**
     * Change state of the screen when user logged in
     *
     * @param username
     */
    public void setLoginStateToTrue(String username) {
        this.loginState = true;
        loginButton.setText("Log out");
        userprofileButton.setEnabled(loginState);
        this.loggedUser = username;
        userprofileButton.setText(loggedUser);
        mainTabbedPane.setEnabledAt(2, true);
        setBorrowInformation();

        BorrowerCardController borrowerCardController = new BorrowerCardController();
        if (borrowerCardController.checkCardExistence(username) == true) {
            BorrowerCard borrowerCard;
            borrowerCard = borrowerCardController.getActiveBorrowerCard();
            int status = borrowerCard.getStatus();
            switch (status) {
                case 0:
                    activateButton.setText("Activate");
                    activateButton.setEnabled(true);
                    activateButton.setVisible(true);
                    JOptionPane.showMessageDialog(rootPane, "User '" + username + "' has successfully logged in! Account not activated.", "Login success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    activateButton.setText("Activated");
                    activateButton.setEnabled(false);
                    activateButton.setVisible(true);
                    submitrequestButton.setEnabled(loginState);
                    JOptionPane.showMessageDialog(rootPane, "User '" + username + "' has successfully logged in! Account activated.", "Login success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case -1:
                    activateButton.setText("(Expired) Reactivate");
                    activateButton.setEnabled(true);
                    activateButton.setVisible(true);
                    break;
                default:
                    break;
            }
        }

    }

    private void setLoginStateToFalse() {
        this.loginState = false;
        loginButton.setText("Log in");
        userprofileButton.setEnabled(loginState);
        userprofileButton.setText("Guest");
        submitrequestButton.setEnabled(loginState);

        activateButton.setText("Activate");
        activateButton.setEnabled(false);
        activateButton.setVisible(false);
        mainTabbedPane.setEnabledAt(2, false);

        JOptionPane.showMessageDialog(rootPane, "User has logged out!", "Logout success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void setColumnToSearchedBookTableModel() {
        searchedbookTableModel.addColumn("Title");
        searchedbookTableModel.addColumn("Author");
        searchedbookTableModel.addColumn("Publisher");
        searchedbookTableModel.addColumn("Category");
    }

    private void setValueToSearchedBookTableModel(ArrayList value) {
        while (searchedbookTableModel.getRowCount() > 0) {
            searchedbookTableModel.removeRow(0);
        }
        for (int i = 0; i < value.size(); i++) {
            Book book = (Book) value.get(i);
            searchedbookTableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getPublisher(), book.getClassification()});
        }
    }

    private void setColumnToWishlistTableModel() {
        wishlistTableModel.addColumn("Title");
        wishlistTableModel.addColumn("Author");
        wishlistTableModel.addColumn("Publisher");
        wishlistTableModel.addColumn("Category");
    }

    private void setValueToWishlistTableModel(ArrayList value) {
        while (wishlistTableModel.getRowCount() > 0) {
            wishlistTableModel.removeRow(0);
        }
        for (int i = 0; i < value.size(); i++) {
            Book book = (Book) value.get(i);
            wishlistTableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getPublisher(), book.getClassification()});
        }
    }

    private ArrayList getValueFromWishlistTableModel() {
        ArrayList requestedBooks = new ArrayList();

        for (int i = 0; i < wishlistTableModel.getRowCount(); i++) {
            Book requestedBook = new Book();
            requestedBook.setTitle(wishlistTableModel.getValueAt(i, 0).toString());
            requestedBook.setAuthor(wishlistTableModel.getValueAt(i, 1).toString());
            requestedBooks.add(requestedBook);
        }
        return requestedBooks;
    }

    private void setColumnToToBeBorrowedBookTableModel() {
        tobeborrowedTableModel.addColumn("Title");
        tobeborrowedTableModel.addColumn("Author");
        tobeborrowedTableModel.addColumn("Borrowed Date");
        tobeborrowedTableModel.addColumn("Expected Return Date");
    }

    private void setValueToToBeBorrowedBookTableModel(ArrayList value) {
        while (tobeborrowedTableModel.getRowCount() > 0) {
            tobeborrowedTableModel.removeRow(0);
        }
        for (int i = 0; i < value.size(); i++) {
            BorrowEntry book = (BorrowEntry) value.get(i);
            tobeborrowedTableModel.addRow(new Object[]{book.getBookTitle(), book.getBookAuthor(), book.getBorrowDate(), book.getExpectedReturnDate()});
        }
    }

    private void setColumnToBorrowedBookTableModel() {
        borrowedTableModel.addColumn("Title");
        borrowedTableModel.addColumn("Author");
        borrowedTableModel.addColumn("Borrowed Date");
        borrowedTableModel.addColumn("Expected Return Date");
    }

    private void setValueToBorrowedBookTableModel(ArrayList value) {
        while (borrowedTableModel.getRowCount() > 0) {
            borrowedTableModel.removeRow(0);
        }
        for (int i = 0; i < value.size(); i++) {
            BorrowEntry book = (BorrowEntry) value.get(i);
            borrowedTableModel.addRow(new Object[]{book.getBookTitle(), book.getBookAuthor(), book.getBorrowDate(), book.getExpectedReturnDate()});
        }
    }

    private void setColumnToOverdueBookTableModel() {
        overdueTableModel.addColumn("Title");
        overdueTableModel.addColumn("Author");
        overdueTableModel.addColumn("Borrowed Date");
        overdueTableModel.addColumn("Expected Return Date");
    }

    private void setValueToOverdueBookTableModel(ArrayList value) {
        while (overdueTableModel.getRowCount() > 0) {
            overdueTableModel.removeRow(0);
        }
        for (int i = 0; i < value.size(); i++) {
            BorrowEntry book = (BorrowEntry) value.get(i);
            overdueTableModel.addRow(new Object[]{book.getBookTitle(), book.getBookAuthor(), book.getBorrowDate(), book.getExpectedReturnDate()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        accountPanel = new javax.swing.JPanel();
        librarysystemLabel = new javax.swing.JLabel();
        mainTabbedPane = new javax.swing.JTabbedPane();
        searchTab = new javax.swing.JPanel();
        searchedbooksPanel = new javax.swing.JPanel();
        addsearchedbooktowishlistButton = new javax.swing.JButton();
        searchedbookScrollPane = new javax.swing.JScrollPane();
        searchedbookTable = new javax.swing.JTable(searchedbookTableModel);
        searchLabel = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        viewAllButton = new javax.swing.JButton();
        wishlistTab = new javax.swing.JPanel();
        wishlistPanel = new javax.swing.JPanel();
        removebookfromwishlistButton = new javax.swing.JButton();
        wishlistScrollPane = new javax.swing.JScrollPane();
        wishlistTable = new javax.swing.JTable(wishlistTableModel);
        submitrequestButton = new javax.swing.JButton();
        borrowinformationTab = new javax.swing.JPanel();
        borrowinformationTabbedPane = new javax.swing.JTabbedPane();
        tobecollectedTab = new javax.swing.JPanel();
        tobecollectedlPane = new javax.swing.JScrollPane();
        tobecollectedTable = new javax.swing.JTable(tobeborrowedTableModel);
        borrowedTab = new javax.swing.JPanel();
        borrowedScrollPane = new javax.swing.JScrollPane();
        borrowedTable = new javax.swing.JTable(borrowedTableModel);
        overdueTab = new javax.swing.JPanel();
        overduePane = new javax.swing.JScrollPane();
        overdueTable = new javax.swing.JTable(overdueTableModel);
        loginButton = new javax.swing.JButton();
        activateButton = new javax.swing.JButton();
        userprofileButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Library System - Home");

        librarysystemLabel.setText("Library System");

        javax.swing.GroupLayout accountPanelLayout = new javax.swing.GroupLayout(accountPanel);
        accountPanel.setLayout(accountPanelLayout);
        accountPanelLayout.setHorizontalGroup(
            accountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountPanelLayout.createSequentialGroup()
                .addComponent(librarysystemLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        accountPanelLayout.setVerticalGroup(
            accountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountPanelLayout.createSequentialGroup()
                .addComponent(librarysystemLabel)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        searchedbooksPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Searched Books"));

        addsearchedbooktowishlistButton.setText("Add to Wishlist");
        addsearchedbooktowishlistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addsearchedbooktowishlistButtonActionPerformed(evt);
            }
        });

        searchedbookScrollPane.setViewportView(searchedbookTable);

        javax.swing.GroupLayout searchedbooksPanelLayout = new javax.swing.GroupLayout(searchedbooksPanel);
        searchedbooksPanel.setLayout(searchedbooksPanelLayout);
        searchedbooksPanelLayout.setHorizontalGroup(
            searchedbooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchedbooksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchedbookScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addsearchedbooktowishlistButton, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );
        searchedbooksPanelLayout.setVerticalGroup(
            searchedbooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchedbooksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchedbooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchedbooksPanelLayout.createSequentialGroup()
                        .addComponent(searchedbookScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(searchedbooksPanelLayout.createSequentialGroup()
                        .addComponent(addsearchedbooktowishlistButton)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        searchLabel.setText("Search for Books:");

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        viewAllButton.setText("View All");
        viewAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchTabLayout = new javax.swing.GroupLayout(searchTab);
        searchTab.setLayout(searchTabLayout);
        searchTabLayout.setHorizontalGroup(
            searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchedbooksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(searchTabLayout.createSequentialGroup()
                        .addComponent(searchLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewAllButton)))
                .addContainerGap())
        );
        searchTabLayout.setVerticalGroup(
            searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchLabel)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(viewAllButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchedbooksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Search", searchTab);

        wishlistPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Wishlist"));

        removebookfromwishlistButton.setText("Remove from Wishlist");
        removebookfromwishlistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removebookfromwishlistButtonActionPerformed(evt);
            }
        });

        wishlistScrollPane.setViewportView(wishlistTable);

        javax.swing.GroupLayout wishlistPanelLayout = new javax.swing.GroupLayout(wishlistPanel);
        wishlistPanel.setLayout(wishlistPanelLayout);
        wishlistPanelLayout.setHorizontalGroup(
            wishlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wishlistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wishlistScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removebookfromwishlistButton)
                .addContainerGap())
        );
        wishlistPanelLayout.setVerticalGroup(
            wishlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wishlistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(wishlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(wishlistPanelLayout.createSequentialGroup()
                        .addComponent(removebookfromwishlistButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(wishlistPanelLayout.createSequentialGroup()
                        .addComponent(wishlistScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        submitrequestButton.setText("Submit Request");
        submitrequestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitrequestButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout wishlistTabLayout = new javax.swing.GroupLayout(wishlistTab);
        wishlistTab.setLayout(wishlistTabLayout);
        wishlistTabLayout.setHorizontalGroup(
            wishlistTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wishlistTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(wishlistTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wishlistPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wishlistTabLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(submitrequestButton)))
                .addContainerGap())
        );
        wishlistTabLayout.setVerticalGroup(
            wishlistTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wishlistTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wishlistPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitrequestButton)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Wishlist", wishlistTab);

        borrowinformationTabbedPane.setToolTipText("");

        tobecollectedlPane.setViewportView(tobecollectedTable);

        javax.swing.GroupLayout tobecollectedTabLayout = new javax.swing.GroupLayout(tobecollectedTab);
        tobecollectedTab.setLayout(tobecollectedTabLayout);
        tobecollectedTabLayout.setHorizontalGroup(
            tobecollectedTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tobecollectedTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tobecollectedlPane, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );
        tobecollectedTabLayout.setVerticalGroup(
            tobecollectedTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tobecollectedTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tobecollectedlPane, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        borrowinformationTabbedPane.addTab("To be collected", tobecollectedTab);

        borrowedScrollPane.setViewportView(borrowedTable);

        javax.swing.GroupLayout borrowedTabLayout = new javax.swing.GroupLayout(borrowedTab);
        borrowedTab.setLayout(borrowedTabLayout);
        borrowedTabLayout.setHorizontalGroup(
            borrowedTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowedTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(borrowedScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );
        borrowedTabLayout.setVerticalGroup(
            borrowedTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowedTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(borrowedScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        borrowinformationTabbedPane.addTab("Borrowed/To be returned", borrowedTab);

        overduePane.setViewportView(overdueTable);

        javax.swing.GroupLayout overdueTabLayout = new javax.swing.GroupLayout(overdueTab);
        overdueTab.setLayout(overdueTabLayout);
        overdueTabLayout.setHorizontalGroup(
            overdueTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overdueTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(overduePane, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );
        overdueTabLayout.setVerticalGroup(
            overdueTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overdueTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(overduePane, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        borrowinformationTabbedPane.addTab("Overdue/To be returned", overdueTab);

        javax.swing.GroupLayout borrowinformationTabLayout = new javax.swing.GroupLayout(borrowinformationTab);
        borrowinformationTab.setLayout(borrowinformationTabLayout);
        borrowinformationTabLayout.setHorizontalGroup(
            borrowinformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowinformationTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(borrowinformationTabbedPane)
                .addContainerGap())
        );
        borrowinformationTabLayout.setVerticalGroup(
            borrowinformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowinformationTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(borrowinformationTabbedPane)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Borrow Information", borrowinformationTab);

        loginButton.setText("Log in");
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        activateButton.setText("Activate");
        activateButton.setEnabled(false);
        activateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activateButtonActionPerformed(evt);
            }
        });

        userprofileButton.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        userprofileButton.setText("Guest");
        userprofileButton.setEnabled(false);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainTabbedPane)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(accountPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(170, 170, 170)
                        .addComponent(userprofileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(activateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loginButton)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(accountPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(loginButton)
                        .addComponent(activateButton)
                        .addComponent(userprofileButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainTabbedPane)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        if (loginState == false) {
            java.awt.EventQueue.invokeLater(() -> {
                instance.setEnabled(false);
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
                loginScreen.setLocationRelativeTo(null);
                loginScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            });
        } else {
            setLoginStateToFalse();
        }

    }//GEN-LAST:event_loginButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        try {
            // TODO add your handling code here:
            String bookToSearch = searchTextField.getText();
            ArrayList searchedBook = new BookController().getBooksByTitle(bookToSearch);
            setValueToSearchedBookTableModel(searchedBook);
        } catch (SQLException ex) {
            Logger.getLogger(HomeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
        searchButtonActionPerformed(evt);
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void viewAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllButtonActionPerformed
        // TODO add your handling code here:
        ArrayList allBooks;
        try {
            allBooks = new BookController().getAllBooks();
            setValueToSearchedBookTableModel(allBooks);
        } catch (SQLException ex) {
            Logger.getLogger(HomeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_viewAllButtonActionPerformed

    private void addsearchedbooktowishlistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addsearchedbooktowishlistButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = searchedbookTable.getSelectedRow();
        if (selectedRow != -1) {
            String bookToAddName = searchedbookTableModel.getValueAt(selectedRow, 0).toString();
            boolean bookExisted = false;
            for (int i = 0; i < wishlistTableModel.getRowCount(); i++) {
                String existedBookName = wishlistTableModel.getValueAt(i, 0).toString();
                if (existedBookName.equals(bookToAddName)) {
                    bookExisted = true;
                }
            }
            if (bookExisted == false) {
                wishlistTableModel.addRow(new Object[]{bookToAddName, searchedbookTableModel.getValueAt(selectedRow, 1).toString(), searchedbookTableModel.getValueAt(selectedRow, 2).toString(), searchedbookTableModel.getValueAt(selectedRow, 3).toString()});
            }
        }

    }//GEN-LAST:event_addsearchedbooktowishlistButtonActionPerformed

    private void removebookfromwishlistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removebookfromwishlistButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = wishlistTable.getSelectedRow();
        if (selectedRow != -1) {
            wishlistTableModel.removeRow(selectedRow);
        }
    }//GEN-LAST:event_removebookfromwishlistButtonActionPerformed

    private void submitrequestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitrequestButtonActionPerformed
        // TODO add your handling code here:
        ArrayList requestedBooks = getValueFromWishlistTableModel();
        if (requestedBooks.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "The Wishlist is empty!", "Empty list", JOptionPane.WARNING_MESSAGE);
        } else {
            for (int i = 0; i < requestedBooks.size(); i++) {
                Book book = (Book) requestedBooks.get(i);
                System.out.println(book.getTitle());
                new BorrowEntryController().createNewBorrowEntry(loggedUser, book);
            }

            setValueToWishlistTableModel(new ArrayList());

            JOptionPane.showMessageDialog(rootPane, "The Wishlist successfully requested!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        setBorrowInformation();

    }//GEN-LAST:event_submitrequestButtonActionPerformed

    private void activateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activateButtonActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(() -> {
            instance.setEnabled(false);
            ActivateScreen activateScreen = new ActivateScreen();
            activateScreen.setVisible(true);
            activateScreen.setLocationRelativeTo(null);
            activateScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });
    }//GEN-LAST:event_activateButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accountPanel;
    private javax.swing.JButton activateButton;
    private javax.swing.JButton addsearchedbooktowishlistButton;
    private javax.swing.JScrollPane borrowedScrollPane;
    private javax.swing.JPanel borrowedTab;
    private javax.swing.JTable borrowedTable;
    private javax.swing.JPanel borrowinformationTab;
    private javax.swing.JTabbedPane borrowinformationTabbedPane;
    private javax.swing.JLabel librarysystemLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JScrollPane overduePane;
    private javax.swing.JPanel overdueTab;
    private javax.swing.JTable overdueTable;
    private javax.swing.JButton removebookfromwishlistButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JPanel searchTab;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JScrollPane searchedbookScrollPane;
    private javax.swing.JTable searchedbookTable;
    private javax.swing.JPanel searchedbooksPanel;
    private javax.swing.JButton submitrequestButton;
    private javax.swing.JPanel tobecollectedTab;
    private javax.swing.JTable tobecollectedTable;
    private javax.swing.JScrollPane tobecollectedlPane;
    private javax.swing.JButton userprofileButton;
    private javax.swing.JButton viewAllButton;
    private javax.swing.JPanel wishlistPanel;
    private javax.swing.JScrollPane wishlistScrollPane;
    private javax.swing.JPanel wishlistTab;
    private javax.swing.JTable wishlistTable;
    // End of variables declaration//GEN-END:variables
}
