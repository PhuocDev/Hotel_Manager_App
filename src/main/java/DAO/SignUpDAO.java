package DAO;

import Connection.DBconnection;
import Model.SignUpModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class SignUpDAO {

    public SignUpDAO() {

    }
    public void addAccount(SignUpModel signUpModel) {
        try {
            DBconnection.open();
            String sqlQuery = "INSERT INTO userAccount " + "(userID,hoTen, username, password) VALUES "
                    + "(?,?,?,?)";
            PreparedStatement preparedStatement = DBconnection.getConnection()
                    .prepareStatement(sqlQuery);
            preparedStatement.setString(1, generateUserID());
            preparedStatement.setString(2, signUpModel.getHoTen());
            preparedStatement.setString(3, signUpModel.getUsername());
            preparedStatement.setString(4, signUpModel.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.close();
        }
    }

    private static String generateUserID() {
        String userID = UUID.randomUUID().toString().replaceAll("-", "").substring(0,5);
        return userID;
    }

    public void addAccountAndRole(SignUpModel customer, String role) {
        String userID = generateUserID();
        try {
            DBconnection.open();
            String sqlQuery = "INSERT INTO userAccount " + "(userID,hoTen, username, password) VALUES "
                    + "(?,?,?,?)";
            PreparedStatement preparedStatement = DBconnection.getConnection()
                    .prepareStatement(sqlQuery);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, customer.getHoTen());
            preparedStatement.setString(3, customer.getUsername());
            preparedStatement.setString(4, customer.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.close();
        }
        //them vai tro
        addRole(userID, role);
    }

    private static void addRole(String userID, String role) {
        try {
            DBconnection.open();
            String sqlQuery = "INSERT INTO role " + "(userID, role) VALUES "
                    + "(?,?)";
            PreparedStatement preparedStatement = DBconnection.getConnection()
                    .prepareStatement(sqlQuery);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, role);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.close();
        }
    }
}
