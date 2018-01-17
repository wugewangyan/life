package com.napoleon.life.core.controller;  
   
 import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.napoleon.life.core.dto.LifeWeightEditDto;
import com.napoleon.life.core.facade.LifeRabbitMQFacade;
import com.napoleon.life.framework.base.BaseController;
import com.napoleon.life.framework.resolver.ParamValid;
 
@Controller
@RequestMapping("/life/rabbitmq")
public class LifeRabbitMQController extends BaseController{

	@Autowired
	private LifeRabbitMQFacade lifeRabbitMQFacade;
	
	@ResponseBody
	@RequestMapping(value = "/product", method = RequestMethod.POST)
    public String serialize(@ParamValid LifeWeightEditDto weightEditDto) {
		Random rand = new Random();
		int index = new Integer(weightEditDto.getWeight());
		
		for(int i = 0; i < index; i++){
			try{
				weightEditDto.setWeight(new Integer(rand.nextInt(100)).toString());
				this.lifeRabbitMQFacade.productWeight(weightEditDto);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return this.lifeRabbitMQFacade.productWeight(weightEditDto);
    }
}
 
