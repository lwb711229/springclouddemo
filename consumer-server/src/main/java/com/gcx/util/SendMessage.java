package com.gcx.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Component;
import com.cloopen.rest.sdk.CCPRestSmsSDK;

//发送验证码工具类
/**
 * 
 * @author 刘鲲
 * @description 发送短信的工具类
 * @createDate 2015-05-25
 */
@Component
public class SendMessage {
	//服务器地址
	private static final String SERVER_IP="sandboxapp.cloopen.com";
	//服务器端口   		
	private static final String SERVER_PORT="8883";
	//主帐号
	private static final String ACOUNT_SID="aaf98f894d328b13014d6672ee9b2576";
	//主帐号令牌
	private static final String AUTH_TOKEN="b58f2ec511fd4ea3949902ab98cb5c9a";
	//应用ID  
	private static final String APP_ID="aaf98f894d7439d8014d74f48dbb00b4";	
	
//信用查询短信模板begin...
	//发送信用查询的模板ID
	public  static final String QUERY_CREDIT_TEMPLATE="20253";
	//发送信用查询的模板ID
	public  static final String SIMPLE_TEMPLATE="243130";
//信用查询短信模板end.	

	/**
	 * 发送短信到目标手机
	 * 
	 * @param templateName 发送短信模板，例如 QUERY_CREDIT_TEMPLATE是发送查询用户信用的短信模板
	 * @param recievePhone 接收短信的电话号码
	 * @param params 对应短信模板的参数，具体详见短信模板说明<code>http://docs.yuntongxun.com/index.php/模板短信</code>
	 * @return true代表短信发送成功，false代表短信发送失败
	 */
	
	public static boolean sendTemplateSMS(String templateName,String recievePhone,String[] params ){
		 if(templateName == "243130"){
				String yzm = params[0];
				String content = "【国诚信】您的验证码为："+yzm+"。此验证码用于国诚信账号注册，请勿转发或告知他人。若不是您本人操作，请忽略本条短信。";
				Date sendTime = new Date();
				UUID uuid = UUID.randomUUID(); 
				String contractNo = uuid.toString();
			}
		
		boolean flag=false;
		HashMap<String, Object> result = null;

		//初始化SDK
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		
		//******************************注释*********************************************
		//*初始化服务器地址和端口                                                       *
		//*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		//*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
		//*******************************************************************************
		
		restAPI.init(SERVER_IP, SERVER_PORT);
		
		//******************************注释*********************************************
		//*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
		//*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
		//*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
		//*******************************************************************************
				
		restAPI.setAccount(ACOUNT_SID, AUTH_TOKEN);
		
		
		//******************************注释*********************************************
		//*初始化应用ID                                                                 *
		//*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
		//*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
		//*******************************************************************************				
		restAPI.setAppId(APP_ID);
		
		
		//******************************注释****************************************************************
		//*调用发送模板短信的接口发送短信   
		//*参数顺序说明：                                                                                  *
		//*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
		//*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
		//*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
		//*第三个参数是要替换的内容数组。
		//**************************************举例说明***********************************************************************
		//*假设使用   查询信用信息模板  QUERY_CREDIT_TEMPLATE  ，则参数如下*
		//*[国诚信]提示：申请人：{1}，电话：{2}，正在申请查询{3}的信用信息，该查询请求的验证码为：{4}。
		//*验证码将在{5}天后无效。
		
		result = restAPI.sendTemplateSMS(recievePhone,templateName,params);
		
		System.out.println("SMS sended,the result is:" + result);
		
		if("000000".equals(result.get("statusCode"))){
			
			
			flag=true;
			//正常返回输出data包体信息（map）
			@SuppressWarnings("unchecked")
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
			
			
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
		
		
		return flag;
	}
}
