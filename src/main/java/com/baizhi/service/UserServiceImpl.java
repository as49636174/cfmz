package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.Modle;
import com.baizhi.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> showall(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        User user = new User();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<User> users = userDao.selectByRowBounds(user, rowBounds);
        int count = userDao.selectCount(user);
        map.put("page",page);
        map.put("rows",users);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    public List<User> show() {
        List<User> users = userDao.selectAll();
        return users;
    }

    @Override
    public Integer selectByman1(Integer time) {
        Integer integer = userDao.selectByman1(time);
        return integer;
    }

    @Override
    public List<Modle> selectBycity(String sex) {
        System.out.println(sex);
        List<Modle> list = userDao.selectBycity(sex);
        return list;
    }

    @Override
    public User selectGurn(String id) {
        User user = userDao.selectByPrimaryKey(id);
        return user;
    }


}
