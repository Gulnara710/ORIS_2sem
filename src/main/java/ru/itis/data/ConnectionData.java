package ru.itis.data;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionData {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/spring1",
                    "postgres", "Uekz2005");
        } catch (SQLException e) {
            throw new RuntimeException("ошибка с бд", e);
        }
    }
}
