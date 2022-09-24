package View;

import Model.LoginModel;
import Model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginView {
    Scanner scanner = new Scanner(System.in);
    public void showMessage(String message) {
        System.out.println(message);
    }
//    public User getUserInfor() {
//        User user = new User();
//        System.out.println("Username: ");
//        user.setUsername(scanner.nextLine());
//        System.out.println("Password:");
//        user.setPassword(scanner.nextLine());
//        return user;
//    }
    public LoginModel getUserInfor() {
        LoginModel user = new LoginModel();
        System.out.println("Username: ");
        user.setUsername(scanner.nextLine());
        System.out.println("Password:");
        user.setPassword(scanner.nextLine());
        return user;
    }

    public void showAll(ArrayList<User> listAccounts) {
        System.out.println("Danh sach account:");
        for (User account : listAccounts ){
            System.out.println(account);
        }
    }
}
