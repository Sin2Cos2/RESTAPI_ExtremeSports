package com.attaproject.dao;

import com.attaproject.controller.LocationController;
import com.attaproject.mapper.LocationSportMapper;
import com.attaproject.mapper.SportMapper;
import com.attaproject.model.Location;
import com.attaproject.model.LocationSport;
import com.attaproject.model.Sport;
import com.attaproject.requestForm.SportLocationRequest;
import com.attaproject.requestForm.SportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SportDAO extends JdbcDaoSupport {

    @Autowired
    LocationController locationController;

    public SportDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Sport> getSports() {

        String sql = SportMapper.BASE_SQL;
        SportMapper sportMapper = new SportMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, sportMapper);
    }

    public Sport getSport(String name) {

        String sql = SportMapper.BASE_SQL + " where s.sport_name like ?";
        SportMapper sportMapper = new SportMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForObject(sql, sportMapper, name);
    }

    public List<LocationSport> getSportLocation(int sportId) {
        String sql = LocationSportMapper.BASE_SQL + " where lxs.sport_id = ?";
        LocationSportMapper mapper = new LocationSportMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, mapper, sportId);
    }

    public Sport getSport(int sportId) {

        String sql = SportMapper.BASE_SQL + " where id = ?";
        SportMapper sportMapper = new SportMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForObject(sql, sportMapper, sportId);
    }

    public boolean deleteSport(String name) {
        Sport sport = this.getSport(name);
        String sql = LocationSportMapper.DELETE_SQL + " where lxs.sport_id = ?";
        assert this.getJdbcTemplate() != null;
        this.getJdbcTemplate().update(sql, sport.getId());

        sql = SportMapper.DELETE_SQL + " where s.id = ?";
        return this.getJdbcTemplate().update(sql, sport.getId()) == 1;
    }

    public boolean addSport(SportRequest sport) {
        String sql = SportMapper.POST_SQL + " values(?)";
        this.getJdbcTemplate().update(sql, sport.getName());

        Sport s = this.getSport(sport.getName());
        assert this.getJdbcTemplate() != null;

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
        Sport sport = this.getSport(sportName);
        Location location = locationController.getLocation(locationName);
        String sql = LocationSportMapper.BASE_SQL + " WHERE location_id = ? AND sport_id = ?";
        LocationSportMapper mapper = new LocationSportMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForObject(sql, mapper, location.getId(), sport.getId());
    }
}
