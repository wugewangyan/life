package com.napoleon.life.core.controller;  
   
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.napoleon.life.core.dto.LifeCostTypeEditDto;
import com.napoleon.life.core.dto.LifeDeleteDto;
import com.napoleon.life.core.facade.LifeCostTypeFacade;
import com.napoleon.life.framework.base.BaseController;
import com.napoleon.life.framework.base.BaseDto;
import com.napoleon.life.framework.resolver.ParamValid;
 
@Controller
@RequestMapping("/life/cost_type")
public class LifeCostTypeController extends BaseController{

	@Autowired
	private LifeCostTypeFacade lifeCostTypeFacade;
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editLifeCostType(@ParamValid LifeCostTypeEditDto editDto) {
		return this.lifeCostTypeFacade.editCostTypeInfo(editDto);
    }
    
    @ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteLifeCostType(@ParamValid LifeDeleteDto deleteDto) {
		return this.lifeCostTypeFacade.deleteCostTypeInfo(deleteDto);
    }
    
    /**
     * 查询所有的花费类型
     * @param queryDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/find_all", method = RequestMethod.GET)
    public String findAllCostTypes(@ParamValid BaseDto queryDto) {
        return this.lifeCostTypeFacade.getALLCostTypes();
    }
}
 
