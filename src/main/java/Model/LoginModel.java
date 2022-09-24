package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginModel {
    private String username;
    private String password;

    public LoginModel(ResultSet resultSet) {
        try {
            this.username = resultSet.getString("username").replaceAll(" ", "");
            this.password = resultSet.getString("PASSWORD").replaceAll(" ", "");
        } catch (SQLException e) {
            System.out.println("LoginModel - ResultSet Error: " + e.getMessage());
        }
    }
    public LoginModel() {

    }
    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "loginModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setUsername(String username) {
        Scanner sc = new Scanner(System.in);
        while (username.isEmpty() || username == null){
            System.out.println("Khong duoc bo trong!");
            System.out.println("Nhap lai username:  ");
            username = sc.nextLine();
        }
        this.username = username;
    }

    public void setPassword(String password) {
        Scanner sc = new Scanner(System.in);
        while (password.isEmpty() || password == null){
            System.out.println("Khong duoc bo trong!");
            System.out.println("Nhap lai password:  ");
            password = sc.nextLine();
        }
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
