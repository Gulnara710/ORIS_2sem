package ru.itis.repository;

import ru.itis.data.ConnectionData;
import ru.itis.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRepository {
    private Connection connection;

    @Autowired
    public UserRepository(ConnectionData  connectionData) {
        this.connection = connectionData.getConnection();
    }

    // create
    public void createUser(String name) {
        String sql = "insert into users (name) values (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error in create user", e);
        }
    }

    // read
    public UserEntity findUserById (Long id) {
        String sql = "select * from users where id  = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet resultSet = ps.executeQuery();) {

                if (resultSet.next()) {
                    return new UserEntity(resultSet.getLong("id"), resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("error in find user", e);
        }
        return null;
    }

    // update
    public void updateUser(Long id, String newName) {
        String sql = "update users set name = ? where id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error in update user", e);
        }
    }

    // delete
    public void deleteUser(Long id) {
        String sql = "delete from users where id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error in delete user", e);
        }
    }

}
