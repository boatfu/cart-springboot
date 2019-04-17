package com.boat.mapper;

import com.boat.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {
    void addUser(User user);
    void deleteUser(int id );
    void updateUser(User user);
    User checkUser(String email);

    List<User> list();

}
