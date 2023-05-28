package com.example.garage.dop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellingCarsByModel {
    public static List<String> getSellingCars(String carModel) {
        Connection connection = ConnectHandler.getConnection();
        List<String> answer = new ArrayList<>();
        try {
            String query = "SELECT id, brand, model_id, price, year, mileage from car WHERE model_id=? and id in (SELECT id from pts WHERE owner_id > 100)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, carModel);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                answer.add("Id: " + resultSet.getString(1) +
                        "*Brand: " + resultSet.getString(2) +
                        "*Model: " + resultSet.getString(3) +
                        "*Price: " + resultSet.getInt(4) +
                        "*Year: " + resultSet.getInt(5) +
                        "*Mileage: " + resultSet.getInt(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return answer;
    }

//    public static void main(String[] args) {
//        List<String> cars = getSellingCars("Solaris");
//        cars.forEach(c -> System.out.println(c + "\n"));
//    }
}
