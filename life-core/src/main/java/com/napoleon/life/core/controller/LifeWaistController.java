package com.napoleon.life.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.napoleon.life.core.dto.LifeDeleteDto;
import com.napoleon.life.core.dto.LifeWaistEditDto;
import com.napoleon.life.core.dto.LifeQueryNoWeekInfoDto;
import com.napoleon.life.core.facade.LifeWaistFacade;
import com.napoleon.life.framework.base.BaseController;
import com.napoleon.life.framework.resolver.ParamValid;

@Controller
@RequestMapping("/life/waist")
public class LifeWaistController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(LifeWaistController.class);

	@Autowired
	private LifeWaistFacade lifeWaistFacade;

	/**
	 * 添加和修改体重信息
	 * @param waistEditDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String waistEdit(@ParamValid LifeWaistEditDto waistEditDto) {
		return this.lifeWaistFacade.editWaist(waistEditDto);
    }
	
	/**
	 * 按照不用的类型查询体重信息
	 * @param waistQueryDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.GET)
    public String queryWaist(@ParamValid LifeQueryNoWeekInfoDto waistQueryDto) {
		return this.lifeWaistFacade.getWaistInfo(waistQueryDto);
    }
	
	/**
	 * 删除用户的腰围信息
	 * @param deleteDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteWaist(@ParamValid LifeDeleteDto deleteDto) {
		return this.lifeWaistFacade.deleteWaistInfo(deleteDto);
    }
	
}
