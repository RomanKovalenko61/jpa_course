package ru.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSelect {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "jpauser";
    static final String PASSWORD = "jpapwd";

    public static void main(String[] args) {

        String SELECT_SQL = "SELECT * FROM test_db.students WHERE avg_grade > ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)) {

            preparedStatement.setDouble(1, 9.0);
            var resultSet = preparedStatement.executeQuery();

            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setAvgGrade(resultSet.getDouble("avg_grade"));
                students.add(student);
            }

            students.forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
