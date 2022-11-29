package com.team012.server.posts.Tag.HashTag.repository;

import com.team012.server.common.utils.JDBC.JDBCRepository;
import com.team012.server.posts.Tag.HashTag.entity.HashTag;
import com.team012.server.room.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class HashTagJDBCRepository extends JDBCRepository<HashTag> {


    public HashTagJDBCRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void batchInsert(List<HashTag> lists) {
        String sql = "INSERT INTO hash_tag " +
                "(tag) VALUES (?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                HashTag hashTag = lists.get(i);
                ps.setString(1, hashTag.getTag());
            }

            @Override
            public int getBatchSize() {
                return lists.size();
            }
        });
    }
}
