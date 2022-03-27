package com.attaproject.dao;

import com.attaproject.mapper.LocationMapper;
import com.attaproject.mapper.LocationSportMapper;
import com.attaproject.model.Location;
import com.attaproject.model.LocationSport;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class LocationDAO extends JdbcDaoSupport {

    public LocationDAO(DataSource dataSource){
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

    public List<LocationSport> getLocationsSports(int locationId) {

        String sql = LocationSportMapper.BASE_SQL + " where lxs.location_id = ?";
        LocationSportMapper mapper = new LocationSportMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, mapper, locationId);
    }
}
