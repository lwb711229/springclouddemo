package com.gcx.support.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;

/**
 * redis操作类
 * @author CZH
 */
@Component
public class RedisFacade {

	private RedisFacade() {

	}

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Resource(name = "redisStringTemplate")
	private RedisTemplate<String, String> redisStringTemplate;

	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void setValue(String key, Object value, int seconds){
		//String str = null;
		if(value instanceof String){
			//redisStringTemplate.boundValueOps(key).set(JSON.toJSONString(value), minutes, TimeUnit.MINUTES);
			redisStringTemplate.boundValueOps(key).set(String.valueOf(value), seconds, TimeUnit.SECONDS);
			//非String类型的转json格式的String字符串
			//str = JSON.toJSONString(value);
		}else{
			//redisTemplate.boundValueOps(key).set(JSON.toJSONString(value), minutes, TimeUnit.MINUTES);
			redisTemplate.boundValueOps(key).set(JSON.toJSONString(value), seconds, TimeUnit.SECONDS);
		}
		/*if(0 == minutes){
			redisTemplate.boundValueOps(key).set(str);
		}else{
			//设置有时间限制的缓存
			redisTemplate.boundValueOps(key).set(str, minutes, TimeUnit.MINUTES);
		}*/
	}



	/**
	 * 获取缓存的值
	 * @param key
	 * @return
	 */
	public String getValue(String key){
		Object value = redisTemplate.boundValueOps(key).get();
		if(null != value){
			return value.toString();
		}
		return null;
	}

	/**
	 * 获取缓存的实体
	 * @param key
	 * @paramclazz
	 * @return
	 */
	public <T> T getValue(String key, T t){
		return (T) JSON.parseObject(getValue(key), t.getClass());
	}

	public void setexpire(String key,int seconds) {

		redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	//	public void setexpr(String key,String value,int seconds) {
	//	redisTemplate.boundValueOps(key).set(value, seconds, TimeUnit.SECONDS);
	}

	public void delete(String key) {

		redisTemplate.delete(key);
		//	public void setexpr(String key,String value,int seconds) {
		//	redisTemplate.boundValueOps(key).set(value, seconds, TimeUnit.SECONDS);
	}

}
