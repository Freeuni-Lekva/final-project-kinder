package ge.kinder.Database;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.*;



public class MyDatabase {
        private static Connection connection;
        private static final String DATABASE_FILE = "/DB.sql";

        public MyDatabase() throws SQLException {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(("jdbc:mysql://localhost:3306/Kinder_Base"),
                        "root", "rootroot");
            } catch (Exception ex) {
                System.out.println("Can`t connect to database");
            }
            try {
                ScriptRunner sr = new ScriptRunner(connection);
                Reader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(DATABASE_FILE)));
                sr.runScript(reader);
            } catch (Exception ex) {
                System.out.println("Can`t run script");
            }
        }
        public static Connection getConnection(){
            return connection;
        }

        public static void closeConnection() throws SQLException {
            if(connection!=null) connection.close();
        }


}





