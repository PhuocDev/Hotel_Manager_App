package Controller;

import DAO.SignUpDAO;
import Model.SignUpModel;
import View.SignUpView;

public class SignUpController {
    private SignUpDAO signUpDAO;
    private SignUpView signUpView;

    public SignUpController() {
        signUpDAO = new SignUpDAO();
        signUpView = new SignUpView();
    }
    public void signUp() {
        System.out.println("-----------------\nSign up new account");
        SignUpModel user = signUpView.getUserInfo();
        while (true) {
            if (checkValidUser(user)) {
                signUpDAO.addAccount(user);
                signUpView.showMessage("Sign up success!");
                break;
            }
            else {
                signUpView.showMessage("User information from view is Invalid!");
            }
        }
    }

    private boolean checkValidUser(SignUpModel user) {
        return true;
    }

    public void signUpCustomer() {
        System.out.println("-----------------\nSign up new account FOR CUSTOMER");
        SignUpModel user = signUpView.getUserInfo();
        while (true) {
            if (checkValidUser(user)) {
                signUpDAO.addAccountAndRole(user, "customer");
                signUpView.showMessage("(CUSTOMER) Sign up success!");
                break;
            }
            else {
                signUpView.showMessage("User information from view is Invalid!");
            }
        }
    }
    public void signUpClerk() {
        System.out.println("-----------------\nSign up new account FOR CLERK");
        SignUpModel user = signUpView.getUserInfo();
        while (true) {
            if (checkValidUser(user)) {
                signUpDAO.addAccountAndRole(user, "clerk");
                signUpView.showMessage("(CLERK) Sign up success!");
                break;
            }
            else {
                signUpView.showMessage("User information from view is Invalid!");
            }
        }
    }
}
