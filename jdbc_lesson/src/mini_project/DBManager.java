package mini_project;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    private static Connection connection;
    public static void connectToDB() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jdbc_test?useUnicode=true&serverTimezone=UTC",
                    "root",
                    "springcourse");

            System.out.println("CONNECTED TO DB");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return connection;
    }
}
