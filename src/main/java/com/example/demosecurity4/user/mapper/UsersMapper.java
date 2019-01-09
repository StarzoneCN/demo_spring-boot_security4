package com.example.demosecurity4.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demosecurity4.user.entity.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author LiHongxing
 * @since 2018-05-17
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}
