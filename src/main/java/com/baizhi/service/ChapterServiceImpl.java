package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class ChapterServiceImpl implements ChapterService{

    @Autowired
    private ChapterDao chapterDao;


    @Override
    public Map<String, Object> showAll(Integer page, Integer rows,String album_id) {
        HashMap<String, Object> map = new HashMap<>();
        Chapter chapter = new Chapter();
        chapter.setAlbum_id(album_id);
        System.out.println(chapter);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Chapter> chapters = chapterDao.selectByRowBounds(chapter, rowBounds);
        int count = chapterDao.selectCount(chapter);
        map.put("page",page);
        map.put("rows",chapters);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    public String add(Chapter chapter,String album_id) {
        String id = UUID.randomUUID().toString();
        chapter.setId(id);
        chapter.setCreateDate(new Date());
        chapter.setAlbum_id(album_id);
        int i = chapterDao.insert(chapter);
        if(i==0){
            throw  new RuntimeException("添加失败");
        }
        return id;
    }

    @Override
    public void upadte(Chapter chapter) {
        int i = chapterDao.updateByPrimaryKeySelective(chapter);
        if(i==0){
            throw new RuntimeException("修改失败");
        }
    }


}
