package com.gcx.control;


import com.gcx.dao.GCXUSERCORPPERFECTMapper;
import com.gcx.dao.GCXUSEREMPLOYEEPERFECTMapper;
import com.gcx.dao.GcxuserMapper;
import com.gcx.dao.SequenceMapper;

import com.gcx.model.GCXUSERCORPPERFECT;
import com.gcx.model.GCXUSEREMPLOYEEPERFECT;
import com.gcx.model.Gcxuser;
import com.gcx.support.redis.RedisFacade;
import com.gcx.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

@RestController
public class InformationController {
	private final Logger log = LoggerFactory.getLogger(getClass().getName());

	@Autowired
	GcxuserMapper gcxuserMapper;
	//企业
	@Autowired
	GCXUSERCORPPERFECTMapper userCorpPerfectDao;
	//个人

	@Autowired
	GCXUSEREMPLOYEEPERFECTMapper userEmployeePerfectDao;

	@Autowired
	SequenceMapper idMapper;

	@Autowired
	UserRedisUtils userRedisUtils;

	@Autowired
	RedisFacade redisFacade;

	@Value("${gcxId}")
	String gcxId;
	/*
	@Autowired
	GcxWsUserZcMapper gcxWsUserZcDao;
	
	@Autowired
	GcxWsUserSupplyMapper gcxWsUserSupplyDao;
	
*/  
	//GCX_USER   GCX_USER_CORP_PERFECT  GCX_USER_EMPLOYEE_PERFECT
	/*(HttpServletRequest request, HttpServletResponse response,String demandName,
            @RequestParam(defaultValue = "0") String type,
            @RequestParam(value="pagenow", defaultValue="1") int pagenow,
            @RequestParam(value="pagesize", defaultValue="10") int pagesize)*/
	//注册 ===Type 1企业。0个人。 sysNmae 系统名称 （哪个系统自己定义  默认信用识别）也可以不输
	//http://localhost:8001/usermanage/informationController/addRecord.do?Type=1&&TellNumber=17191078627&&Password=123456&&msgCode=556170
	/***
	 * @param Type 1企业。0个人
	 * @param TellNumber 手机号码
	 * @return Password
	 */

	@ApiOperation(value = "用户注册" ,  notes="用户注册")
     @ApiImplicitParams({
			@ApiImplicitParam(name = "Type", value = "企业1个人0", required = true, paramType = "query", dataType = "String"),
			 @ApiImplicitParam(name = "TellNumber", value = "注册手机号", required = true, paramType = "query", dataType = "String"),
			 @ApiImplicitParam(name = "Password", value = "密码", required = true, paramType = "query", dataType = "String"),
			// @ApiImplicitParam(name = "sysNmae", value = "系统说明", required = false, paramType = "说明", dataType = "String"),
			 @ApiImplicitParam(name = "msgCode", value = "验证码", required = true, paramType = "query", dataType = "String")
	 })
	@GetMapping("/informationController/addRecord.do")
	public MyResult<Object> addRecord(HttpServletRequest request, HttpServletResponse response,
									  String Type, String TellNumber, String Password, @RequestParam(required = true,value = "系统说明",defaultValue = "信用识别") String sysNmae, String msgCode){
		
		if("".equals(TellNumber) || TellNumber==null) return MyResult.error("用户名为空");
		if("".equals(Password) || Password==null) return MyResult.error("密码为空");
		String message = "注册失败";
		String where1 = "";

		String authId = "";
		//判断是否已生成 cookie唯一标示GcxuserMapper
		authId = CookieUtils.getCookieValue(request, gcxId);
		//如果没有 生成 并存入cookie
		if("".equals(authId) || authId==null){
			authId = UUIDUtils.createUuid();
			CookieUtils.setCookie(request, response, gcxId, authId);

		}

	/*	if("1".equals(Type) ){ where1 = "  GCX_USER_CORP_PERFECT WHERE CORP_ID='"+TellNumber+"'";}
		if("0".equals(Type) ){ where1 = "  GCX_USER_EMPLOYEE_PERFECT WHERE EMP_ID='"+TellNumber+"'";}
		if("8".equals(Type) ){ where1 = "  GCX_USER_GOV_PERFECT WHERE Z6='"+TellNumber+"'";}*/

		 if(gcxuserMapper.mapUser("  GCX_USER WHERE USER_NAME='"+TellNumber+"'") !=null) {
			return MyResult.error("此用户名已存在");
		}
		 MyResult<Object>  myresult =  subCheckCodes(request,response, msgCode);
		 if(myresult.getStatus() !=0){
			 return MyResult.error("验证码错误");
		 }
		 int i = 0;
		 BigDecimal id = idMapper.getNextval();
		Gcxuser user = new Gcxuser();
			Date date = new Date();
			user.setUserId(id);
			user.setUserName(TellNumber);
			user.setPassword(MD5.MD5ofStr(Password));
			user.setStatus((short) 0);//0：正常  1：未认证3：认证中
			user.setAccCate(Short.parseShort(Type)); //类型 ：0.个人 1.企业 8.政府 
			//user.setRealName(corp.getCorpName()); //用户真实姓名，企业名称，协会名称，园区名称等
			user.setRelatedId(new BigDecimal(TellNumber));
			user.setRegistTime(date);
			user.setSource(sysNmae);
			user.setZ10("1");
			user.setZ1("否");
			//user.setZ1("是");
			//user.setZ9(corp.getZ1());
			user.setUserGroup(System.nanoTime() + "");

			i =	gcxuserMapper.insertSelective(user);

			    
		
			//gcxWsUserZc.setPassword(MD5.MD5ofStr(Password));
			//gcxWsUserZc.setPassword(MD5.MD5ofStr(Password));
		if("1".equals(Type) ){   //企业

			GCXUSERCORPPERFECT record = new GCXUSERCORPPERFECT();
			record.setCorpId(id);
			i = userCorpPerfectDao.insertSelective(record);
			//try{i = userCorpPerfectDao.insertSelective(record); }catch(Exception e) {e.printStackTrace();};
		}else if("0".equals(Type)){  //个人 

			GCXUSEREMPLOYEEPERFECT record = new GCXUSEREMPLOYEEPERFECT();
			record.setEmpId(id);
			i = userEmployeePerfectDao.insertSelective(record);
			
		}
		
	/*	else if("8".equals(Type)){  // 政府

			UserGovPerfect record = new UserGovPerfect();
			record.setGovId(id.toString());
			record.setA10(sysNmae);
			i = userGovPerfectDao.insertSelective(record);
			//try{i = userGovPerfectDao.insertSelective(record); }catch(Exception e) {e.printStackTrace();};
		}*/
		if(i > 0){message="注册成功";}
			
			
		
/*
		User record1 = new User();
		record1.setUserName(TellNumber);
		//查询用户
		List<User> list = userDao.findByRecord(record1, -1, -1);
		user = list.get(0);
		Map userMap = new HashMap();
		userMap.put("userId", user.getUserId());
		userMap.put("userName", user.getUserName());
		userMap.put("accCate", user.getAccCate());
		userMap.put("z1", user.getZ1());
		userMap.put("status", user.getStatus());
		userMap.put("realName", user.getRealName());
		userMap.put("mainId", user.getMainId());
		userMap.put("source", user.getSource());
		userMap.put("z10", user.getZ10());
		

		//保存用户信息至redis 半小时
		//TODO
		String userJSON =	JSONObject.toJSONString(user,SerializerFeature.WriteMapNullValue);
		stringRedisTemplate.delete("user:"+authId);
		valueOps.set("user:"+authId, userJSON, 60*60*20, TimeUnit.SECONDS);
		//登录成功*/
		
		 //	return MyResult.ok(userMap);

		return MyResult.ok(null);
	}
	//==========================================================================验证码

  //发送验证码
  //	http://localhost:8001/usermanage/informationController/sendMessage.do?TellNumber=17191078627
	@GetMapping("/informationController/sendMessage.do")
	public MyResult<Object> sendMessage(HttpServletRequest request, HttpServletResponse response,String  TellNumber) {
		//用户唯一身份标示
		String authId = "";
		//判断是否已生成 cookie唯一标示
		authId = CookieUtils.getCookieValue(request, gcxId);
		//如果没有 生成 并存入cookie
		if("".equals(authId) || authId==null){
			authId = UUIDUtils.createUuid();
			CookieUtils.setCookie(request, response, gcxId, authId);
			//return MyResult.error("请重新登录");
		}
		//获取用户信息
	//	User record = userRedisUtils.getUser(request);
	//	if(record == null) return MyResult.error("请重新登录");
			
		//获取手机号
		String tel =TellNumber;
		//生成6位随机数
		String msgCheckCode = (int)((Math.random()*9+1)*100000) + "";
		//验证码存入redis  过期时间2分钟
		//valueOps.set("msgCheckCode:"+authId, msgCheckCode, 60*2, TimeUnit.SECONDS);
	   redisFacade.setValue("msgCheckCode:"+authId,msgCheckCode,60*2);
		//发送手机验证码
		// 装入SendSMSUtil模板中发送短信
		//if (SendSMSUtil.sendTemplateSMS(SendSMSUtil.SIMPLE_TEMPLATE, tel, new String[] { msgCheckCode },saleRemindService)) {
		if (SendMessage.sendTemplateSMS("243130", tel, new String[] {msgCheckCode})){
			return MyResult.ok("验证码已发送");
		} else {
			return MyResult.error("请重新发送");
		}
	
	}
	public MyResult<Object> subCheckCodes(HttpServletRequest request, HttpServletResponse response, String msgCode) {
		//用户唯一身份标示
		String authId = "";
		//判断是否已生成 cookie唯一标示
		authId = CookieUtils.getCookieValue(request, gcxId);
		//如果没有 生成 并存入cookie
		if("".equals(authId) || authId==null){
			authId = UUIDUtils.createUuid();
			CookieUtils.setCookie(request, response, gcxId, authId);
			return MyResult.error("错误");
		}
		//验证码判断
		String msgCodeRedis = userRedisUtils.getMsgCode(request);
		if(msgCodeRedis == null)
			return MyResult.error("手机验证码已过期");
		if(!msgCodeRedis.equals(msgCode))
			return MyResult.error("手机验证码错误");

		return MyResult.ok();
	}
	
	//用户密码登录
	//密码登陆 ==TellNumber 老账户也可以登录  audit 可以不传--审核者 发布者
	//http://localhost:8001/usermanage/informationController/userLogin.do?TellNumber=17191078627&&Password=123456&&audit=审核者
	@GetMapping("/informationController/userLogin.do")
	public MyResult<Object> userLogin(HttpServletRequest request, HttpServletResponse response,
									   String TellNumber,String Password, String msgCode,String audit) {
		Gcxuser record = new Gcxuser();
		if("".equals(TellNumber) || TellNumber==null) return MyResult.error("用户名为空");
		if("".equals(Password) || Password==null) return MyResult.error("密码为空");			
	//	if("".equals(checkCode) || checkCode==null) return MyResult.error("验证码为空");
	
		//密码MD5转换
		String passwordMD5 = MD5.MD5ofStr(Password);
		record.setPassword(passwordMD5);
		record.setUserName(TellNumber);
		
		 if(gcxuserMapper.mapUser("  GCX_USER WHERE USER_NAME='"+TellNumber+"'") == null) {
				return MyResult.error(" 抱歉，账号暂未注册");
			}
		
		//查询用户  审核是否通过
		// record.setStatus((short)1);
		 
		List<Gcxuser> list = gcxuserMapper.findByRecord(record);
		//用户名或密码错误
		if(list==null || list.size()==0)
			return MyResult.error("密码错误");
		if(list.size()>1)
			return MyResult.error("账号异常，请联系客服");
		//获取用户信息
		//前提：用户登录可用字段（例：用户名、手机、邮箱）不允许重复
		Gcxuser user = list.get(0);
		/*
		if (!"1".equalsIgnoreCase(user.getStatus().toString())){
			return MyResult.error("审核没有通过");
		}
		*/
		
		/*
		if(user.getStatus() == 1)
			return MyResult.error("账号审核中");
		if(user.getStatus() == 2)
			return MyResult.error("账号异常");
		if(user.getStatus() == 3)
			return MyResult.error("账号审核未通过");
		*/
		//==========================================================
		//用户唯一身份标示
		String authId = "";
		//判断是否已生成 cookie唯一标示
		authId = CookieUtils.getCookieValue(request, gcxId);
		//如果没有 生成 并存入cookie
		if("".equals(authId) || authId==null){
			authId = UUIDUtils.createUuid();
			CookieUtils.setCookie(request, response, gcxId, authId);
			
		}
		//保存用户信息至redis 半小时
		//TODO
		Map userMap = new HashMap();
		userMap.put("userId", user.getUserId());
		userMap.put("userName", user.getUserName());
		userMap.put("accCate", user.getAccCate());
		userMap.put("z1", user.getZ1());
		userMap.put("status", user.getStatus());
		userMap.put("realName", user.getRealName());
		userMap.put("mainId", user.getMainId());
		userMap.put("source", user.getSource());
		userMap.put("z10", user.getZ10());
		userMap.put("z9", user.getZ9()); //审核者 发布者
		
		Short accCate=user.getAccCate();
		if (accCate!=null){ 
		 if("1".equals(accCate.toString()) ){
			 
			/* Map<String, Object> mapuser =  userCorpPerfectDao.mapUser("  GCX_USER WHERE USER_NAME='"+TellNumber+"'");
			 
			 UserCorpPerfect userCorpPerfect = userCorpPerfectDao.selectByPrimaryKey((BigDecimal) mapuser.get("USER_ID")) ;
			 user.setInfo(userCorpPerfect);
			 String userCorpJSON =	JSONObject.toJSONString(userCorpPerfect,SerializerFeature.WriteMapNullValue);
			 valueOps.set("userCorp:"+authId, userCorpJSON, 60*60, TimeUnit.SECONDS);
			 userMap.put("info", userCorpPerfect);
			 */
		 }
		 else if ("0".equals(accCate.toString()) ){

			/* Map<String, Object> mapuser =  userCorpPerfectDao.mapUser( "  GCX_USER WHERE USER_NAME='"+TellNumber+"'");
			 
			 UserEmployeePerfect userEmployeePerfect = userEmployeePerfectDao.selectByPrimaryKey((BigDecimal) mapuser.get("USER_ID")) ;
			 
			 
			 user.setInfo(userEmployeePerfect);
			 String userEmpJSON =	JSONObject.toJSONString(userEmployeePerfect,SerializerFeature.WriteMapNullValue);
			 valueOps.set("userEmp:"+authId, userEmpJSON, 60*60, TimeUnit.SECONDS);
			 userMap.put("info", userEmployeePerfect);
			 */
		 }
		}
		String userJSON =	JSONObject.toJSONString(user,SerializerFeature.WriteMapNullValue);
		//valueOps.set("user:"+authId, userJSON, 60*30, TimeUnit.SECONDS);
		redisFacade.delete("user:"+authId);
		//valueOps.set("user:"+authId, userJSON, 60*60, TimeUnit.SECONDS);
		redisFacade.setValue("user:"+authId, userJSON,60*30);
	/*	try {
			user.setToken(RongUserApi.getToken(user.getUserName(),user.getUserName(),"").getToken());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String userJSON1 = redisFacade.getValue("user:"+authId);
		Gcxuser use1r = JSON.parseObject(userJSON1, Gcxuser.class);
		
		/*if(!"".equals(audit) && audit!=null){
			if (user.getZ9()!=null){
			if (!audit.equals(user.getZ9())){
				return MyResult.error("你没有权限登录");
			}}
		}*/
		
		return MyResult.ok(userMap);
	}
	//验证码登陆
	//http://localhost:8001/usermanage/informationController/yzmLogin.do?TellNumber=17191078627&&msgCode=123456
	@GetMapping("/informationController/yzmLogin.do")
	public MyResult<Object> modpassword(HttpServletRequest request, HttpServletResponse response,
										String TellNumber, String msgCode) {
		if("".equals(TellNumber) || TellNumber==null) return MyResult.error("用户名为空");
		if("".equals(msgCode) || msgCode==null) return MyResult.error("验证为空");			

		 if(gcxuserMapper.mapUser("  GCX_USER WHERE USER_NAME='"+TellNumber+"'") ==null) {
				return MyResult.error(" 抱歉，账号暂未注册");
			}
		Gcxuser record = new Gcxuser();
		//用户唯一身份标示
		String authId = "";
		//判断是否已生成 cookie唯一标示
		authId = CookieUtils.getCookieValue(request, gcxId);
		//如果没有 生成 并存入cookie
		if("".equals(authId) || authId==null){
			authId = UUIDUtils.createUuid();
			CookieUtils.setCookie(request, response, gcxId, authId);
			return MyResult.error("身份认证错误");
		}		
		
		
			 MyResult<Object>  myresult =  subCheckCodes(request,response, msgCode);
			 if(myresult.getStatus() !=0){
				 return MyResult.error("手机验证码错误");
			 }
			 
		 
		 
		//密码MD5转换
		
			record.setUserName(TellNumber);
			//查询用户
			List<Gcxuser> list = gcxuserMapper.findByRecord(record);
			//用户名或密码错误
			if(list==null || list.size()==0)
				return MyResult.error("用户名或密码错误");
			if(list.size()>1)
				return MyResult.error("账号异常，请联系客服");
			//获取用户信息
			//前提：用户登录可用字段（例：用户名、手机、邮箱）不允许重复
		Gcxuser user = list.get(0);
			
			/*if (!"1".equalsIgnoreCase(user.getStatus().toString())){
				return MyResult.error("审核没有通过");
			}
			*/
			/*if(user.getStatus() == 1)
				return MyResult.error("账号审核中");
			if(user.getStatus() == 2)
				return MyResult.error("账号异常");
			if(user.getStatus() == 3)
				return MyResult.error("账号审核未通过");*/
			//保存用户信息至redis 半小时
			//TODO
			//保存用户信息至redis 半小时
			//TODO
			Map userMap = new HashMap();
			userMap.put("userId", user.getUserId());
			userMap.put("userName", user.getUserName());
			userMap.put("accCate", user.getAccCate());
			userMap.put("z1", user.getZ1());
			userMap.put("status", user.getStatus());
			userMap.put("realName", user.getRealName());
			userMap.put("mainId", user.getMainId());
			userMap.put("source", user.getSource());
			userMap.put("z10", user.getZ10());
			
			Short accCate=user.getAccCate();
			if (accCate!=null){ 
			 if("1".equals(accCate.toString()) ){
			 	/*
				 Map<String, Object> mapuser =  userCorpPerfectDao.mapUser("  GCX_USER WHERE USER_NAME='"+TellNumber+"'");
				 
				 UserCorpPerfect userCorpPerfect = userCorpPerfectDao.selectByPrimaryKey((BigDecimal) mapuser.get("USER_ID")) ;
				 String userCorpJSON =	JSONObject.toJSONString(userCorpPerfect,SerializerFeature.WriteMapNullValue);
				 valueOps.set("userCorp:"+authId, userCorpJSON, 60*60, TimeUnit.SECONDS);
				 user.setInfo(userCorpPerfect);
				 userMap.put("info", userCorpPerfect);
				 */
			 }
			 else if ("0".equals(accCate.toString()) ){
			 	/*
				 Map<String, Object> mapuser =  userCorpPerfectDao.mapUser( "  GCX_USER WHERE USER_NAME='"+TellNumber+"'");
				 
				 UserEmployeePerfect userEmployeePerfect = userEmployeePerfectDao.selectByPrimaryKey((BigDecimal) mapuser.get("USER_ID")) ;
				// UserEmployeePerfect userEmployeePerfect = userEmployeePerfectDao.selectByPrimaryKey(new BigDecimal(TellNumber));
				 String userEmpJSON =	JSONObject.toJSONString(userEmployeePerfect,SerializerFeature.WriteMapNullValue);
				 valueOps.set("userEmp:"+authId, userEmpJSON, 60*60, TimeUnit.SECONDS);
				 user.setInfo(userEmployeePerfect);
				 userMap.put("info", userEmployeePerfect);
				 */
			 }
			}
			
			String userJSON =	JSONObject.toJSONString(user,SerializerFeature.WriteMapNullValue);
			//valueOps.set("user:"+authId, userJSON, 60*30, TimeUnit.SECONDS);
			//stringRedisTemplate.delete("user:"+authId);
		    redisFacade.delete("user:"+authId);
			//valueOps.set("user:"+authId, userJSON, 60*60, TimeUnit.SECONDS);
		    redisFacade.setValue("user:"+authId, userJSON,60*30);
			//登录成功
	
		/*	try {
				user.setToken(RongUserApi.getToken(user.getUserName(),user.getUserName(),"").getToken());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		 
		
			 
		return MyResult.ok(userMap);
	}

	//修改密码 ==旧密码 Password 新密码 newPassword
	//http://localhost:8001/usermanage/informationController/modpassword.do?TellNumber=17191078627&&Password=123456&&newPassword=
	//@Authorization(url="url 跳转")
	//@Wsxx
	@GetMapping("/informationController/modpassword.do")
		public MyResult<Object> modpassword(HttpServletRequest request, HttpServletResponse response,
											String TellNumber,String Password, String newPassword) {
			if("".equals(TellNumber) || TellNumber==null) return MyResult.error("用户名为空");
			if("".equals(Password) || Password==null) return MyResult.error("密码为空");			
		//	if("".equals(checkCode) || checkCode==null) return MyResult.error("验证码为空");
			//用户唯一身份标示
			String authId = "";
			//判断是否已生成 cookie唯一标示
			authId = CookieUtils.getCookieValue(request, gcxId);
			//如果没有 生成 并存入cookie
			if("".equals(authId) || authId==null){
				authId = UUIDUtils.createUuid();
				CookieUtils.setCookie(request, response, gcxId, authId);
				return MyResult.error("身份过期");
			}
		Gcxuser record = new Gcxuser();
			//密码MD5转换
			String passwordMD5 = MD5.MD5ofStr(Password);
			//查询用户 TellNumber,passwordMD5
			 Map<String, Object> user1 = gcxuserMapper.mapUser("  GCX_USER WHERE USER_NAME='"+TellNumber+"'" +" and PASSWORD='"+passwordMD5+"'");
			 if(user1==null ) return MyResult.error("用户名或密码错误");
			 record.setUserId((BigDecimal) user1.get("USER_ID"));
			 record.setPassword( MD5.MD5ofStr(newPassword));
			gcxuserMapper.updateByPrimaryKeySelective(record);
			return MyResult.ok("修改成功");
		}

    //忘记密码=== &msgCode 可选参数  如果不传先调用 //手机号 验证码 检测 接口
	//http://localhost:8001/usermanage/informationController/wjpassword.do?TellNumber=17191078627&&newPassword=123456&&msgCode=

		@RequestMapping("/informationController/wjpassword.do")

		public MyResult<Object> wjpassword(HttpServletRequest request, HttpServletResponse response,
										  String TellNumber,String newPassword, String msgCode) {
			if("".equals(TellNumber) || TellNumber==null) return MyResult.error("用户名为空");
            // if("".equals(msgCode) || msgCode==null) return MyResult.error("验证码为空");
            if("".equals(newPassword) || newPassword==null) return MyResult.error("密码为空");

			Gcxuser record = new Gcxuser();
			//用户唯一身份标示
			String authId = "";
			//判断是否已生成 cookie唯一标示
			authId = CookieUtils.getCookieValue(request, gcxId);
			//如果没有 生成 并存入cookie
			if("".equals(authId) || authId==null){
				authId = UUIDUtils.createUuid();
				CookieUtils.setCookie(request, response, gcxId, authId);
				
			}
			if("".equals(msgCode) || msgCode==null){
				if (userRedisUtils.getMsgCode(request)==null){
					 return MyResult.error("手机验证码过期");
				}
			}else{
				 MyResult<Object>  myresult =  subCheckCodes(request,response, msgCode);
				 if(myresult.getStatus() !=0){
					 return MyResult.error("手机验证码错误");
				 }
				 
			 }
			
			//查询用户
			Map<String, Object> mapuser =  gcxuserMapper.mapUser("  GCX_USER WHERE USER_NAME='"+TellNumber+"'");
			 if(mapuser==null ) return MyResult.error("账号未注册");
			 record.setUserId((BigDecimal) mapuser.get("USER_ID"));
			 record.setPassword( MD5.MD5ofStr(newPassword));
			gcxuserMapper.updateByPrimaryKeySelective(record);
			return MyResult.ok("修改成功");
		}

		//==========================================================================检测用户是否重复
		@GetMapping("/informationController/checkTel.do")
		public MyResult<Object> checkTel(HttpServletRequest request, HttpServletResponse response,String  TellNumber) {
			if("".equals(TellNumber) || TellNumber==null) return MyResult.error("用户名为空");
			 if(gcxuserMapper.mapUser("  GCX_USER WHERE USER_NAME='"+TellNumber+"'") !=null) {
					return MyResult.error("此用户已存在");
				}
			 return MyResult.ok("检验通过");
		}
		


		// 通过用户名或者userid 查找用户信息
				
				@RequestMapping("/informationController/userinfo.do")
				public MyResult<Object> userinfo(HttpServletRequest request, HttpServletResponse response,String  TellNumber) {
					if("".equals(TellNumber) || TellNumber==null) return MyResult.error("用户名为空");
					String where1 = "  GCX_USER WHERE ( USER_NAME='"+TellNumber+"')"+" or (USER_ID ='"+ TellNumber+"')" ;
					Map<String, Object> user11 = gcxuserMapper.mapUser(where1) ;
					
					if (user11==null) return MyResult.error("此用户不存在");
					
					Map userMap = new HashMap();
					userMap.put("userId", user11.get("USER_ID"));
					userMap.put("userName", user11.get("USER_NAME"));
					userMap.put("accCate", user11.get("ACC_CATE"));
					userMap.put("z1", user11.get("Z1"));
					userMap.put("status", user11.get("STATUS"));
					userMap.put("realName", user11.get("REAL_NAME"));
					userMap.put("source", user11.get("SOURCE"));
					userMap.put("z10", user11.get("Z10"));
					
					if ("1".equals(user11.get("ACC_CATE").toString())){
						// UserCorpPerfect userCorpPerfect = userCorpPerfectDao.selectByPrimaryKey((BigDecimal) user11.get("USER_ID")) ;
						// userMap.put("info", userCorpPerfect);
					}
					if ("0".equals(user11.get("ACC_CATE").toString())){
						// UserEmployeePerfect userEmployeePerfect = userEmployeePerfectDao.selectByPrimaryKey((BigDecimal) user11.get("USER_ID")) ;
						// userMap.put("info", userEmployeePerfect);
					}
					
					 return MyResult.ok(userMap);
				}

	//手机号 验证码 检测
	//http://localhost:8001/usermanage/informationController/looktelyzm.do?TellNumber=17191078627&&msgCode=
		@GetMapping("/informationController/looktelyzm.do")
		public MyResult<Object> looktelyzm(HttpServletRequest request, HttpServletResponse response, 
				String TellNumber, String msgCode) {
			if("".equals(TellNumber) || TellNumber==null) return MyResult.error("用户名为空");
			if("".equals(msgCode) || msgCode==null) return MyResult.error("验证为空");			

			 if(gcxuserMapper.mapUser("  GCX_USER WHERE USER_NAME='"+TellNumber+"'") == null) {
					return MyResult.error("无此用户");
				}
			//用户唯一身份标示
			String authId = "";
			//判断是否已生成 cookie唯一标示
			authId = CookieUtils.getCookieValue(request, gcxId);
			//如果没有 生成 并存入cookie
			if("".equals(authId) || authId==null){
				authId = UUIDUtils.createUuid();
				CookieUtils.setCookie(request, response, gcxId, authId);
				return MyResult.error("身份认证错误");
			}		
			
			 MyResult<Object>  myresult =  subCheckCodes(request,response, msgCode);
				
			 if(myresult.getStatus() !=0){
				 return MyResult.error("手机验证码错误");
			 }
			 return MyResult.ok("验证通过");
			}

	// 获取验证码
	@GetMapping("/informationController/getPicCheckCode.do")
	public void getPicCheckCode(HttpServletRequest request, HttpServletResponse response) {
		//用户唯一身份标示
		String authId = "";
		//判断是否已生成 cookie唯一标示
		authId = CookieUtils.getCookieValue(request, gcxId);
		//如果没有 生成 并存入cookie
		if("".equals(authId) || authId==null){
			authId = UUIDUtils.createUuid();
			CookieUtils.setCookie(request, response, gcxId, authId);
		}

		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/gif");

		// 生成的图片
		CheckCodeImageUtil imgUtil = new CheckCodeImageUtil();
		BufferedImage image = imgUtil.createImage();
		// request.getSession().setAttribute("SESSION_CHECKCODE", imgUtil.getRandString());
		redisFacade.setValue("imageCheckCode:"+authId, imgUtil.getRandString(),2*60);
		//redisFacade.setValue();etValue("imageCheckCode:"+imgUtil.getRandString());
		System.err.println(imgUtil.getRandString());
		OutputStream out = null;
		try {
			// Servlet输出流
			out = response.getOutputStream();
			// 将图片写入到输出流中去
			ImageIO.write(image, "JPG", out);
			// 强制刷新
			out.flush();
			// 关闭输出流
			out.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
		
		
}
