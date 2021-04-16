package com.kalus.demo.service;

import com.kalus.demo.model.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from user")
    @Results({
            @Result(property = "userName", column = "user_name", javaType = String.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<User> getUserList();
}
