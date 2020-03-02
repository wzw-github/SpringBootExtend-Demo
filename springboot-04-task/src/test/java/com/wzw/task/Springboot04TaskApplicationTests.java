package com.wzw.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot04TaskApplicationTests {

	@Autowired
	JavaMailSenderImpl javaMailSender;

	@Test
	void contextLoads() {
		SimpleMailMessage message = new SimpleMailMessage();
		//邮件设置
		message.setSubject("通知-今晚开会");
		message.setText("今晚7:30开会");
		message.setTo("w452339689@163.com");
		message.setFrom("452339689@qq.com");
		javaMailSender.send(message);
	}

	@Test
	void test02() throws MessagingException {
		//创建一个复杂的消息邮件
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		//邮件设置
		helper.setSubject("通知-今晚开会");
		helper.setText("<b style='color:red'>今晚7:30开会</b>",true);
		helper.setTo("w452339689@163.com");
		helper.setFrom("452339689@qq.com");
		//上传文件
		helper.addAttachment("1.jpg",new File("C:\\Users\\Administrator\\Desktop\\Snipaste_2020-02-08_10-57-50.png"));
		helper.addAttachment("2.jpg",new File("C:\\Users\\Administrator\\Desktop\\Snipaste_2020-02-10_15-24-55.png"));

		javaMailSender.send(mimeMessage);
	}

}
