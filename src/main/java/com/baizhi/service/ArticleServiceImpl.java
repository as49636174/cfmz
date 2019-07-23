package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;


import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleDao articleDao;


    @Override
    public Map<String, Object> showAll(String guru_id, Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Article article = new Article();
        article.setGuru_id(guru_id);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Article> articles = articleDao.selectByRowBounds(article, rowBounds);
        int count = articleDao.selectCount(article);
        map.put("page", page);
        map.put("rows", articles);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        map.put("records", count);
        return map;

    }

    @Override
    public void delete(Article article) {
        int i = articleDao.delete(article);
        if(i==0){
            throw new RuntimeException("删除失败");
        }

    }

    @Override
    public void add(Article article) {
        String s = UUID.randomUUID().toString();
        article.setId(s);
        int i = articleDao.insert(article);
        if(i==0){
            throw new RuntimeException("添加失败");
        }/*else {
            //第一个参数：REST Host       第二个参数：appks
            GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io","BC-8add987f434a4520af4b3e88e7b54dfb");
            // 第一个参数：channel的名称      第二个参数：发送的内容
            goEasy.publish("cmfz-article", article.getContent());
        }*/
    }

    @Override
    public void update(Article article) {
        int i = articleDao.updateByPrimaryKeySelective(article);
        if(i==0){
            throw new RuntimeException("修改失败");
        }
    }
}
