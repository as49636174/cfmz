package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    Map<String,Object> showAll(Integer page,Integer rows,String album_id);

    String add(Chapter chapter,String album_id);


    void upadte(Chapter chapter);
}
