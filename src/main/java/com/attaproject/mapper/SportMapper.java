package com.attaproject.mapper;

import com.attaproject.model.Sport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SportMapper implements RowMapper<Sport> {

    public static final String BASE_SQL = "SELECT * from sports s";

    @Override
    public Sport mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer id = rs.getInt("id");
        String name = rs.getString("sport_name");

        return new Sport(id, name);
    }
}
