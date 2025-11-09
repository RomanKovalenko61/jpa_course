package ru.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCDelete {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "jpauser";
    static final String PASSWORD = "jpapwd";

    public static void main(String[] args) {

        String DELETE_SQL = "DELETE FROM test_db.students WHERE surname = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setString(1, "Surname");
            var deletedRows = preparedStatement.executeUpdate();

            System.out.println("DeletedRows = " + deletedRows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
