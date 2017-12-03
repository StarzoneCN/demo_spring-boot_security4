package com.example.demosecurity4.user.mapper;

import com.example.demosecurity4.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    /**
     * username、email、mobile均可作为用户唯一标识
     * @param username
     * @return
     */
    User selectByUsername(@Param("username") String username);

    int insert(User record);

    int insertSelective(User record);
}
