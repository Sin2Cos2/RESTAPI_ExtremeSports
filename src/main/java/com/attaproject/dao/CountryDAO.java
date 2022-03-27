package com.attaproject.dao;

import com.attaproject.controller.CountryController;
import com.attaproject.mapper.CountryMapper;
import com.attaproject.model.Country;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CountryDAO extends JdbcDaoSupport{

    public CountryDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Country> getCountries(){
        String sql = CountryMapper.BASE_SQL;
        CountryMapper countryMapper = new CountryMapper();
        assert this.getJdbcTemplate() != null;

        return this.getJdbcTemplate().query(sql, countryMapper, new Object[]{});
    }

    public Country getCountry(String name){

        String sql = CountryMapper.BASE_SQL + " where c.country_name like ?";
        CountryMapper mapper = new CountryMapper();
        assert this.getJdbcTemplate() != null;

        return this.getJdbcTemplate().queryForObject(sql, mapper, name);
    }

    public Country getCountry(Integer id){

        String sql = CountryMapper.BASE_SQL + " where c.id = ?";
        CountryMapper mapper = new CountryMapper();
        assert this.getJdbcTemplate() != null;

        return this.getJdbcTemplate().queryForObject(sql, mapper, id);
    }
}
