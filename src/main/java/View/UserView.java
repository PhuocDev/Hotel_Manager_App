package View;

import Model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserView {
    private Scanner scanner = new Scanner(System.in);
    public void showMessage(String mess) {
        System.out.println("UserView: " + mess);
    }
    public User getUserInfo(){
        User user = new User();
        System.out.println("UserID: ");
        user.setUserID(scanner.nextLine());
        System.out.println("Ho ten: ");
        user.setHoTen(scanner.nextLine());
        System.out.println("Role: ");
        user.setRole(scanner.nextLine());
        System.out.println("Username: ");
        user.setUsername(scanner.nextLine());
        System.out.println("Password: ");
        user.setPassword(scanner.nextLine());
        return  user;
    }
    public void showAll(ArrayList<User> dsUser) {
        for(User user : dsUser) {
            System.out.println(user);
        }
    }

}
