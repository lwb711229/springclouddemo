package com.gcx.util;

import javax.mail.MessagingException;



public class Emailtest {



	public static void main(String[] args) {
		EmailBean emailb=new EmailBean();
		emailb.setUseEmail("service@gcx365.com");
		emailb.setUsePwd("gcx365.com123");
		emailb.setToEmail("l_wb1@163.com");

		/*emailb.setContent("【国诚信】您好，已为您开通了国诚信账号。账号："+userName+"，密码："+password+"，请您记住您的账号和密码，并在登录后立即修改密码。操作方法：（1）登录国诚信APP客户端，iOS版本下载地址："+
		"https://itunes.apple.com/cn/app/guo-cheng-xin/id1088762495?mt=8，Android版本下载地址：http://www.gcx365.com/appdown/Honesty.apk"+
		"；(2)登录国合信用信息登记及应用平台：www.gcx365.com。");*/
		emailb.setContent("【国诚信】尊敬的用户您好，您在国合信用平台已成功注册，相关信息如下，账号："+"userName"+"，密码："+"password"+"，确保是您本人办理并在登陆后尽快修改密码，勿将信息告知其他人，谨防诈骗。");
		emailb.setTitle("用户注册");
		emailb.setNickName("国诚信");
		emailb.setProps();
		Email email=new Email();
		try {
			email.sendEmail(emailb);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

}
