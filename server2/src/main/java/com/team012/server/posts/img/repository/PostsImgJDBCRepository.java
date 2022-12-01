package com.team012.server.posts.img.repository;


import com.team012.server.common.utils.JDBC.JDBCRepository;
import com.team012.server.posts.img.entity.PostsImg;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostsImgJDBCRepository extends JDBCRepository<PostsImg> {


    public PostsImgJDBCRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void batchInsert(List<PostsImg> lists) {
        String sql = "INSERT INTO posts_img " +
                "(file_name, img_url, posts_id) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                PostsImg postsImg = lists.get(i);
//                ps.setLong(1, postsImg.getId());
                ps.setString(1, postsImg.getFileName());
                ps.setString(2, postsImg.getImgUrl());
                ps.setLong(3, postsImg.getPosts().getId());

            }

            @Override
            public int getBatchSize() {
                return 5;
            }
        });
    }
}
