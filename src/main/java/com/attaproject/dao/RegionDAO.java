package com.attaproject.dao;

import com.attaproject.mapper.RegionMapper;
import com.attaproject.model.Region;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RegionDAO extends JdbcDaoSupport {

    public RegionDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Region> getRegionsByCountryId(Integer id){
        String sql = RegionMapper.BASE_SQL + " where r.country_id = ?";
        RegionMapper mapper = new RegionMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, mapper, id);
    }

    public List<Region> getRegions() {
        String sql = RegionMapper.BASE_SQL;
        RegionMapper mapper = new RegionMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public Region getRegion(String name) {
        String sql = RegionMapper.BASE_SQL + " where r.region_name like ?";
        RegionMapper regionMapper = new RegionMapper();

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForObject(sql, regionMapper, name);
    }
}
