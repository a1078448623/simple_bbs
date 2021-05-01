package com.example.demoWeb.dao;

import com.example.demoWeb.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComplexDaoImpl implements ComplexDao{

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<RecommendBean> getRecoms() {
        String sql="select art_id,title,likes,comments,cre_time,nickname,t_name "+
                   "from" +
                         " article,user,art_type" +
                   " where" +
                         " type_id=t_id and auth_id=u_id " +
                   "order by likes desc ";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<RecommendBean>(RecommendBean.class));
    }

    @Override
    public List<CommentsBean> getCommts(int art_id) {

        String sql="select id,to_art_id,nickname,content,com_time,floor,reply_floor,himage " +
                    "from " +
                          "comments,user " +
                    "where " +
                          "re_user_id=user.u_id and to_art_id=? " +
                    "order by floor ";

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<CommentsBean>(CommentsBean.class),art_id);
    }

    @Override
    public ArtitleDetailBean getDetails(int art_id) {

        String sql="select art_id,t_name,title,content,nickname,cre_time,likes,comments,himage " +
                "from article,user,art_type " +
                "where auth_id=user.u_id and type_id=art_type.t_id and art_id=? ";


        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<ArtitleDetailBean>(ArtitleDetailBean.class),art_id);
    }

    @Override
    public boolean addCommts(AddCommentBean addCommentBean) {



        int i;
        System.out.println(addCommentBean.getReply_floor());
        if(addCommentBean.getReply_floor()==0) {

            String sql3="select auth_id from article where art_id=?";
            Integer to_user_id = jdbcTemplate.queryForObject(sql3, Integer.class, Integer.parseInt(addCommentBean.getTo_art_id()));



            String sql="insert into comments values (null,?,?,?,default,?,default,default,?,default)";
            i = jdbcTemplate.update(sql, Integer.parseInt(addCommentBean.getTo_art_id()),
                    Integer.parseInt(addCommentBean.getRe_auth_id()),
                    addCommentBean.getContent(), addCommentBean.getFloor(),to_user_id);
        }
        else {
            String sql4="select re_user_id,id from comments where to_art_id=? and floor=?";
            CommtArtuteBean artuteBean = jdbcTemplate.queryForObject(sql4, new BeanPropertyRowMapper<>(CommtArtuteBean.class),
                    Integer.parseInt(addCommentBean.getTo_art_id()), addCommentBean.getReply_floor());


            String sql2="insert into comments values (null,?,?,?,default,?,?,default,?,?)";
            i = jdbcTemplate.update(sql2, Integer.parseInt(addCommentBean.getTo_art_id()),
                    Integer.parseInt(addCommentBean.getRe_auth_id()),
                    addCommentBean.getContent(), addCommentBean.getFloor(), addCommentBean.getReply_floor(),artuteBean.getRe_user_id(),artuteBean.getId());
        }
        return i>0;
    }

    @Override
    public int getNums(int art_id) {

        try {
            String sql = "select count(*) from comments where to_art_id=?";
            return jdbcTemplate.queryForObject(sql, Integer.class, art_id);
        }catch (NullPointerException e){
            return 0;
        }
    }

    @Override
    public boolean updateCommts(int art_id) {

        String sql="update article set comments=comments+1 where art_id=?";
        int i = jdbcTemplate.update(sql, art_id);
        return i>0;
    }

    @Override
    public boolean updateCollects(int art_id ,String dowhat) {
        String sql1="update article set likes=likes+1 where art_id=?";
        String sql2="update article set likes=likes-1 where art_id=?";
        int i=0;
        if("add".equals(dowhat))
            i = jdbcTemplate.update(sql1, art_id);
        else if("sub".equals(dowhat)){
            i = jdbcTemplate.update(sql2, art_id);
        }
        return i>0;
    }

    @Override
    public List<RecommendBean> serch(String content, int type_id) {

        try {
            if (type_id == 0) {
                String sql1 = "select art_id,title,likes,comments,cre_time,nickname,t_name " +
                        "from" +
                        " article,user,art_type" +
                        " where" +
                        " type_id=t_id and auth_id=u_id and locate(?,title)>0 " +
                        "order by cre_time desc ";
                return jdbcTemplate.query(sql1, new BeanPropertyRowMapper<RecommendBean>(RecommendBean.class), content);
            } else {
                String sql2 = "select art_id,title,likes,comments,cre_time,nickname,t_name " +
                        "from" +
                        " article,user,art_type" +
                        " where" +
                        " type_id=t_id and auth_id=u_id and locate(?,title)>0 and type_id=? " +
                        "order by cre_time desc ";
                return jdbcTemplate.query(sql2, new BeanPropertyRowMapper<RecommendBean>(RecommendBean.class), content, type_id);
            }
        }catch (DataAccessException e){
            return null;
        }
    }


}
