package com.example.demoWeb.dao;

import com.example.demoWeb.domain.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserBean login(UserBean loginUser){
        try{
            String sql = "select * from user where username=? and password=?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class),
                    loginUser.getUsername(), loginUser.getPassword());
        }catch (DataAccessException e){
            return null;
        }

    }
    @Override
    public UserBean addUser(UserBean resUser){
        String sql="insert into USER values (null,?,?,?,default)";
        String sql2="select *from user where username=?";
        int i = jdbcTemplate.update(sql, resUser.getUsername(), resUser.getPassword(),resUser.getNickname());
        return jdbcTemplate.queryForObject(sql2,new BeanPropertyRowMapper<>(UserBean.class),resUser.getUsername());

    }

    @Override
    public boolean nameReapt(String username) {

        try {
            String sql = "select * from user where username=?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserBean.class), username) != null;
        }catch (DataAccessException e){
            return false;
        }
    }

    @Override
    public boolean nicknameReapt(String nickname) {

        try {
            String sql = "select * from user where nickname=?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserBean.class), nickname) != null;
        }catch (DataAccessException e){
            return false;
        }

    }

    @Override
    public boolean storeImage(int u_id,String vurl) {

        String sql="update user set himage=? where u_id=?";
        int i = jdbcTemplate.update(sql, vurl, u_id);
        return i>0;
    }

    @Override
    public boolean changeNmae(int u_id, String nickname) {

        String sql="update user set nickname=? where u_id=?";
        int i = jdbcTemplate.update(sql, nickname, u_id);
        return i>0;
    }
}
