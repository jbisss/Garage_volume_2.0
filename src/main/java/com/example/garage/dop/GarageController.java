package com.example.garage.dop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GarageController {

    public static List<String> getBoughtCars(int id) {
        Connection connection = ConnectHandler.getConnection();
        List<String> answer = new ArrayList<>();
        try {
            String query = "SELECT brand, model_id, price, year, mileage from car WHERE pts_id in (SELECT id FROM pts WHERE owner_id = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                answer.add("Brand: " + resultSet.getString(1) +
                        "*Model: " + resultSet.getString(2) +
                        "*Price: " + resultSet.getInt(3) +
                        "*Year: " + resultSet.getInt(4) +
                        "*Mileage: " + resultSet.getInt(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (answer.size() == 0)
            answer.add("You don't have any cars");
        return answer;
    }

//    public static void main(String[] args) {
//        getBoughtCars(1).forEach(System.out::println);
//    }
}
