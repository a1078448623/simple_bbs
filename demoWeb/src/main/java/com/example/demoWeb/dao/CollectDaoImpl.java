package com.example.demoWeb.dao;

import com.example.demoWeb.domain.RecommendBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CollectDaoImpl implements CollectDao{

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public boolean hasCollect(int u_id, int art_id) {

        try {
            String sql = "select id from collect where user_id=? and art_id=?";
            jdbcTemplate.queryForObject(sql, Integer.class, u_id, art_id);
            return true;
        }catch (DataAccessException e){
            return false;
        }

    }

    @Override
    public boolean addCollect(int u_id, int art_id) {

        String sql="insert into collect values (null,?,?)";
        int i = jdbcTemplate.update(sql, u_id, art_id);
        return i>0;
    }

    @Override
    public boolean removeCollect(int u_id, int art_id) {

        String sql="delete from collect where user_id=? and art_id=?";
        int i = jdbcTemplate.update(sql, u_id, art_id);
        return i>0;
    }

    @Override
    public List<RecommendBean> getCollectArts(int u_id) {
        try {
            String sql = "select article.art_id,nickname,t_name,title,likes,comments,cre_time " +
                    "from article,user,art_type,collect " +
                    "where user_id=? and article.art_id=collect.art_id and t_id=article.type_id and auth_id=user.u_id";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<RecommendBean>(RecommendBean.class), u_id);
        } catch (DataAccessException e){
            return null;
        }

    }
}
