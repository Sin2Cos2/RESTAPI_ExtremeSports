package com.attaproject.mapper;

import com.attaproject.model.LocationSport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationSportMapper implements RowMapper<LocationSport> {

    public static final String BASE_SQL = "SELECT * from locations_x_sports lxs";
    public static final String DELETE_SQL = "DELETE from locations_x_sports lxs";

    @Override
    public LocationSport mapRow(ResultSet rs, int rowNum) throws SQLException {

        int locationId = rs.getInt("location_id");
        int sportId = rs.getInt("sport_id");
        Double price = rs.getDouble("price");
        Date startDate = rs.getDate("start_date");
        Date endDate = rs.getDate("end_date");

        return new LocationSport(locationId, sportId, price, startDate, endDate);
    }
}
