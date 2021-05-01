package com.example.demoWeb.dao;

import com.example.demoWeb.domain.CommentsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public class NewCommtsDaoImpl implements NewCommtsDao{

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<CommentsBean> getCommts(int u_id) {

        try {
            String sql = "select id,to_art_id,nickname,comments.content,com_time,floor,reply_floor,reply_checked,to_commt_id,title,himage " +
                    "from comments,user,article " +
                    "where re_user_id=user.u_id and reply_checked=0 and to_art_id=article.art_id and to_user_id=? " +
                    "order by com_time desc ";
            List<CommentsBean> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CommentsBean>(CommentsBean.class), u_id);
            return list;
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public List<CommentsBean> replyedCommts(int u_id) {

        try {
            String sql = "select id,to_art_id,nickname,content,com_time,floor " +
                    "from comments,user " +
                    "where re_user_id=user.u_id and id in (select to_commt_id " +
                    "       from comments where reply_checked=0 and to_user_id=?       )  ";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CommentsBean.class), u_id);
        }catch (DataAccessException e){
            return null;
        }

    }

    @Override
    public boolean setRead(int commt_id, int u_id) {
        if(u_id==0){
            String sql="update comments set reply_checked=1 where id=?";
            int i = jdbcTemplate.update(sql, commt_id);
            return i>0;
        }
        else{
            String sql="update comments set reply_checked=1 where to_user_id=?";
            int i = jdbcTemplate.update(sql, u_id);
            return i>0;
        }

    }


}
