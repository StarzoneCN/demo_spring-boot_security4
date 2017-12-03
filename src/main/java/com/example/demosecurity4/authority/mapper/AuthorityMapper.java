package com.example.demosecurity4.authority.mapper;

import com.example.demosecurity4.authority.entity.CustomGrantedAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthorityMapper {
    int insert(CustomGrantedAuthority record);

    int insertSelective(CustomGrantedAuthority record);

    List<CustomGrantedAuthority> selectByUserId(@Param("userId") Integer userId);
}