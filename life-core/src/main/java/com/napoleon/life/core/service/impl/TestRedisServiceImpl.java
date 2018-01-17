package com.napoleon.life.core.service.impl;  
   
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.napoleon.life.core.entity.LifeWaist;
import com.napoleon.life.core.service.LifeWaistService;
import com.napoleon.life.core.service.TestRedisService;
import com.napoleon.life.core.service.impl.RedisTemplate.Callback;
import com.napoleon.life.framework.redis.RedisServer;
 
 @Service
 public class TestRedisServiceImpl implements TestRedisService{

	@Autowired
	private RedisServer redisServer;
	
	@Autowired
	private LifeWaistService waistService;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * 之前的同步访问缓存
	 */
	public synchronized LifeWaist queryForSyn(Long waistId, Integer time){
		String key = waistId + "";
		String value = redisServer.get(key, null);
		if(StringUtils.isEmpty(value)){
			LifeWaist lifeWaist = this.waistService.findByWaistId(waistId);
			if(lifeWaist != null){
				value = JSONObject.toJSONString(lifeWaist);
				redisServer.set(key, value, time);
			}
			
			return lifeWaist;
		}else{
			return JSONObject.parseObject(value, LifeWaist.class);
		}
	}
	
	/**
	 * 改造之后并发的访问缓存
	 */
	public LifeWaist queryForConcurrent(Long waistId, Integer time){
		String key = waistId + "";
		String value = redisServer.get(key, null);
		if(StringUtils.isEmpty(value)){
			synchronized(this){
				value = redisServer.get(key, null);
				if(StringUtils.isEmpty(value)){
					LifeWaist lifeWaist = this.waistService.findByWaistId(waistId);
					if(lifeWaist != null){
						value = JSONObject.toJSONString(lifeWaist);
						redisServer.set(key, value, time);
					}
					
					return lifeWaist;
				}else{
					return JSONObject.parseObject(value, LifeWaist.class);
				}
			}
		}else{
			return JSONObject.parseObject(value, LifeWaist.class);
		}
	}
	
	/**
	 * 使用模版方法封装缓存
	 */
	@Override
	public LifeWaist queryByTemplate(final Long waistId, Integer time){
		return this.redisTemplate.query(String.valueOf(waistId),
				time, new TypeReference<LifeWaist>(){}, new Callback<LifeWaist>(){
			@Override
			public LifeWaist doForCache() {
				return waistService.findByWaistId(waistId);
			}
		});
	}

	
}
 
