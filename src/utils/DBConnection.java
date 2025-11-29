package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sg_cinema";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private DBConnection(){}
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL , USER , PASSWORD);
        } catch(SQLException e){
            System.out.println("Database connection failed: "+ e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
