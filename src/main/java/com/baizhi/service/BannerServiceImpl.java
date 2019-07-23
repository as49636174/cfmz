package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerDao bannerDao;

    //展示所有轮播图
    @Override
    public Map<String, Object> selectAllBanner(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Banner banner = new Banner();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Banner> banners = bannerDao.selectByRowBounds(banner, rowBounds);
        int count = bannerDao.selectCount(banner);
        map.put("page",page);
        map.put("rows",banners);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    //展示所有轮播图
   /* @Override
    public List<Banner> showAll() {
        List<Banner> banners = bannerDao.selectAll();
        return banners;
    }*/
    //添加
    @Override
    public String insertOne(Banner banner) {
        /*String id = UUID.randomUUID().toString();
        banner.setCreate_date(new Date());
        bannerDao.insert(banner);*/
        String id = UUID.randomUUID().toString();
        banner.setId(id);
        banner.setCreate_date(new Date());
        int i = bannerDao.insertSelective(banner);
        if(i == 0){
            throw new RuntimeException("添加轮播图失败");
        }
        return id;
    }

    //删除
    @Override
    public void removeOne(Banner banner) {
        System.out.println(banner);
        int i = bannerDao.delete(banner);
        if(i == 0){
            throw new RuntimeException("修改轮播图失败");
        }
        System.out.println("删除的数目："+i);
    }

    @Override
    public List<Banner> selectAll() {
        List<Banner> banners = bannerDao.selectAll();
        return banners;
    }

    //修改
    @Override
    public void update(Banner banner) {
        /*Banner banner1 = bannerDao.selectByPrimaryKey(id);
        System.out.println(banner1);
        System.out.println(banner);
        banner1.setName(banner.getName());
        if("".equals(banner.getCover())){

        }else {
            banner1.setCover(banner.getCover());
        }
        banner1.setDescription(banner.getDescription());
        banner1.setStatus(banner.getStatus());
        int i = bannerDao.updateByPrimaryKey(banner1);
        System.out.println("修改的条数："+i);*/
        int i = bannerDao.updateByPrimaryKeySelective(banner);
        System.out.println("i:"+i);
        if(i == 0){
            throw new RuntimeException("修改轮播图失败");
        }
    }





}
