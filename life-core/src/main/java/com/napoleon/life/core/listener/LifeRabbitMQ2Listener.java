package com.napoleon.life.core.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.napoleon.life.core.dto.LifeWeightEditDto;
import com.napoleon.life.core.facade.LifeWeightFacade;

public class LifeRabbitMQ2Listener implements MessageListener{

	@Autowired
	private LifeWeightFacade lifeWeightFacade;
	
	@Override
	public void onMessage(Message message) {
		try{
			String json = new String(message.getBody(), "UTF-8");
			LifeWeightEditDto editDto = JSON.parseObject(json, LifeWeightEditDto.class);
			editDto.setUserNo(editDto.getUserNo() + "_2");
			this.lifeWeightFacade.editWeight(editDto);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
