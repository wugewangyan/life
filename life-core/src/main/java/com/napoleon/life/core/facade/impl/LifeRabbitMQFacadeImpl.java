package com.napoleon.life.core.facade.impl;  
   
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.napoleon.life.core.dto.LifeWeightEditDto;
import com.napoleon.life.core.facade.LifeRabbitMQFacade;
import com.napoleon.life.framework.result.CommonRltUtil;
import com.napoleon.life.user.code.UserModelCode;
 
 @Service
 public class LifeRabbitMQFacadeImpl implements LifeRabbitMQFacade{
	
	@Autowired
	private AmqpTemplate amqpTemplate;
		
	@Override
	public String productWeight(LifeWeightEditDto weightEditDto){
		// queue_one_key 表示routingKey，这里的routingKey应该与配置文件中的binding 的key属性相同
		this.amqpTemplate.convertAndSend(weightEditDto.getKey(), weightEditDto);
		
		return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR,
				JSON.toJSONString(weightEditDto));
	}
	
	
	
}
 
