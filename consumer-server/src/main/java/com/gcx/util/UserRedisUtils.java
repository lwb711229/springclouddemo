package com.gcx.util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.gcx.support.redis.RedisFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Repository;


/**
 * @author	yang 
 * @version 创建时间：2017年4月18日 下午4:40:14 
 * 类说明:	
 */
@Component
@Repository
public class UserRedisUtils {

	@Value("${gcxId}")
	String gcxId;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	RedisFacade redisFacade;

	//@Resource(name = "stringRedisTemplate")
	//private ValueOperations<String, String> valueOps;

	/*//返回null 表示已过期或者未登录
	public User getUser(HttpServletRequest request) {
		String authId = CookieUtils.getCookieValue(request, gcxId);
		String userJSON = valueOps.get("user:"+authId);
		if("".equals(userJSON) || userJSON==null){
			return null;
		}
		User user = JSON.parseObject(userJSON, User.class);

		return user;
	}*/
	//返回null 表示已过期或者未登录
	public boolean removeUser(HttpServletRequest request) {
		String authId = CookieUtils.getCookieValue(request, gcxId);
		String userJSON = redisFacade.getValue("user:"+authId);
		if("".equals(userJSON) || userJSON==null){
			return true;
		}
		stringRedisTemplate.delete("user:"+authId);
		return true;
	}

	//返回null 表示已过期   图片验证码
	public String getImageCode(HttpServletRequest request) {
		String authId = CookieUtils.getCookieValue(request, gcxId);
		String imageCode = redisFacade.getValue("imageCheckCode:"+authId);
		if("".equals(imageCode) || imageCode==null){
			return null;
		}
		return imageCode;
	}


	//返回null 表示已过期  手机验证码
	public String getMsgCode(HttpServletRequest request) {
		String authId = CookieUtils.getCookieValue(request, gcxId);
		//String msgCode = valueOps.get("msgCheckCode:"+authId);
		String msgCode = redisFacade.getValue("msgCheckCode:"+authId);

		if("".equals(msgCode) || msgCode==null){
			return null;
		}
		return msgCode;
	}

}
