package com.example.garage.dop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailInfoAboutModel {
    public static String getSellingCars(String model_id) {

        Connection connection = ConnectHandler.getConnection();
        String answer = "";
        try {
            String query = "SELECT category, engine, country FROM carmodel WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, model_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            answer = "Necessary drivers license's category: " + resultSet.getString(1) +
                    "*Engine: " + resultSet.getString(2) +
                    "*Manufacture country: " + resultSet.getString(3);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return answer;
    }

//    public static void main(String[] args) {
//        System.out.println(getSellingCars("Q7"));
//    }
}
