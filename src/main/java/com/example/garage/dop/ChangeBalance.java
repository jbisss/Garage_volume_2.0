package com.example.garage.dop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChangeBalance {

    public static void changeCustomerBalance(int id, int newBalance) {
        Connection connection = ConnectHandler.getConnection();
        try {
            String query = "UPDATE customer SET balance=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, newBalance);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
