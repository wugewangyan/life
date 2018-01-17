package com.napoleon.life.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.napoleon.life.framework.redis.RedisServer;

@Service
public class RedisTemplate {
	
	@Autowired
	private RedisServer redisServer;

	public interface Callback<T> {
		public T doForCache();
	}
	
	public <T> T query(String key, Integer expired, TypeReference<T> reference, Callback<T> callback){
		String value = this.redisServer.get(key, null);
		if(StringUtils.isEmpty(value)){
			synchronized (this) {
				value = this.redisServer.get(key, null);
				if(StringUtils.isEmpty(value)){
					T result = callback.doForCache();
					if(result != null){
						value = JSONObject.toJSONString(result);
						redisServer.set(key, value, expired);
					}
					
					return result;
				}else{
					return JSONObject.parseObject(value, reference);
				}
			}
		}else{
			return JSONObject.parseObject(value, reference);
		}
	}

}
