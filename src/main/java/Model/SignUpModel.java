package Model;

public class SignUpModel {
    private String username;
    private String password;

    private String hoTen;

    public SignUpModel() {

    }

    public SignUpModel(String username, String password, String hoTen) {
        this.username = username;
        this.password = password;
        this.hoTen = hoTen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
}
