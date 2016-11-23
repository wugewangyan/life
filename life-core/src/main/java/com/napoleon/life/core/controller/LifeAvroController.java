package com.napoleon.life.core.controller;  
   
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.napoleon.life.core.dto.LifeAvroDto;
import com.napoleon.life.core.entity.Book;
import com.napoleon.life.core.facade.LifeAvroFacade;
import com.napoleon.life.framework.base.BaseController;
import com.napoleon.life.framework.base.BaseDto;
import com.napoleon.life.framework.resolver.ParamValid;
 
@Controller
@RequestMapping("/life/avro")
public class LifeAvroController extends BaseController{

	@Autowired
	private LifeAvroFacade lifeAvroFacade;
	
	@ResponseBody
	@RequestMapping(value = "/serialize", method = RequestMethod.POST)
    public String serialize(@ParamValid LifeAvroDto avroDto) {
		Book book = new Book();
		book.setId(avroDto.getId());
		book.setName(avroDto.getName());
		book.setCategory(avroDto.getCategory());
		return this.lifeAvroFacade.avroSerialize(book);
    }
	
	
	@ResponseBody
	@RequestMapping(value = "/deserialize", method = RequestMethod.GET)
    public String deserialize(@ParamValid LifeAvroDto avroDto) {
		return this.lifeAvroFacade.avroDeserialize(avroDto.getBookPath());
    }
}
 
