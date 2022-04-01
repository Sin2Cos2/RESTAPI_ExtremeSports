package com.attaproject.dao;

import com.attaproject.controller.LocationController;
import com.attaproject.controller.LocationSportController;
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
    private LocationSportController locationSportController;

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
        return locationSportController.getSportLocation(sportId);
    }

    public Sport getSport(int sportId) {

        String sql = SportMapper.BASE_SQL + " where id = ?";
        SportMapper sportMapper = new SportMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForObject(sql, sportMapper, sportId);
    }

    public boolean deleteSport(String name) {
        Sport sport = this.getSport(name);
        locationSportController.deleteSport(sport);
        String sql;

        sql = SportMapper.DELETE_SQL + " where s.id = ?";
        return this.getJdbcTemplate().update(sql, sport.getId()) == 1;
    }

    public boolean addSport(SportRequest sport) {
        String sql = SportMapper.POST_SQL + " values(?)";
        this.getJdbcTemplate().update(sql, sport.getName());

        Sport s = this.getSport(sport.getName());
        assert this.getJdbcTemplate() != null;

        return locationSportController.addSport(sport, s);
    }
    

    public boolean updateSport(SportRequest sport) {
        return locationSportController.updateSport(sport);
    }
}
