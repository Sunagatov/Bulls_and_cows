package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionManager {
    private static Connection dbConnection = null;

    private ConnectionManager() {
    }

    static Connection getDbConnection() {
        if (dbConnection == null) {
            Connection dbConnection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                dbConnection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/bulls_and_cows?useUnicode=true&useJDBCCompliantTimezoneShift=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return dbConnection;
        }
        return dbConnection;
    }
}
