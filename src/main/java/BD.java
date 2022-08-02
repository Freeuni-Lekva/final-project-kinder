import java.sql.*;
import java.util.HashMap;

public class BD {
    private Connection connection;


    public BD() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(("jdbc:mysql://localhost:3306/Kinder_Base"),
                    "root", "rootroot");
        } catch (Exception ex) {
            System.out.println("Can`t connect to database");
        }
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = connection.createStatement();
            rs = stat.executeQuery("select * from Kinder_Base.products ");
        } catch (Exception ex) {
            System.out.println("Can`t execute query");
        }

        while (rs.next()) {
            String productid = rs.getString("productid");
            System.out.println("productid");
        }
    }
}