package com.napoleon.life.core.service;  
   
 import com.napoleon.life.core.entity.LifeWaist;
 
 public interface TestRedisService{

	
	public LifeWaist queryForSyn(Long waistId, Integer time);
	
	
	public LifeWaist queryForConcurrent(Long waistId, Integer time);
	
	
	public LifeWaist queryByTemplate(final Long waistId, Integer time);
}
 
