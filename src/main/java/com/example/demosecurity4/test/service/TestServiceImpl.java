package com.example.demosecurity4.test.service;

import com.example.demosecurity4.test.entity.Test;
import com.example.demosecurity4.test.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Li Hongxing
 * @description: Test表service实现类
 * @date: Created in 2017/12/02 0:33
 * @modified:
 */
@Service
public class TestServiceImpl implements TestService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource private TestMapper testMapper;

    @Override
    public Test selectById(Integer id) {
        return testMapper.selectById(id);
    }
}
