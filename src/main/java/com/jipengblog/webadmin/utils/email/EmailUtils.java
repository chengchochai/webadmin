package com.jipengblog.webadmin.utils.email;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.jipengblog.webadmin.exception.UtilsException;
import com.jipengblog.webadmin.utils.PennUtils;
import com.jipengblog.webadmin.utils.email.constant.EmailTemplate;

public class EmailUtils extends PennUtils {

	private JavaMailSenderImpl mailSender;

	public EmailUtils() {
		mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.qq.com");
		mailSender.setUsername("1253543197@qq.com");
		mailSender.setPassword("jp109165147");
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.smtp.timeout", "25000");
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
		javaMailProperties.setProperty("mail.smtp.socketFactory.port", "465");
		javaMailProperties.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		javaMailProperties.setProperty("mail.smtp.socketFactory.fallback",
				"false");
		mailSender.setJavaMailProperties(javaMailProperties);

	}

	/**
	 * 发送模板邮件
	 * 
	 * @param subject
	 *            邮件主题
	 * @param to
	 *            接收人
	 * @param vmPath
	 *            模板的路径
	 * @param model
	 *            模板中需要替换的内容
	 * @throws UtilsException
	 */
	public void sendVelocityTpl(String subject, String to, String vmPath,
			HashMap<String, Object> model) throws UtilsException {
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(Velocity.RESOURCE_LOADER, "class");
		velocityEngine
				.setProperty("class.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"); // 设置类路径加载模板
		velocityEngine.setProperty(Velocity.INPUT_ENCODING, "utf-8");// 设置输入字符集
		velocityEngine.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");// 设置输出字符集
		velocityEngine.init();// 初始化模板引擎
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		VelocityContext velocityContext = new VelocityContext();
		StringWriter stringWriter = new StringWriter();
		try {
			Template template = velocityEngine.getTemplate(vmPath);// 加载模板，相对于classpath路径
			velocityContext.put("map", model);
			template.merge(velocityContext, stringWriter);
			mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
					mimeMessage, true, "UTF-8");
			mimeMessageHelper.setFrom(mailSender.getUsername());
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(stringWriter.toString(), true);
		} catch (Exception e) {
			throw new UtilsException(e);
		}
		logger.debug(stringWriter.toString());
		mailSender.send(mimeMessage);
	}

	/**
	 * 普通文本邮件
	 * 
	 * @param subject
	 *            主题
	 * @param to
	 *            接收人
	 * @param text
	 *            内容
	 */
	public void simpleSend(String subject, String to, String text) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom(mailSender.getUsername());
		smm.setTo(to);
		smm.setSubject(subject);
		smm.setText(text);
		mailSender.send(smm);
	}

	public static void main(String[] args) throws MessagingException {
		EmailUtils sender = new EmailUtils();
		String vmPath1 = "/com/jipengblog/webadmin/utils/mail/tpl/email_system.vm";
		String vmPath = EmailTemplate.RESPONSE_URL.getValue();

		System.out.println(vmPath1);
		System.out.println(vmPath);
		HashMap<String, Object> model = new HashMap<String, Object>();
		model.put("username", "jipeng");
		model.put("url", "this is url");
		try {
			sender.sendVelocityTpl("hello", "jipeng@dodonew.com", vmPath,
					model);
		} catch (UtilsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("mail send done");
	}
}
