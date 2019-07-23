package com.baizhi.dao;

import com.baizhi.entity.Modle;
import com.baizhi.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User> {
    Integer selectByman1(Integer time);

    List<Modle> selectBycity(String sex);

}
