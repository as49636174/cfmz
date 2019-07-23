package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;




    @RequestMapping("/showall")
    public Map<String,Object> showAll(String guru_id,Integer page,Integer rows){
        Map<String, Object> map = articleService.showAll(guru_id, page, rows);
        return map;
    }




    @RequestMapping("dele")
    public Map<String,Object> add(Article article,String oper,String gurn_id){
        System.out.println(article);
        System.out.println(oper);
        HashMap<String, Object> map = new HashMap<>();
        if("del".equals(oper)){
            try{
                articleService.delete(article);
                map.put("status","ok");
            }catch (Exception e){
                map.put("status","error");
                map.put("message",e.getMessage());
            }
        }
        if("add".equals(oper)){
            try {
                article.setCreate_date(new Date());
                articleService.add(article);
                map.put("status","ok");
            }catch (Exception e){
                map.put("status","error");
                map.put("message",e.getMessage());
            }
        }
        if("edit".equals(oper)){
            try {
                articleService.update(article);
                map.put("status","ok");
            }catch (Exception e){
                map.put("status","error");
                map.put("message",e.getMessage());
            }
        }
        return map;
    }


    //文件上传
    @RequestMapping("/upload")
    public Map<String,Object> upload(HttpSession session, MultipartFile articleImage, HttpServletRequest request)  {
        HashMap<String, Object> map = new HashMap<>();

        try {
            articleImage.transferTo(new File(request.getSession().getServletContext().getRealPath("image"),articleImage.getOriginalFilename()));
            map.put("error",0);
            map.put("url","http://localhost:8989/image/"+articleImage.getOriginalFilename());

        } catch (IOException e) {
            map.put("error",1);
        }
        return map;

    }

    @RequestMapping("browser")
    public Map<String,Object> browser(HttpServletRequest request){
        File file = new File(request.getSession().getServletContext().getRealPath("image"));
        File[] files = file.listFiles();

        Map<String, Object> map = new HashMap<>();
        //文件夹的网络路径
        map.put("current_url","http://localhost:8989/image/");
//        当前文件夹中的文件的数量
        map.put("total_count",files.length);
        ArrayList<Object> list = new ArrayList<>();
        for (File img : files) {
            HashMap<String, Object> imgObject = new HashMap<>();
            imgObject.put("is_dir",false);
            imgObject.put("has_file",false);
            imgObject.put("filesize",img.length());
            imgObject.put("is_photo",true);
            imgObject.put("filetype", FilenameUtils.getExtension(img.getName()));
            imgObject.put("filename", img.getName());
            imgObject.put("datetime", "2018-06-06 00:36:39");
            list.add(imgObject);


        }

        map.put("file_list",list);
        return map;
    }



}
