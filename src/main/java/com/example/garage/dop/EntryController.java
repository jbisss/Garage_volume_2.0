package com.example.garage.dop;

import java.sql.*;

public class EntryController {
    public static String tryToEnter(int id, String password) {
        Connection connection = ConnectHandler.getConnection();
        String answer = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, surname, balance FROM customer WHERE password=? AND id=?");

            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                answer = resultSet.getString(1) + "-" + resultSet.getString(2) + "-" + resultSet.getString(3) + "-" + resultSet.getString(4);
            else answer = "Invalid password or id, please, check your data";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return answer;
    }

    public static String tryToRegistrate(String name, String surname, String password, int balance) {
        Connection connection = ConnectHandler.getConnection();
        String answer = "";
        int id = 0;
        if (!checkData(balance))
            return "Please, check your data";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select max(id) from customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            id = resultSet.getInt(1) + 1;
            preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES(?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, surname);
            preparedStatement.setInt(5, balance);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        answer = "You registered and your id is " + id + " please save it!";
        return answer;
    }

    public static boolean checkData(int balance){
        return balance > 0;
    }

//    public static void main(String[] args) {
//        System.out.println(tryToRegistrate("new", "new", "newnew", 11_000_000));
//        System.out.println(tryToEnter(9, "newnew"));
//    }
}
