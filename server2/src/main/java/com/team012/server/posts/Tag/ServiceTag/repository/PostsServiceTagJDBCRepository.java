package com.team012.server.posts.Tag.ServiceTag.repository;

import com.team012.server.common.utils.JDBC.JDBCRepository;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostsServiceTagJDBCRepository extends JDBCRepository<PostsServiceTag> {
    protected PostsServiceTagJDBCRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void batchInsert(List<PostsServiceTag> lists) {
        String sql = "INSERT INTO posts_service_tag (posts_id, service_tags_id) VALUES (?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                PostsServiceTag postsServiceTag = lists.get(i);
                ps.setString(1,String.valueOf(postsServiceTag.getPosts().getId()));
                ps.setString(2, String.valueOf(postsServiceTag.getServiceTag().getId()));
            }

            @Override
            public int getBatchSize() {
                return lists.size();
            }
        });
    }
}
