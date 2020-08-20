package com.gcx.util;

import java.util.Properties;



public class EmailBean {
	//Properties 类用于记录邮箱的一些属性
	private Properties props;
	//名义发送的Email账号
	private String useEmail;
	//名义发送的Email密码
	private String usePwd;
	//接收的Email账号
	private String toEmail;
	
	// 设置邮件标题
	private String title;

	// 设置邮件的内容体
	private String content;
	//标示
	private String token;
	//访问
	private String url;
	//昵称
	private String nickName;

	public void setProps() {
		Properties prop = new Properties();
		// 表示SMTP发送邮件，必须进行身份验证
		prop.put("mail.smtp.auth", "true");
		//此处填写SMTP服务器
		prop.put("mail.smtp.host", "smtp.ym.163.com");
		//端口号，QQ邮箱给出了两个端口，但是另一个不管用，这个587在这是管用的
		prop.put("mail.smtp.port", "25");
		// 此处填写你的账号
		prop.put("mail.user", this.useEmail);
		// QQ邮箱的授权码，去邮箱自己获取
		prop.put("mail.password", this.usePwd);
		this.props = prop;
	}
	
	public Properties getProps() {
		return props;
	}
	public void setProps(Properties props) {
		this.props = props;
	}
	public String getUseEmail() {
		return useEmail;
	}
	public void setUseEmail(String useEmail) {
		this.useEmail = useEmail;
	}
	public String getUsePwd() {
		return usePwd;
	}
	public void setUsePwd(String usePwd) {
		this.usePwd = usePwd;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	

}
