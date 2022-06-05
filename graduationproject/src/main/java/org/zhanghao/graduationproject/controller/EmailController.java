package org.zhanghao.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.zhanghao.graduationproject.config.Redom;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 张昊
 * @date 2019/10/5 - 15:17
 **/
@Controller
public class EmailController {


    @Autowired
    JavaMailSenderImpl javaMailSender;
    @Autowired
    Redom redom;
    @Autowired
    private ThreadPoolTaskExecutor poolTaskExecutor;

    @PostMapping("/email")
    public String email(String email) throws Exception{
        CompletableFuture<Void> NewEmail = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                MimeMessage mimeMessage=javaMailSender.createMimeMessage();
                MimeMessageHelper helper;
                String pwd = new Redom().getPWD();
                System.out.println(pwd);
                try {
                    helper = new MimeMessageHelper(mimeMessage,true);
                    helper.setSubject("二级密码权限验证");
                    helper.setText("<h1 style='color:blue'><em><strong>尊敬的用户你好：</strong></h1></br><h1 style='color:red'></em>你已成功获取网站的二级密码<em>"+redom.getPWD()+"</em></h1>",true);
                    helper.setTo(email);
                    helper.setFrom("304711385@qq.com");
                    Thread.sleep(100);
                } catch (MessagingException | InterruptedException e) {
                    e.printStackTrace();
                }
                javaMailSender.send(mimeMessage);
            }
        },poolTaskExecutor);
        //等待完成
        //CompletableFuture.allOf(NewEmail).get();
//        Thread thread = new Thread(() -> {
//            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
//            MimeMessageHelper helper;
//            String pwd = new Redom().getPWD();
//            System.out.println(pwd);
//            try {
//                helper = new MimeMessageHelper(mimeMessage,true);
//                helper.setSubject("二级密码权限验证");
//                helper.setText("<h1 style='color:blue'><em><strong>尊敬的用户你好：</strong></h1></br><h1 style='color:red'></em>你已成功获取网站的二级密码<em>"+redom.getPWD()+"</em></h1>",true);
//                helper.setTo(email);
//                helper.setFrom("304711385@qq.com");
//                Thread.sleep(500);
//            } catch (MessagingException | InterruptedException e) {
//                e.printStackTrace();
//            }
//            javaMailSender.send(mimeMessage);
//        });
//        thread.start();
        return "redirect:/pwd";
    }
}
