package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//轮播图模块
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    //展示所有
    @RequestMapping("showall")
    public Map<String,Object> showall(Integer page,Integer rows){
        Map<String, Object> map = bannerService.selectAllBanner(page, rows);
        return map;
    }

 /*   @RequestMapping("showall")
    @ResponseBody
    public List<Banner> showAll(){
        List<Banner> banners = bannerService.showAll();
        return banners;
    }*/



    //添加轮播图
    @RequestMapping("/add")
    public Map<String,Object> add(String oper , Banner banner) {
        Map<String, Object> map = new HashMap<>();
        if("add".equals(oper)){
            String id = bannerService.insertOne(banner);
            System.out.println("轮播图Id"+id);
            map.put("status","ok");
            map.put("message", id);
//            map = add(banner);
        }
        if("del".equals(oper)){
//            map = del(banner);
            bannerService.removeOne(banner);
            map.put("status","ok");
        }
        if("edit".equals(oper)){
//            map = edit(banner);
            try {
                if("".equals(banner.getCover())){
                    banner.setCover(null);
                }
                bannerService.update(banner);
                map.put("status","ok");
            }catch (Exception e){
                map.put("status","error");
                map.put("message",e.getMessage());
            }
        }
        return map;
    }

/*
    public Map<String,Object> add(Banner banner){
        Map<String, Object> map = new HashMap<>();
        try {
            String id = bannerService.insertOne(banner);
            map.put("status",true);
            map.put("message",id);
        } catch (Exception e) {
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }

    public Map<String,Object> edit(Banner banner){
        Map<String, Object> map = new HashMap<>();
        try {
            System.out.println("banner:"+banner);
            System.out.println("".equals(banner.getCover()));
            if("".equals(banner.getCover())){
                banner.setCover(null);
            }
            bannerService.update(banner);
            map.put("status",true);
        } catch (Exception e) {
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }

    public Map<String,Object> del(Banner banner) {
        Map<String, Object> map = new HashMap<>();
        try {
            bannerService.removeOne(banner);
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
*/








    //文件上传
    @RequestMapping("/upload")
    public void upload(String id, MultipartFile cover , HttpSession session) throws IOException {
        System.out.println(cover.getOriginalFilename());
        String img = session.getServletContext().getRealPath("img");
        cover.transferTo(new File(img,cover.getOriginalFilename()));

        Banner banner = new Banner();
        banner.setId(id);
        banner.setCover(cover.getOriginalFilename());
        bannerService.update(banner);
    }

}
