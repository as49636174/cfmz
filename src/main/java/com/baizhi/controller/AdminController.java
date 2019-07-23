package com.baizhi.controller;


import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.baizhi.service.AdminService;
import com.baizhi.util.DayuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String username, String password, String code, HttpSession session, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            adminService.login(username,password,code);
//            DayuUtil.dayu();
            map.put("status",true);
        } catch (Exception e) {
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }



    @RequestMapping("/remove")
    public String removeSessino(HttpSession session){
        session.removeAttribute("login");
        return "redirect:/login.jsp";
    }
}
