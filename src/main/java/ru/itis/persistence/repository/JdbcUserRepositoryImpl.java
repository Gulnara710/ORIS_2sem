package ru.itis.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.persistence.entity.UserEntity;

import java.time.LocalDate;
import java.util.*;

@Profile("local")
@Repository
@RequiredArgsConstructor
public class JdbcUserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private final RowMapper<UserEntity> userMapper = (rs, rowNum) ->
            new UserEntity(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("name"),
                    rs.getObject("birth_date", LocalDate.class)
            );

    @Override
    public void save(UserEntity user) {

        String sql = "insert into users(id, name, birth_date) values(:id, :name, :birth_date)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("birth_date", user.getBirthDate());

        namedJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<UserEntity> getById(UUID uuid) {
        String sql = "select * from users where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource("id", uuid);

        try {
            UserEntity entity = namedJdbcTemplate.queryForObject(sql, params, userMapper);
            return Optional.of(entity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<UserEntity> getAll() {
        String sql = "select * from users";
        return namedJdbcTemplate.query(sql, userMapper);
    }

    @Override
    public void update(UserEntity user) {
        String sql = "update users set name = :name, birth_date = :birth_date where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("birth_date", user.getBirthDate())
                .addValue("id", user.getId());

        namedJdbcTemplate.update(sql, params);
    }

    @Override
    public boolean deleteById(UUID uuid) {

        String sql = "delete from users where id= :id";

        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", uuid);

        int deleted_rows = namedJdbcTemplate.update(sql, params);

        return deleted_rows > 0;
    }

    @Override
    public void deleteAll() {

        String sql = "delete from users";

        namedJdbcTemplate.update(sql, new MapSqlParameterSource());
    }

}