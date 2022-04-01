package com.attaproject.dao;

import com.attaproject.controller.LocationController;
import com.attaproject.mapper.LocationSportMapper;
import com.attaproject.model.Location;
import com.attaproject.model.LocationSport;
import com.attaproject.model.Sport;
import com.attaproject.requestForm.*;
import com.attaproject.responseForm.LocationSportResponse;
import com.attaproject.responseForm.LocationSportResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LocationSportDAO extends JdbcDaoSupport {

    @Autowired
    private SportDAO sportDAO;
    @Autowired
    @Lazy
    private LocationController locationController = new LocationController();

    public LocationSportDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public boolean updateLocationsSport(LocationRequest location) {
        String sql = LocationSportMapper.UPDATE_SQL + " SET " +
                " price = ?, start_date = ?, end_date = ? " +
                "WHERE location_id = ? AND sport_id = ?";
        Object[] objects;
        for (LocationSportRequest sport : location.getLocationSport()) {
            objects = checkForUpdates(location, sport);

            assert this.getJdbcTemplate() != null;
            this.getJdbcTemplate().update(sql, objects);
        }

        return true;
    }

    private Object[] checkForUpdates(LocationRequest location, LocationSportRequest sport) {
        Object[] objects;
        LocationSport locationSport = this.getLocationSports(location.getName(), sport.getName());

        locationSport.setPrice(sport.getPrice() == null ? locationSport.getPrice() : sport.getPrice());
        locationSport.setStartDate(sport.getStartDate() == null ? locationSport.getStartDate() : sport.getStartDate());
        locationSport.setEndDate(sport.getEndDate() == null ? locationSport.getEndDate() : sport.getEndDate());

        objects = new Object[]{locationSport.getPrice(), locationSport.getStartDate(), locationSport.getEndDate(),
                locationSport.getLocationId(), locationSport.getSportId()};
        return objects;
    }

    private LocationSport getLocationSports(String locationName, String sportName) {
        Location location = locationController.getLocation(locationName);
        Sport sport = sportDAO.getSport(sportName);
        String sql = LocationSportMapper.BASE_SQL + " WHERE location_id = ? AND sport_id = ?";
        LocationSportMapper mapper = new LocationSportMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForObject(sql, mapper, location.getId(), sport.getId());
    }

    public List<LocationSport> getLocationSports(int locationId) {

        String sql = LocationSportMapper.BASE_SQL + " where lxs.location_id = ?";
        LocationSportMapper mapper = new LocationSportMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, mapper, locationId);
    }

    public void deleteLocation(Location location) {
        String sql = LocationSportMapper.DELETE_SQL + " where location_id = ?";
        assert this.getJdbcTemplate() != null;
        this.getJdbcTemplate().update(sql, location.getId());
    }

    public void addLocationSport(LocationRequest location, Location loc) {
        String sql;
        sql = LocationSportMapper.POST_SQL + " values(?, ?, ?, ?, ?)";
        Object[] objects;
        Sport sport;
        for (LocationSportRequest s : location.getLocationSport()) {
            sport = sportDAO.getSport(s.getName());
            if (sport == null)
                continue;
            objects = new Object[]{loc.getId(), sport.getId(), s.getPrice(), s.getStartDate(), s.getEndDate()};
            this.getJdbcTemplate().update(sql, objects);
        }
    }

    public List<LocationSport> getSportLocation(int sportId) {
        String sql = LocationSportMapper.BASE_SQL + " where lxs.sport_id = ?";
        LocationSportMapper mapper = new LocationSportMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, mapper, sportId);
    }

    public void deleteSport(Sport sport) {
        String sql = LocationSportMapper.DELETE_SQL + " where lxs.sport_id = ?";
        assert this.getJdbcTemplate() != null;
        this.getJdbcTemplate().update(sql, sport.getId());
    }

    public boolean addSport(SportRequest sport, Sport s) {
        String sql;
        sql = LocationSportMapper.POST_SQL + " values(?, ?, ?, ?, ?)";
        Object[] objects;
        for (SportLocationRequest location : sport.getLocations()) {
            Location loc = locationController.getLocation(location.getName());
            objects = new Object[]{loc.getId(), s.getId(),
                    location.getPrice(), location.getStartDate(), location.getEndDate()};
            this.getJdbcTemplate().update(sql, objects);
        }

        return true;
    }

    public boolean updateSport(SportRequest sport) {
        String sql = LocationSportMapper.UPDATE_SQL + " SET " +
                " price = ?, start_date = ?, end_date = ? " +
                "WHERE location_id = ? AND sport_id = ?";
        Object[] objects;
        for (SportLocationRequest location : sport.getLocations()) {
            objects = checkForUpdates(sport, location);

            assert this.getJdbcTemplate() != null;
            this.getJdbcTemplate().update(sql, objects);
        }

        return true;
    }

    private Object[] checkForUpdates(SportRequest sport, SportLocationRequest location) {
        Object[] objects;
        LocationSport locationSport = this.getSportLocations(sport.getName(), location.getName());

        locationSport.setPrice(location.getPrice() == null ? locationSport.getPrice() : location.getPrice());
        locationSport.setStartDate(location.getStartDate() == null ? locationSport.getStartDate() : location.getStartDate());
        locationSport.setEndDate(location.getEndDate() == null ? locationSport.getEndDate() : location.getEndDate());

        objects = new Object[]{locationSport.getPrice(), locationSport.getStartDate(), locationSport.getEndDate(),
                locationSport.getLocationId(), locationSport.getSportId()};
        return objects;
    }

    private LocationSport getSportLocations(String sportName, String locationName) {
        System.out.println(sportName);
        Sport sport = sportDAO.getSport(sportName);
        Location location = locationController.getLocation(locationName);
        String sql = LocationSportMapper.BASE_SQL + " WHERE location_id = ? AND sport_id = ?";
        LocationSportMapper mapper = new LocationSportMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForObject(sql, mapper, location.getId(), sport.getId());
    }

    public List<LocationSportResponse> getLocationSports(Date startDate, Date endDate) {
        String sql = LocationSportMapper.BASE_SQL;
        LocationSportMapper mapper = new LocationSportMapper();
        List<LocationSport> locationSports;
        List<LocationSportResponse> locationSportResponses = new ArrayList<>();

        if (startDate == null && endDate == null) {
            locationSports = this.getJdbcTemplate().query(sql, mapper);
        } else if (startDate == null) {
            sql += " where end_date <= ?";
            locationSports = this.getJdbcTemplate().query(sql, mapper, endDate);
        } else if (endDate == null) {
            sql += " where start_date >= ?";
            locationSports = this.getJdbcTemplate().query(sql, mapper, startDate);
        } else{
            sql += " where start_date >= ? AND end_date <= ?";
            locationSports = this.getJdbcTemplate().query(sql, mapper, startDate, endDate);
        }

        for (LocationSport locationSport : locationSports) {
            Location location = locationController.getLocation(locationSport.getLocationId());
            Sport sport = sportDAO.getSport(locationSport.getSportId());
            locationSportResponses
                    .add(new LocationSportResponse(location.getName(), sport.getName(), locationSport.getPrice()));
        }

        return locationSportResponses;
    }

    public List<LocationSportResponse> getLocationSports(SearchForm searchForm) {
        List<LocationSportResponse> responses = this.getLocationSports(searchForm.getStartDate(), searchForm.getEndDate());

        return responses
                .stream()
                .filter(l -> contains(searchForm.getSports(), l.getSportName()))
                .collect(Collectors.toList());
    }

    private boolean contains(List<String> sports, String sportName) {
        for (String sport : sports) {
            if(sport.equalsIgnoreCase(sportName))
                return true;
        }
        return false;
    }
}
