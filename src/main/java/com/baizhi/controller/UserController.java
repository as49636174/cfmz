package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Modle;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.swing.border.TitledBorder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/showall")
    public Map<String, Object> showall(Integer page, Integer rows) {
        Map<String, Object> showall = userService.showall(page, rows);
        return showall;
    }


    @RequestMapping("/show")
    public void show(HttpServletResponse resp) {
        List<User> list = userService.show();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户详细信息表", "用户信息"),
                User.class, list);


        String fileName = "用户报表(" + new
                SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ").xls";
        //处理中文下载名乱码
        try {
            fileName = new
                    String(fileName.getBytes("gbk"), "iso-8859-1");
            //设置 response
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("content-disposition", "attachment;filename=" + fileName);
            workbook.write(resp.getOutputStream());
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/title")
    public Integer[] title(){
        Integer [] a=new Integer[6];
        a[0]=userService.selectByman1(30);
        a[1]=userService.selectByman1(60)-userService.selectByman1(30);
        a[2]=userService.selectByman1(90)-userService.selectByman1(60);;
        a[3]=userService.selectByman1(120)-userService.selectByman1(90);;
        a[4]=userService.selectByman1(150)-userService.selectByman1(120);;
        a[5]=userService.selectByman1(180)-userService.selectByman1(150);;
        return a;

    }

    @RequestMapping("/city")
    public Map<String,Object> city(){
        Map<String, Object> map = new HashMap<>();
        List list1 = userService.selectBycity("男");
        map.put("male",list1);

        List list2 = userService.selectBycity("女");
        map.put("female",list2);

        return map;
    }


}

