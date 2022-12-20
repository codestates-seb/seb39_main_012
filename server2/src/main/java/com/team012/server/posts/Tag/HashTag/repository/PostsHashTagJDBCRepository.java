package com.team012.server.posts.Tag.HashTag.repository;

import com.team012.server.common.utils.JDBC.JDBCRepository;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostsHashTagJDBCRepository extends JDBCRepository<PostsHashTags> {


    public PostsHashTagJDBCRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void batchInsert(List<PostsHashTags> lists) {
        String sql = "INSERT INTO posts_hash_tags (posts_id, hash_tags_id) VALUE (?, ?) ";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                PostsHashTags postsHashTags = lists.get(i);
                ps.setString(1, String.valueOf(postsHashTags.getPosts().getId()));
                ps.setString(2, String.valueOf(postsHashTags.getHashTag().getId()));
            }

            @Override
            public int getBatchSize() {
                return lists.size();
            }
        });
    }
}
