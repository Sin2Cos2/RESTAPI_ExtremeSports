package com.attaproject.mapper;

import com.attaproject.model.Region;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionMapper implements RowMapper<Region> {

    public static final String BASE_SQL = "Select * from regions r";
    public static final String DELETE_SQL = "DELETE from regions r";

    @Override
    public Region mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer id = rs.getInt("id");
        String name = rs.getString("region_name");
        Integer countryId = rs.getInt("country_id");

        return new Region(id, name, countryId);
    }
}
