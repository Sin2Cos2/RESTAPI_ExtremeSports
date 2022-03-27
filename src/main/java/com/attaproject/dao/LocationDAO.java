package com.attaproject.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class LocationDAO extends JdbcDaoSupport {

    public LocationDAO(DataSource dataSource){
        this.setDataSource(dataSource);
    }


}
