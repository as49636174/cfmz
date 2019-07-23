package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.User;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("first_page")
public class FirstPageController {
    //轮播图
    @Autowired
    private BannerService bannerService;
    //专辑
    @Autowired
    private AlbumService albumService;
    //文章
    @Autowired
    private ArticleService articleService;
    //用户
    @Autowired
    private UserService userService;
    public Map<String,Object> firstPage(String uid,String type,String sub_type){


        HashMap<String, Object> map = new HashMap<>();


        if("all".equals(type)){
            //查询所以轮播图
            List<Banner> banners = bannerService.selectAll();
            map.put("banner",banners);
            //全部专辑
            List<Album> albums = albumService.selectAll();
            map.put("album",albums);
            //文章
            User user = userService.selectGurn(uid);
            String username = user.getUsername();



        }
        if("wen".equals(type)){

            //全部专辑
            List<Album> albums = albumService.selectAll();
            map.put("album",albums);


        }
        if("si".equals(type)){


            if("ssyj".equals(sub_type)){

            }

        }




        return null;
    }




}
