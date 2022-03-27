package com.attaproject.dao;

import com.attaproject.mapper.LocationSportMapper;
import com.attaproject.mapper.SportMapper;
import com.attaproject.model.LocationSport;
import com.attaproject.model.Sport;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SportDAO extends JdbcDaoSupport {

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
}
