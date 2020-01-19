package com.crk.hs;

import com.crk.hs.mail.MailService;
import com.crk.hs.mail.MailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@SpringBootTest
class HsApplicationTests {
    @Resource
    private com.crk.hs.mail.MailService MailService;

    @Test
    void contextLoads() {
    }
    @Test
    public void test2() {
        String to = "1527559899@qq.com";
        String subject = "今晚要加班，不用等我了";
        String rscId = "img110";
        String content = "<html><body><img width='250px' src=\'cid:" + rscId + "\'></body></html>";
        // 此处为linux系统路径
        String imgPath = "E:\\bishe\\cqrwkj\\lmz_meet\\meet\\meet\\src\\main\\resources\\static\\dist\\img\\avatar\\avatar-1.jpeg";
        try {
            MailService.sendInlineResourceMail(to, subject, content, imgPath, rscId);
            System.out.println("邮件发送成功了");
        } catch (MessagingException e) {
            System.out.println("邮件发送失败了");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
