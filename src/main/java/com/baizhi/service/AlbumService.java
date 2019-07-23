package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {

    Map<String,Object> showAll(Integer page, Integer rows);

    String add(Album album);

    void update(Album album);

    List<Album> selectAll();
}
