package com.napoleon.life.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.napoleon.life.core.dto.LifeDeleteDto;
import com.napoleon.life.core.dto.LifeQueryInfoDto;
import com.napoleon.life.core.dto.LifeWeightEditDto;
import com.napoleon.life.core.facade.LifeWeightFacade;
import com.napoleon.life.framework.base.BaseController;
import com.napoleon.life.framework.resolver.ParamValid;

@Controller
@RequestMapping("/life/weight")
public class LifeWeightController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(LifeWeightController.class);

	@Autowired
	private LifeWeightFacade lifeWeightFacade;

	/**
	 * 添加和修改体重信息
	 * @param weightEditDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String weightEdit(@ParamValid LifeWeightEditDto weightEditDto) {
		return this.lifeWeightFacade.editWeight(weightEditDto);
    }
	
	/**
	 * 按照不用的类型查询体重信息
	 * @param weightQueryDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.GET)
    public String queryWeight(@ParamValid LifeQueryInfoDto weightQueryDto) {
		return this.lifeWeightFacade.getWeightInfo(weightQueryDto);
    }
	
	/**
	 * 删除用户的体重信息
	 * @param deleteDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteWeight(@ParamValid LifeDeleteDto deleteDto) {
		return this.lifeWeightFacade.deleteWeightInfo(deleteDto);
    }
	
}
