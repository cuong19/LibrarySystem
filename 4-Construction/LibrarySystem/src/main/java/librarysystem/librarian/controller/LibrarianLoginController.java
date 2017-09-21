/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem.librarian.controller;

import librarysystem.database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import librarysystem.entity.Librarian;
import java.sql.PreparedStatement;

/**
 * Class này điều khiển các chức năng liên quan tới librarian
 *
 * @author pdc
 */
public class LibrarianLoginController {

    private final String GET_LOGIN = "select * from librarian where username=? and password=?";
    private final String SEARCH_USER_BY_USERNAME = "select * from librarian where username=?";
    Librarian librarian = new Librarian();
    private static LibrarianLoginController me;

    /**
     * Hàm khởi tạo là private để không đối tượng nào bên ngoài có thể khởi tạo
     * tuỳ ý đối tượng của lớp này
     */
    public LibrarianLoginController() {
    }

    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của
     * LibrarianLoginController nếu đối tượng đố null
     *
     * @return đối tượng dùng chung duy nhất của LibrarianLoginController
     */
    public static LibrarianLoginController getInstance() {
        if (me == null) {
            me = new LibrarianLoginController();
        }
        return me;
    }

    /**
     * Hàm này để kiểm tra người dùng có thể đăng nhập vào hệ thống không
     *
     * @param username là tên đăng nhập người dùng
     * @param password là mật khẩu người dùng
     *  *@return boolean return là : true: là đúng false: mật khẩu hoặc tên đăng
     * nhập sai
     *
     */
    /**
     * Hàm này để kiểm tra người dùng có thể đăng nhập vào hệ thống không
     *
     * @param username là tên đăng nhập người dùng
     * @param password là mật khẩu người dùng
     * @return boolean return là : true: là đúng false: mật khẩu hoặc tên đăng
     * nhập sai
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        if (findByUserName(username)) {
            PreparedStatement ps = databaseConnection.connection.prepareStatement(GET_LOGIN);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                databaseConnection.closeConnection();
                return 0; // đăng nhập thành công
            }
        } else {
            databaseConnection.closeConnection();
            return 2; // sai mật khẩu
        }
        return 1; // user không tồn tại
    }

    public boolean findByUserName(String username) throws SQLException, ClassNotFoundException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        PreparedStatement ps = databaseConnection.connection.prepareStatement(SEARCH_USER_BY_USERNAME);
        ps.setString(1, username);
        ps.executeQuery();
        ResultSet rs = ps.executeQuery();
        if (rs != null && rs.next()) {
            librarian.setUsername(rs.getString("username"));
            librarian.setPassword(rs.getString("password"));
            librarian.setFullname(rs.getString("full_name"));
            databaseConnection.closeConnection();
            return true;
        }
        databaseConnection.closeConnection();
        return false;
    }
}
