package com.example.demosecurity4.test.mapper;

import com.example.demosecurity4.test.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestMapper {

    Test selectById(@Param("id") Integer id);
}
