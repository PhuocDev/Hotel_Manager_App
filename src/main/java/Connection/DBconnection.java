package Connection;

import java.sql.*;
import java.util.UUID;

public class DBconnection {

    private static final String JDBC_DRIVER = "com.microsoft.sqlsever.jdbc.SQLSeverDriver";
    //    private static final String DB_URL = "jdbc.sqlsever://localost:1433;databaseName=JDBCDemo;"
//            +"user=sa;password=Password.1";
    public static String url = "jdbc:sqlserver://localhost:1433;database=JDBC_Assignment;"
            + "user=sa; " + "password=Password.1;"
            + "encrypt=true;trustServerCertificate=true;";
    public static String getURL() {
        return url;
    }
    private static Connection connection = null;

    public static boolean open() {
        try {
            //Class.forName("JDBC_DRIVER");  //"com.microsoft.sqlserver.jdbc.SQLServerDriver"
            connection = DriverManager.getConnection(url);
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static Connection getConnection() {
        if(connection == null) {
            open();
        }
        return connection;
    }
    public static void close() {
        if(connection == null) {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("SQL Error:"+e.getMessage());
            }
        }
    }

    public static String testDB() throws SQLException {
        //Connection con = DBConnection.getConnection();
        try (Connection con = DriverManager.getConnection(url)) {
            if (con != null) {
                return  "Wellcome to Database";
            } else
                return  "Connect FAILED";
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        System.out.println(testDB());

    }


}
