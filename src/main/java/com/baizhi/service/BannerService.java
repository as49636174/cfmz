package com.baizhi.service;

        import com.baizhi.entity.Banner;

        import java.util.List;
        import java.util.Map;

public interface BannerService {
    //展示所有
    Map<String,Object> selectAllBanner(Integer page, Integer rows);
//    List<Banner> showAll();
    //添加轮播图
    String insertOne(Banner banner);
    //修改
    void update(Banner banner);
    //删除
    void removeOne(Banner banner);
    //差所有
    List<Banner> selectAll();
}
