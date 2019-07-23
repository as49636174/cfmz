package com.baizhi.controller;

import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/guru")
public class GuruController {

    @Autowired
    private GuruService guruService;

    @RequestMapping("/showall")
    public Map<String,Object> showAll(Integer page,Integer rows){
        Map<String, Object> map = guruService.showAll(page, rows);
        return map;
    }
}
