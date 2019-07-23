package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/selectChaptersBuAlbumId")
    public Map<String,Object> selectChaptersBuAlbumId(Integer page,Integer rows,String album_id){
        System.out.println(album_id);
        Map<String, Object> map = chapterService.showAll(page, rows,album_id);
        return map;
    }
    @RequestMapping("/add")
    public Map<String,Object> add(String oper, Chapter chapter,String album_id){
        Map<String, Object> map = new HashMap<>();
        if("add".equals(oper)){
            try {
                String s = chapterService.add(chapter, album_id);
                map.put("status","ok");
                map.put("message",s);
            }catch (Exception e){
                map.put("status","error");
                map.put("message",e.getMessage());
            }
        }
        if("edit".equals(oper)){
            try {
                chapterService.upadte(chapter);
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
    public void upload(String id, MultipartFile name , HttpSession session) throws IOException, EncoderException {
        String realPath = session.getServletContext().getRealPath("image");
        File file = new File(realPath, name.getOriginalFilename());
        name.transferTo(file);


        Chapter chapter = new Chapter();
        chapter.setId(id);
        chapter.setName(name.getOriginalFilename());
        //获取文件大小
        BigDecimal bigDecimal1 = new BigDecimal(name.getSize());
        BigDecimal bigDecimal2 = new BigDecimal(1024);
        BigDecimal bigDecimal = bigDecimal1.divide(bigDecimal2).divide(bigDecimal2).setScale(2, BigDecimal.ROUND_HALF_UP);
        chapter.setSize(bigDecimal+"MB");
        //获取时长
        Encoder encoder = new Encoder();
        long duration = encoder.getInfo(file).getDuration();
        chapter.setDuration(duration/1000/60+":"+duration/1000%60);
        chapterService.upadte(chapter);
    }



}








