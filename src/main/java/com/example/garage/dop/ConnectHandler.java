package com.example.garage.dop;

/**
 * Hello world!
 *
 */
import java.sql.*;


public class ConnectHandler {
    private static final String URL = "jdbc:postgresql://localhost:5432/Rusik";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }

//    public static void main(String[] args) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car");
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                String carName = resultSet.getString(2) + " " + resultSet.getString(3);
//                System.out.println(carName);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
}