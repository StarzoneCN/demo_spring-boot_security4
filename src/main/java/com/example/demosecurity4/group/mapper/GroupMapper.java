package com.example.demosecurity4.group.mapper;

import com.example.demosecurity4.group.entity.Group;
import com.example.demosecurity4.authority.entity.CustomGrantedAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupMapper {
    int insert(Group record);

    int insertSelective(Group record);

    List<CustomGrantedAuthority> selectGroupAuthoritiesByUserId(@Param("userId") Integer userId);
}