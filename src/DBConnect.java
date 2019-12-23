import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnect {
    public static void main (String[] args) {
        try {
            String host = "jdbc:mysql://localhost:3306/car_rental";
            String uName = "root";
            String uPass = "3920";
            Connection con = DriverManager.getConnection(host, uName, uPass);
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
}
