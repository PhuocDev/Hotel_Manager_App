package View;

import Model.SignUpModel;

import java.util.Scanner;

public class SignUpView {
    Scanner scanner = new Scanner(System.in);
    public void showMessage(String message) {
        System.out.println(message);
    }
    public SignUpModel getUserInfo() {
        SignUpModel userInfo = new SignUpModel();
        System.out.println("------------\nSign up screen:");
        System.out.println("Sign up view _ Ho va ten: ");
        String hoTen = scanner.nextLine();
        System.out.println("Sign up view _ Username:     ");
        String username = scanner.nextLine();
        System.out.println("Sign up view _ Password:     ");
        String pass = scanner.nextLine();
        System.out.println("Sign up view _ ReEnter password:    ");
        String confirmPass = scanner.nextLine();

        while (!confirmPass.equals(pass) && checkValidUsername(username)) {
            System.out.println("Invalid password or username! Please enter it again!");
            System.out.println("Sign up view _ Username:     ");
            username = scanner.nextLine();
            System.out.println("Sign up view _ Password:     ");
            pass = scanner.nextLine();
            System.out.println("Sign up view _ ReEnter password:    ");
            confirmPass = scanner.nextLine();
        }
        userInfo.setUsername(username);
        userInfo.setPassword(pass);
        userInfo.setHoTen(hoTen);
        return userInfo;
    }

    private boolean checkValidUsername(String username) {
        //under construction
        return true;
    }


}
