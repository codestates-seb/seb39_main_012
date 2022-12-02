package com.team012.server.room.repository;

import com.team012.server.common.utils.JDBC.JDBCRepository;
import com.team012.server.room.entity.Room;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoomJDBCRepository extends JDBCRepository<Room> {

    String sql = "INSERT INTO room" +
            "(room_size, price, room_count, posts_id) VALUES (?,?,?,?)";

    protected RoomJDBCRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void batchInsert(List<Room> lists) {


        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Room room = lists.get(i);
                ps.setString(1, room.getRoomSize());
                ps.setInt(2, room.getPrice());
                ps.setInt(3, room.getRoomCount());
                ps.setLong(4, room.getPostsId());
            }

            @Override
            public int getBatchSize() {
                return lists.size();
            }
        });
    }
}
