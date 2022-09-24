package Controller;
import Connection.DBconnection;
import DAO.LoginDAO;
import DAO.UserDAO;
import Model.LoginModel;
import Model.User;
import View.LoginView;

import java.sql.*;

public class LoginController {
    private LoginModel loginModel;
    private LoginView loginView;
    private UserDAO userDAO;
    private LoginDAO loginDAO;
    private User tempUser;
    private String username;
    private String password;
    public LoginController() {
        loginModel = new LoginModel();
        loginView = new LoginView();
        userDAO = new UserDAO();
        tempUser = new User();
    }
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }
    public void login() {
        while (true){
            this.loginModel = loginView.getUserInfor();
            if (checkLogin(loginModel)) {
                loginView.showMessage("Login success!");
                break;
            }
            else {
                loginView.showMessage("Login failed!");
            }
        }
    }

    private boolean checkLogin(LoginModel user) {
        boolean result = false;
        try {
            Connection connection = DriverManager.getConnection(DBconnection.getURL());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from userAccount ");
            while (resultSet.next()) {
                String username = resultSet.getString("username").replaceAll(" ","");
                String password = resultSet.getString("password").replaceAll(" ","");
                //System.out.println(username + "+" + username.length() + "+" + password + "+" + password.length()  +";");
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    result = true;
                    this.username = user.getUsername();
                    this.password = user.getPassword();
                    return result;
                }
            }
        } catch (SQLException e) {
            System.out.println("loi check login");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    public User getUserInfor() {
        try {
            Connection connection = DriverManager.getConnection(DBconnection.getURL());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from userAccount" );
            while (resultSet.next()) {
                String userID = resultSet.getString("userID").replaceAll(" ","");
                String hoTen = resultSet.getString("hoTen").replaceAll(" ","");
                String username1 = resultSet.getString("username").replaceAll(" ", "");
                String password1 = resultSet.getString("password").replaceAll(" ", "");
                //System.out.println(username + "+" + username.length() + "+" + password + "+" + password.length()  +";");
                if (this.username.equals(username1) && this.password.equals(password1)) {
                    tempUser.setUserID(userID);
                    tempUser.setUsername(username1);
                    tempUser.setPassword(password1);
                    tempUser.setHoTen(hoTen);
                }
            }
        } catch (SQLException e) {
            System.out.println("loi check login");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tempUser;
    }
//    public User getUserInfor() {
//        try {
//            Connection connection = DriverManager.getConnection(DBconnection.getURL());
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * from userAccount where username= '" + this.username
//                    + "' and password='" + this.password + "'" );
//            if (resultSet.next()) {
//                String userID = resultSet.getString("userID").replaceAll(" ","");
//                String hoTen = resultSet.getString("hoTen").replaceAll(" ","");
//                String username1 = resultSet.getString("username").replaceAll(" ", "");
//                String password1 = resultSet.getString("password").replaceAll(" ", "");
//                //System.out.println(username + "+" + username.length() + "+" + password + "+" + password.length()  +";");
//                tempUser.setUsername(username1);
//                tempUser.setPassword(password);
//                tempUser.setHoTen(hoTen);
//            } else return null;
//        } catch (SQLException e) {
//            System.out.println("loi check login");
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//        return tempUser;
//    }

    public User getTempUser() {
        return tempUser;
    }
}
