package com.napoleon.life.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.napoleon.life.core.dto.LifeCostDetailEditDto;
import com.napoleon.life.core.dto.LifeCostDetailQueryDto;
import com.napoleon.life.core.dto.LifeDeleteDto;
import com.napoleon.life.core.facade.LifeCostDetailFacade;
import com.napoleon.life.framework.base.BaseController;
import com.napoleon.life.framework.resolver.ParamValid;

@Controller
@RequestMapping("/life/cost")
public class LifeCostDetailController extends BaseController{
	
	@Autowired
	private LifeCostDetailFacade lifeCostDetailFacade;

	/**
	 * 添加和修改花费信息
	 * @param editDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String costDetailEdit(@ParamValid LifeCostDetailEditDto editDto) {
		return this.lifeCostDetailFacade.editCostDetailInfo(editDto);
    }
	
	/**
	 * 按照不用的参数查询花费信息
	 * @param queryDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.GET)
    public String queryCostDetail(@ParamValid LifeCostDetailQueryDto queryDto) {
		return this.lifeCostDetailFacade.getCostDetailInfo(queryDto);
    }
	
	/**
	 * 删除用户的花费信息
	 * @param deleteDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteWeight(@ParamValid LifeDeleteDto deleteDto) {
		return this.lifeCostDetailFacade.deleteCostDetailInfo(deleteDto);
    }
	
}
