package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/selectAllAlbum")
    public Map<String, Object> selectAllAlbum(Integer page, Integer rows) {
        Map<String, Object> map = albumService.showAll(page, rows);
        return map;
    }


    @RequestMapping("/add")
    public Map<String, Object> add(Album album, String oper) {
        HashMap<String, Object> map = new HashMap<>();
        if ("add".equals(oper)) {
            try {
                String id = albumService.add(album);
                map.put("status", "ok");
                map.put("message", id);
            } catch (Exception e) {
                map.put("status", "error");
                map.put("message", e.getMessage());
            }
        }
        if ("edit".equals(oper)) {
            try {
                albumService.update(album);
                map.put("status", "ok");
            } catch (Exception e) {
                map.put("status", "error");
                map.put("message", e.getMessage());
            }
        }
        return map;
    }


    @RequestMapping("/upload")
    public void upload(String id, MultipartFile cover, HttpSession session) throws IOException {
        String img = session.getServletContext().getRealPath("img");
        cover.transferTo(new File(img, cover.getOriginalFilename()));

        Album album = new Album();
        album.setId(id);
        album.setCover(cover.getOriginalFilename());
        albumService.update(album);
    }

}