package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private HttpSession session;

    @Override
    public void login(String username,String password,String code) {
        String s = (String) session.getAttribute("code");
        if(s.equals(code)){
            Example example = new Example(Admin.class);
            example.createCriteria().andEqualTo("username",username);
            Admin login = adminDao.selectOneByExample(example);
            if(login!=null){
                if(login.getPassword().equals(password)){
                    session.setAttribute("login",login);
                }else {
                    throw new RuntimeException("密码错误");
                }
            }else {
                throw new RuntimeException("帐号错误");
            }
        }else {
            throw new RuntimeException("验证码错误");
        }

    }
}
