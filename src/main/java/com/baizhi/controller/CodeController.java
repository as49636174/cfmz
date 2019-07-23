package com.baizhi.controller;

import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/code")
public class CodeController {
    @RequestMapping("/codeimage")
    public void code(HttpServletResponse response, HttpSession session) throws IOException {
        String code = SecurityCode.getSecurityCode();
        session.setAttribute("code",code);
        BufferedImage image = SecurityImage.createImage(code);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"png",outputStream);
        System.out.println(code);
    }
}
