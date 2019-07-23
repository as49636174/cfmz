package com.baizhi.service;

import com.baizhi.entity.Modle;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String,Object> showall(Integer page,Integer rows);

    List<User> show();

    Integer selectByman1(Integer time);

    List<Modle> selectBycity(String sex);

    User selectGurn(String id);
}
