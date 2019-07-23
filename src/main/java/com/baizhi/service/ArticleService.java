package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {
    Map<String,Object> showAll(String guruId,Integer page,Integer rows);

    void delete(Article article);

    void add(Article article);

    void update(Article article);
}
