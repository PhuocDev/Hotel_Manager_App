package App;

import Controller.UserController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        LoginController loginController = new LoginController();
//        loginController.login();
        /*
        Testing account:
        admin:  admin - admin
        clerk: nv001 - nv001
        customer: cu001 - cu001
        Ứng với mỗi tài khoản sẽ có các chức năng khác nhau phụ thuộc vào role của user.
        (Phân loại tự động dựa theo role)
         */
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-------------\nMan hinh dang nhap: ");
            UserController user = new UserController();
            user.login();
            //use this var to exit/continue the loop
            boolean continueLoop = true;
            while (continueLoop) {
                if (user.getRole().equals("admin")) {
                    System.out.println("Chon chuc nang cua admin: 1. Create new user   2. Set role    " +
                            "3. Log out  4. View all account");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            adminCreateUser(user);
                            break;
                        case 2:
                            setRole(user);
                            break;
                        case 3:
                            user.logOut();
                            continueLoop = false;
                            break;
                        case 4:
                            user.showAllUser();
                            break;
                        default:
                            System.out.println("Invalid input (1,2,3)");
                            break;
                    }
                } else if (user.getRole().equals("clerk")) {
                    System.out.println("Chon chuc nang cua clerk:\n1.View all room   2. Search all empty room   " +
                            "3. Update room status    4. Log out");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            user.viewAllRoom();
                            break;
                        case 2:
                            user.searchEmptyRoom();
                            break;
                        case 3:
                            user.updateStatusRoom();
                            break;
                        case 4:
                            user.logOut();
                            continueLoop = false;
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                }else  {
                    System.out.println("Chon chuc nang cua customer:\n1. Register    2. booking    3. Log out");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            userRegister(user);
                            break;
                        case 2:
                            booking(user);
                            break;
                        case 3:
                            user.logOut();
                            continueLoop = false;
                            break;
                        default:
                            System.out.println("Invalid input (1,2,3)");
                            break;
                    }
                }
            }
        }
    }

    private static void booking(UserController user) {
        System.out.println("Chuc nang booking cua customer");
        user.bookingRoomForCustomer(user.getUserID());
    }

    private static void userRegister(UserController user) {
        System.out.println("Chuc nang register cua khach hang");
        user.registerForCustomer();
    }

    private static void setRole(UserController user) {
        System.out.println("Chuc nang set role (for admin)");
        user.setRole();
    }

    private static void adminCreateUser(UserController user) {
        System.out.println("Chuc nang create user");
        System.out.println("Create:   1. Clerk    2. Customer ");
        int option = 0;
        Scanner sc = new Scanner(System.in);
        option = sc.nextInt();
        switch (option) {
            case 1:
                user.registerForClerk();
                break;
            case 2:
                user.registerForCustomer();
                break;
            default:
                System.out.println("invalid input");
        }
    }
}
