package com.attaproject.dao;

import com.attaproject.mapper.LocationMapper;
import com.attaproject.mapper.LocationSportMapper;
import com.attaproject.model.*;
import com.attaproject.requestForm.LocationRequest;
import com.attaproject.requestForm.LocationSportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class LocationDAO extends JdbcDaoSupport {

    @Autowired
    private SportDAO sportDAO;

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

    public Boolean deleteLocation(String name) {

        Location location = this.getLocation(name);
        String sql = LocationSportMapper.DELETE_SQL + " where location_id = ?";
        assert this.getJdbcTemplate() != null;
        this.getJdbcTemplate().update(sql, location.getId());

        sql = LocationMapper.DELETE_SQL + " where l.id = ?";

        return this.getJdbcTemplate().update(sql, location.getId()) == 1;
    }

    public boolean addLocation(LocationRequest location, Region region, Country country) {
        String sql = LocationMapper.POST_SQL + " values(?, ?, ?)";
        assert this.getJdbcTemplate() != null;
        this.getJdbcTemplate().update(sql, location.getName(),region.getId(), country.getId());

        sql = LocationSportMapper.POST_SQL + " values(?, ?, ?, ?, ?)";
        Location loc = this.getLocation(location.getName());
        Object[] objects;
        Sport sport;
        for(LocationSportRequest s : location.getLocationSport()){
            sport = sportDAO.getSport(s.getName());
            if(sport == null)
                continue;
            objects = new Object[]{loc.getId(), sport.getId(), s.getPrice(), s.getStartDate(), s.getEndDate()};
            this.getJdbcTemplate().update(sql, objects);
        }

        return true;
    }
}
