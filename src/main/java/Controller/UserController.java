package Controller;

import DAO.RoomDAO;
import DAO.UserDAO;
import Model.Room;
import Model.User;
import View.RoomView;
import View.UserView;
import Connection.DBconnection;
import java.awt.image.CropImageFilter;
import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserController {

    private Scanner scanner = new Scanner(System.in);
    private UserView userView;
    private UserDAO userDAO;
    private User user;
    private RoomView roomView;

    public UserController() {
        userView = new UserView();
        userDAO = new UserDAO();
        user = new User();
        roomView = new RoomView();
    }

    public UserController(UserView userView) {
        this.userView = userView;
    }

    public void creatNewUser() {
        System.out.println("-----------\nCreate new user: ");
        userView = new UserView();
        user = userView.getUserInfo();
        userDAO.addUser(user);
    }
    public void showAllUser() {
        ArrayList<User> danhSachUser = new ArrayList<>();
        danhSachUser = userDAO.getAll();
        if(danhSachUser != null && !danhSachUser.isEmpty()) {
            userView.showAll(danhSachUser);
        }
    }
    public void login() {
        LoginController loginController = new LoginController();
        loginController.login();
        this.user = loginController.getUserInfor();
        user.setRole(getRole(user.getUserID()));
        System.out.println(user);
        System.out.println("Xin chao " + user.getRole() + ": " + user.getHoTen());
    }
    public void register() {
        SignUpController signUpController = new SignUpController();
        signUpController.signUp();
    }
    public void registerForCustomer() {
        SignUpController signUpController = new SignUpController();
        signUpController.signUpCustomer();
    }
    public void registerForClerk() {
        SignUpController signUpController = new SignUpController();
        signUpController.signUpClerk();
    }
    public User getUser() {
        return user;
    }

    public static String getRole(String userIDRole) {
        String result = "";
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
    public String getRole() {
        return user.getRole();
    }
    public String getUserID() {
        return user.getUserID();
    }
    public void logOut() {
        this.userView = new UserView();
        this.userDAO = new UserDAO();
        this.user = new User();
    }

    //Hotel clerk / admin view all room function:
    public void viewAllRoom() {
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Room> dsRoom = new ArrayList<>();
        dsRoom = roomDAO.getAll();
        if (!dsRoom.isEmpty() && dsRoom != null) {
            roomView.showAll(dsRoom);
        }
    }
    //Filter empty room
    public void searchEmptyRoom() {
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Room> dsEmptyRoom = new ArrayList<>();
        dsEmptyRoom = roomDAO.getAlEmptyRoom();
        if (!dsEmptyRoom.isEmpty() && dsEmptyRoom != null) {
            System.out.println("Danh sach phong trong (empty): ");
            roomView.showAll(dsEmptyRoom);
        }
    }

    //Hotel clerk update status
    public void updateStatusRoom() {
        RoomDAO roomDAO = new RoomDAO();
        System.out.println("Nhap roomID can update: ");
        String roomID = scanner.nextLine();
        System.out.println("Nhap status moi (empty or booked):");
        String status = scanner.nextLine();
        //be careful! this can be logic confict with booking table
        roomDAO.updateRoomStatus(roomID, status);
    }

    // customer booking room
    public void bookingRoomForCustomer(String userID) {
        RoomDAO roomDAO = new RoomDAO();
        System.out.println("Nhap roomID muon dat: ");
        String roomID = scanner.nextLine();
        System.out.println("Nhap ngay booking (mm/dd/yyyy): ");
        String ngayBook = scanner.nextLine();
        roomDAO.addBooking(userID, roomID, ngayBook);
    }

    // Admin Set role
    public void setRole() {
        UserDAO userDAO1 = new UserDAO();
        System.out.println("Nhap userID can update role: ");
        String userID = scanner.nextLine();
        System.out.println("Nhap role muon update: 1. Clerk    2. Admin   3. Customer");
        int option = 0;
        option = scanner.nextInt(); scanner.nextLine();
        switch (option) {
            case 1:
                userDAO1.setRole(userID, "clerk");
                break;
            case 2:
                userDAO1.setRole(userID, "admin");
                break;
            case 3:
                userDAO1.setRole(userID, "customer");
                break;
            default:
                System.out.println("Invalid input");
        }
    }
}
