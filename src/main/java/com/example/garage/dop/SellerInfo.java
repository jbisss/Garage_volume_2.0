package com.example.garage.dop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerInfo {

    public static String getOwnerInfo(int id) {
        String answer;
        Connection connection = ConnectHandler.getConnection();
        ResultSet rs;
        try {
            String query = "SELECT name, surname, rating FROM seller WHERE id = (SELECT owner_id FROM pts WHERE id = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            rs.next();
            answer = rs.getString(1) + "*" + rs.getString(2) + "*" + rs.getString(3);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return answer;
    }
}
