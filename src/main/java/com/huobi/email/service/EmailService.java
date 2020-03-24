package com.huobi.email.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    //邮件的发送者
    @Value("${spring.mail.username}")
    private String from;
    //注入MailSender
    @Autowired
    private JavaMailSender javaMailSender;
    //发送邮件的模板引擎
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    public void sendMessageMail(Object params, String title, String templateName){
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);
            //发送给谁
            mimeMessageHelper.setTo(InternetAddress.parse("1599796855@qq.com"));
            //邮件标题
            mimeMessageHelper.setSubject("【" + title + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "】");
            Map<String,Object> model = new HashMap<>();
            model.put("params",params);
            try{
                Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
                try{
                    String text = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
                    mimeMessageHelper.setText(text,true);
                    javaMailSender.send(mimeMessage);
                }catch (TemplateException e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (MessagingException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
