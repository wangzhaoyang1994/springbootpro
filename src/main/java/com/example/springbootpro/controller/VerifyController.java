package com.example.springbootpro.controller;

import com.example.springbootpro.utils.VerifyCodeUtils;
import com.example.springbootpro.utils.sessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/verify")
public class VerifyController {
    @Autowired
    private  HttpServletResponse response;
    @GetMapping("/getVerify")
    public void getVerify() throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        int w=132;
        int h=39;
        String verifyCode=VerifyCodeUtils.generateVerifyCode(4);
        String key="yzm";
        sessionUtils.set(request,key,verifyCode);
        VerifyCodeUtils.outputImage(w,h,response.getOutputStream(),verifyCode);
    }
}
