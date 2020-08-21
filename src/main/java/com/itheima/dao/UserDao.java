package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

public interface UserDao {

   int save(User user);

   int delete(User user);

   int update(User user);

   User findByid( String id);

   List<User> findAll();
}
