package com.example.demoWeb.dao;

import com.example.demoWeb.domain.UserBean;

public interface UserDao {

     UserBean login(UserBean loginUser);
     UserBean addUser(UserBean resUser);
     boolean nameReapt(String username);
     boolean nicknameReapt(String nickname);
     boolean storeImage(int u_id,String vurl);
     boolean changeNmae(int u_id,String nickname);
}
