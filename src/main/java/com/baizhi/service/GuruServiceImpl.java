package com.baizhi.service;

import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Guru;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GuruServiceImpl implements GuruService{

    @Autowired
    private GuruDao guruDao;



    @Override
    public Map<String, Object> showAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Guru guru = new Guru();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Guru> gurus = guruDao.selectByRowBounds(guru, rowBounds);
        int count = guruDao.selectCount(guru);
        map.put("page", page);
        map.put("rows", gurus);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        map.put("records", count);
        return map;
    }
}
