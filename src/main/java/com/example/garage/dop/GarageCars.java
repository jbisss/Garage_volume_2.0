package com.example.garage.dop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GarageCars {
    public static void putCarToGarage(int owner_id, int car_id) {
        Connection connection = ConnectHandler.getConnection();
        List<String> answer = new ArrayList<>();
        try {
            //"UPDATE Person SET name=?, age=?, email=? WHERE id=?");
            String query = "UPDATE pts SET owner_id=? WHERE id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, owner_id);
            preparedStatement.setInt(2, car_id);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Congratulations on your new car!");
    }

//    public static void main(String[] args) {
//        putCarToGarage(1, 1);
//    }
}
