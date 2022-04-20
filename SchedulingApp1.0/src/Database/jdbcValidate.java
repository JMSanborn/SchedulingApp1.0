package Database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;

public class jdbcValidate {

    private static Statement statement;
    private static ResultSet result;
    private static String Login;
    private static final String FILENAME = "login_activity.txt";

    public static Boolean validateLogin(Integer userID, String userName, String userPassword) {
        jdbcValidate.validateConnection("SELECT * FROM users WHERE User_Name='" + userName + "' AND Password='" + userPassword + "'");
        ResultSet userSet = jdbcValidate.getResult();
        try {
            if (userSet.next()) {
                loginReport(userName, true);
                return true;
            } else {
                loginReport(userName, false);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Login Validation Error: " + e.getMessage());
            return false;
        }
    }

    public static void loginReport(String userName, boolean success){
        try (FileWriter fw = new FileWriter(FILENAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println(ZonedDateTime.now() + " User: " + userName + " Login_Attempt: " + (success ? " Success" : " Failure"));
        } catch (IOException e) {
            System.out.println("Login Report Error: " + e.getMessage());
        }
    }

    public static void validateConnection(String l){
        Login = l;

        try{
            statement = jdbc.getConnection().createStatement();
            System.out.println("Conn_Working");


            if(Login.toLowerCase().startsWith("select")){
                result = statement.executeQuery(Login);
                System.out.println("Login_Working");
            }
            if(Login.toLowerCase().startsWith("delete") || Login.toLowerCase().startsWith("insert") || Login.toLowerCase().startsWith("update")){
                statement.executeUpdate(Login);
            }
        } catch(Exception e){
            System.out.println("Connection Error: " + e.getMessage());
        }
    }

    public static ResultSet getResult(){
        return result;
    }


}
