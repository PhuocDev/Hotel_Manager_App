package DAO;

import Connection.DBconnection;
import Model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    public UserDAO() {

    }
    public void addUser(User user) {
        try {
            DBconnection.open();
            String sqlQuery = "INSERT INTO userAccount VALUES" +
                    "(?,?,?,?,?)";
            PreparedStatement preparedStatement = DBconnection.getConnection()
                    .prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getUserID() );
            preparedStatement.setString(2, user.getHoTen() );
            preparedStatement.setString(3, user.getRole() );
            preparedStatement.setString(4, user.getUsername() );
            preparedStatement.setString(5, user.getPassword() );
            preparedStatement.executeUpdate();
            System.out.println("Da them user moi thanh cong");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.close();
        }
    }
    public ArrayList<User> getAll() {
        ArrayList<User> dsUser = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DBconnection.getURL());
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * from userAccount";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                //System.out.println(resultSet.getString("username"));
                String userID = resultSet.getString("userID").replaceAll(" ", "");
                String hoTen = resultSet.getString("hoTen").replaceAll(" ", "");
                String username = resultSet.getString("username").replaceAll(" ","");
                String password = resultSet.getString("password").replaceAll(" ","");
                String role = getRole(userID);
                User user = new User(userID, hoTen,role, username, password);
                dsUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsUser;
    }

    private String getRole(String userIDRole) {
        String result = "unknow";
        try {
            Connection connection = DriverManager.getConnection(DBconnection.getURL());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from role" );
            while (resultSet.next()) {
                String userID = resultSet.getString("userID").replaceAll(" ","");
                String role = resultSet.getString("role").replaceAll(" ","");
                //System.out.println(username + "+" + username.length() + "+" + password + "+" + password.length()  +";");
                if (userIDRole.equals(userID)) {
                    result = role;
                }
            }
        } catch (SQLException e) {
            System.out.println("loi check role");
            e.printStackTrace();
        }
        return result;
    }

    public void setRole(String userID, String role) {
        try {
            DBconnection.open();
            String sqlQuery = "Update role SET role = ? Where userID = ?";
            PreparedStatement preparedStatement = DBconnection.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setString(1, role);
            preparedStatement.setString(2, userID);
            preparedStatement.executeUpdate();
            System.out.println("Role Updated successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBconnection.close();
        }
    }
}
