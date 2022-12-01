package com.team012.server.common.utils.JDBC;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


public abstract class JDBCRepository<T> {

    protected final JdbcTemplate jdbcTemplate;

    protected JDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public abstract void batchInsert(List<T> lists);

}
