package com.attaproject.dao;

import com.attaproject.mapper.LocationMapper;
import com.attaproject.model.*;
import com.attaproject.requestForm.LocationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class LocationDAO extends JdbcDaoSupport {

    @Autowired
    private SportDAO sportDAO;
    @Autowired
    private LocationSportDAO locationSportDAO;

    public LocationDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Location> getLocations() {

        String sql = LocationMapper.BASE_SQL;
        LocationMapper locationMapper = new LocationMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, locationMapper);
    }

    public Location getLocation(String name) {

        String sql = LocationMapper.BASE_SQL + " where l.location_name like ?";
        LocationMapper locationMapper = new LocationMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForObject(sql, locationMapper, name);
    }

    public List<Location> getLocations(int regionId) {

        String sql = LocationMapper.BASE_SQL + " where l.region_id = ?";
        LocationMapper locationMapper = new LocationMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, locationMapper, regionId);
    }

    public Location getLocation(int locationId) {

        String sql = LocationMapper.BASE_SQL + " where l.id = ?";
        LocationMapper locationMapper = new LocationMapper();

        return this.getJdbcTemplate().queryForObject(sql, locationMapper, locationId);
    }

    public List<LocationSport> getLocationSports(int locationId) {

        return locationSportDAO.getLocationSports(locationId);
    }

    public Boolean deleteLocation(String name) {

        Location location = this.getLocation(name);
        locationSportDAO.deleteLocation(location);
        String sql;

        sql = LocationMapper.DELETE_SQL + " where l.id = ?";

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().update(sql, location.getId()) == 1;
    }

    public boolean addLocation(LocationRequest location, Region region, Country country) {
        String sql = LocationMapper.POST_SQL + " values(?, ?, ?)";
        assert this.getJdbcTemplate() != null;
        this.getJdbcTemplate().update(sql, location.getName(), region.getId(), country.getId());

        Location loc = this.getLocation(location.getName());
        locationSportDAO.addLocationSport(location, loc);

        return true;
    }

    //TODO: DELETE для определенного спорта из города или города из спорта
    public boolean updateLocation(LocationRequest location) {
        return locationSportDAO.updateLocationsSport(location);
    }

    //TODO: создать LocationSportDAO и перенести туда все нужные функции

}
