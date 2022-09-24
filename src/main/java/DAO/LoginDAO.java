package DAO;

import Model.LoginModel;
import Connection.DBconnection;
import java.sql.*;
import java.util.ArrayList;

public class LoginDAO {

    public ArrayList<LoginModel> getAll (){
        ArrayList<LoginModel> result = new ArrayList<LoginModel>();
        try {
            Connection connection = DriverManager.getConnection(DBconnection.getURL());
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * from LOGIN";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                //System.out.println(resultSet.getString("username"));
                String username = resultSet.getString("username").replaceAll(" ","");
                String password = resultSet.getString("PASSWORD").replaceAll(" ","");
                LoginModel loginModel = new LoginModel(username, password);
                result.add(loginModel);
                //System.out.println("Da them vao list");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean addAccount(LoginModel loginModel) {
        try {
            if (DBconnection.open()) {
                String sqlQuery = "INSERT INTO LOGIN " + "(username, password) VALUES "
                        + "(?,?)";
                PreparedStatement preparedStatement = DBconnection.getConnection()
                        .prepareStatement(sqlQuery);
                preparedStatement.setString(1, loginModel.getUsername());
                preparedStatement.setString(2, loginModel.getPassword());
                preparedStatement.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBconnection.close();
        }
    }

}
