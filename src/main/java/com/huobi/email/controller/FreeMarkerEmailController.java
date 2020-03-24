package com.huobi.email.controller;


import com.huobi.email.diamond.Message;
import com.huobi.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class FreeMarkerEmailController {


    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/sendMessage")
    public boolean sendMailMessage() {
        Message message = new Message();

        message.setMessageCode("MissingParameter");
        message.setMessageStatus("Failed");
        message.setCause("缺少参数,请确认");
        emailService.sendMessageMail(message, "测试消息通知", "message.ftl");
        return true;
    }
}
