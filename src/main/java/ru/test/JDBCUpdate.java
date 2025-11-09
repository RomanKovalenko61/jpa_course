package ru.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCUpdate {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "jpauser";
    static final String PASSWORD = "jpapwd";

    public static void main(String[] args) {

        String UPDATE_SQL = "UPDATE test_db.students SET avg_grade = 7.5 WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter name: ");
            String enteredName = scanner.nextLine();

            preparedStatement.setString(1, enteredName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
