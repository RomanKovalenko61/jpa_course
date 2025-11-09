package ru.test;

import java.sql.*;

public class JDBCInsert_v3 {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "jpauser";
    static final String PASSWORD = "jpapwd";

    public static void main(String[] args) {

        Student student = new Student("Isaac", "Sharp", 9.8);
        String INSERT_SQL = "INSERT INTO test_db.students(name, surname, avg_grade) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setDouble(3, student.getAvgGrade());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("Failed to add student to DB");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getLong(1));
            } else {
                throw new RuntimeException("Failed to create student ID");
            }

            System.out.println(student);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
