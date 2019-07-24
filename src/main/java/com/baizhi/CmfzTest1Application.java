package com.baizhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class CmfzTest1Application {

    public static void main(String[] args) {
        SpringApplication.run(CmfzTest1Application.class,args);
    }


    public Jedis getJedis(){
        return new Jedis("192.168.17.131",6379);
    }

}
