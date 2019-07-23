package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumDao albumDao;
    @Override
    public Map<String, Object> showAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Album album = new Album();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Album> albums = albumDao.selectByRowBounds(album, rowBounds);
        int count = albumDao.selectCount(album);
        map.put("page",page);
        map.put("rows",albums);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    public String add(Album album) {
        String s = UUID.randomUUID().toString();
        album.setId(s);
        album.setCreateDate(new Date());
        int i = albumDao.insert(album);
        if(i==0){
            throw new RuntimeException("添加失败");
        }
        return s;
    }

    @Override
    public void update(Album album) {
        int i = albumDao.updateByPrimaryKeySelective(album);
        if(i==0){
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public List<Album> selectAll() {
        List<Album> albums = albumDao.selectAll();
        return albums;
    }


}
