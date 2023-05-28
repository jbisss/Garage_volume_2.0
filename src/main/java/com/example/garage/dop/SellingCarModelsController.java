package com.example.garage.dop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellingCarModelsController {

    public static List<String> getSellingCars() {
        Connection connection = ConnectHandler.getConnection();
        List<String> answer = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT brand, model_id from car WHERE id in (SELECT id from pts WHERE owner_id > 100)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                answer.add(resultSet.getString(1) + "-" + resultSet.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return answer;
    }

//    public static void main(String[] args) {
//        List<String> cars = getSellingCars();
//        cars.forEach(System.out::println);
//    }
}