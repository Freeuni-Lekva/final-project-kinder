package Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

import org.apache.ibatis.jdbc.ScriptRunner;

public class ConnectionToDB {
        private Connection connection;
        private static final String DATABASE_FILE = "src/main/resources/DB.sql";

        public ConnectionToDB() throws SQLException {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(("jdbc:mysql://localhost:3306/Kinder_Base"),
                        "root", "admin");
            } catch (Exception ex) {
                System.out.println("Can`t connect to database");
            }
            try{
                ScriptRunner sr = new ScriptRunner(connection);
                Reader reader = new BufferedReader(new FileReader(DATABASE_FILE));
                sr.runScript(reader);
            } catch(Exception ex){
                System.out.println("Can`t run script");
            }




        }





}
