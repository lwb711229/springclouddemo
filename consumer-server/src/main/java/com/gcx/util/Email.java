package com.gcx.util;


/**
 * @author	yang
 * @version 创建时间：2018年1月6日 下午4:26:19
 * 类说明:
 */
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.gcx.util.EmailBean;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by Healist on 2017/1/29.
 */
public class Email {
	public static void main(String[] args) throws MessagingException {


       /* String nick="";

		try {
            nick=javax.mail.internet.MimeUtility.encodeText("凌风学长");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		EmailBean bean=new EmailBean();
		bean.setUseEmail("service@gcx365.com");


		bean.setUsePwd("gcx365.com123");
		bean.setToEmail("771474065@qq.com");
		bean.setContent("http://www.gcx365.com123");
		bean.setTitle("国诚信1");
		bean.setProps();
		*/

		/*// 创建Properties 类用于记录邮箱的一些属性
		Properties prop = new Properties();
		// 表示SMTP发送邮件，必须进行身份验证
		prop.put("mail.smtp.auth", "true");
		//此处填写SMTP服务器
		prop.put("mail.smtp.host", "smtp.ym.163.com");
		//端口号，QQ邮箱给出了两个端口，但是另一个不管用，这个587在这是管用的
		prop.put("mail.smtp.port", "25");
		// 此处填写你的账号
		prop.put("mail.user", "service@gcx365.com");
		// QQ邮箱的授权码，去邮箱自己获取
		prop.put("mail.password", bean。set);*/


//		bean.setProps(prop);

		/*Email email=new Email();
		email.sendEmail(bean);*/


/*		// 创建Properties 类用于记录邮箱的一些属性
		Properties props = new Properties();
		// 表示SMTP发送邮件，必须进行身份验证
		props.put("mail.smtp.auth", "true");
		//此处填写SMTP服务器
		props.put("mail.smtp.host", "smtp.ym.163.com");
		//端口号，QQ邮箱给出了两个端口，但是另一个不管用，这个587在这是管用的
		props.put("mail.smtp.port", "25");
		// 此处填写你的账号
		props.put("mail.user", "service@gcx365.com");
		// QQ邮箱的授权码，去邮箱自己获取
		props.put("mail.password", "gcx365.com123");

		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication("service@gcx365.com", "gcx365.com123");
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		InternetAddress form = new InternetAddress(
				props.getProperty("mail.user"));
		message.setFrom(form);

		// 设置收件人的邮箱
		InternetAddress to = new InternetAddress("2775863006@qq.com");
		message.setRecipient(MimeMessage.RecipientType.TO, to);

		// 设置邮件标题
		message.setSubject("测试邮件");

		// 设置邮件的内容体
		message.setContent("haahahahahahhahahahahahdifuaehnfiwe", "text/html;charset=UTF-8");

		// 发送邮件
		Transport.send(message);*/
		EmailBean emailb=new EmailBean();
		emailb.setUseEmail("service@gcx365.com");
		emailb.setUsePwd("gcx365.com123");
		String ss="";
		ss+="尊敬的用户:";
		ss+="\r";
		ss+="您好!";
		ss+="\r";
		ss+="刘国飞用户对您的信用状况做出了评价,";
		ss+="\r";
		ss+="请登录全国信用识别系统查看";
		ss+="<a href='http://cfcd.gcx365.com/cfcdweb/index.html' style='color:#468ded'>全国信用识别系统</a>查看";
		emailb.setContent(ss);
		emailb.setToEmail("771474065@qq.com");
		emailb.setTitle("评价");
		emailb.setNickName("国诚信");
		emailb.setProps();
		Email email=new Email();
		email.sendEmail(emailb);
	}

	public void sendEmail(EmailBean emailBean) throws MessagingException {
		//名义发送的Email账号
		final String useEmail = emailBean.getUseEmail();

		final String usePwd = emailBean.getUsePwd();
		// 构建授权信息，用于进行SMTP进行身份验证
		Properties props=emailBean.getProps();
		Authenticator authenticator = new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				/*String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");*/

				return new PasswordAuthentication(useEmail, usePwd);
			}
		};

		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		InternetAddress form = new InternetAddress(
				props.getProperty("mail.user"));
		message.setFrom(form);

		// 设置收件人的邮箱
		InternetAddress to = new InternetAddress(emailBean.getToEmail());
		message.setRecipient(MimeMessage.RecipientType.TO, to);
		String nick="";
		try {
			nick=javax.mail.internet.MimeUtility.encodeText(emailBean.getNickName());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 设置邮件标题
		message.setSubject(emailBean.getTitle());

		// 	message.setHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
//
		message.setFrom(new InternetAddress(nick+" <"+form+">"));
//
		// 设置邮件的内容体
		message.setContent(emailBean.getContent(), "text/html;charset=UTF-8");

		// 发送邮件
		Transport.send(message);


	}
}





