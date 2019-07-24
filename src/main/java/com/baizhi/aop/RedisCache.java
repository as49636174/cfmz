package com.baizhi.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.util.Set;

@Configuration
@Aspect
public class RedisCache {


    @Autowired
    private Jedis jedis;

    @Around("execution(* com.baizhi.service.*select*(..))")
    public Object cache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //1.获取将要执行的方法
        //2.判断将要执行的方法是否含有注解
        //3.如果含有注解，先去缓存中拿，如果缓存中有直接返回，如果没有查询数据库，并添加到缓存中
        //4.如果没有注解，直接方法放行
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();//获取执行的方法
        Method method = signature.getMethod();
        boolean b = method.isAnnotationPresent(com.baizhi.annotaton.RedisCache.class);
        if(b){
            //当前执行的方法上还有注解
            //判断redis中是否有某个Key(某个方法)
            //如果含就直接拿
            //如果不含就查数据库，然后放到缓存中一份

            //获取key
            StringBuilder stringBuilder = new StringBuilder();
            //获取类的全限定名
            String className = proceedingJoinPoint.getTarget().getClass().getName();
            stringBuilder.append(className).append(".");
            //获取方法名
            String methodName = method.getName();
            stringBuilder.append(methodName).append(":");
            //获取实参
            Object[] args = proceedingJoinPoint.getArgs();
            //获取要执行方法中的实参
            for (int i = 0; i < args.length; i++) {
                stringBuilder.append(args[i].toString());
                if(i==args.length-1){
                    break;
                }
                stringBuilder.append(",");
            }
            String key = stringBuilder.toString();
            if(jedis.exists(key)){
                //redis中含有key
                String s = jedis.get(key);
                //把S转成json格式
                Object result = JSON.parse(s);
                return result;
            }else {
                //redis中不含有这个key
                Object result = proceedingJoinPoint.proceed();
                //放入redis中
                jedis.set(key, JSON.toJSONString(result));
                return result;
            }
        }else {
            //当前要执行的方法不含注解
            Object result = proceedingJoinPoint.proceed();
            return result;
        }
    }

    public void after(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getName();


        //获取redis缓存中的所以key
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            if(key.startsWith(className)){
                jedis.del(key);
            }
        }
    }
}
