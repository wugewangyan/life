package com.napoleon.life.core.facade.impl;  
   
 import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napoleon.life.common.util.StringUtil;
import com.napoleon.life.core.dto.LifeCostTypeEditDto;
import com.napoleon.life.core.dto.LifeDeleteDto;
import com.napoleon.life.core.entity.LifeCostType;
import com.napoleon.life.core.facade.LifeCostTypeFacade;
import com.napoleon.life.core.service.LifeCostTypeService;
import com.napoleon.life.core.util.Constants;
import com.napoleon.life.framework.result.CommonRltUtil;
import com.napoleon.life.user.code.UserModelCode;
import com.napoleon.life.user.service.CommonSerialNoService;
 
 @Service
 public class LifeCostTypeFacadeImpl implements LifeCostTypeFacade{

	@Autowired
	private LifeCostTypeService lifeCostTypeService;
	
	@Autowired
	private CommonSerialNoService serialNoService;

	@Override
	public String editCostTypeInfo(LifeCostTypeEditDto editDto) {
		LifeCostType lifeCostType = null;
		if(StringUtil.notEmpty(editDto.getEntityId())){
			lifeCostType = lifeCostTypeService.findByEntityId(Long.valueOf(editDto.getEntityId()));
			if(lifeCostType == null){
				return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
			}
		}else{
			lifeCostType = new LifeCostType();
			lifeCostType.setCreateTime(new Timestamp(new Date().getTime()));
			lifeCostType.setCostTypeNo(this.serialNoService.getSerialNo(Constants.COST_TYPE_NO));
		}

		lifeCostType.setCostType(editDto.getCostType());
		lifeCostType.setDescription(editDto.getDescription());
		lifeCostType.setUpdateTime(new Timestamp(new Date().getTime()));
		
		this.lifeCostTypeService.insertOrUpdate(lifeCostType);
		return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
	}
	
	@Override
	public String deleteCostTypeInfo(LifeDeleteDto deleteInfo) {
		LifeCostType lifeCostType = this.lifeCostTypeService.findByCostTypeNo(deleteInfo.getEntityId());
		if(lifeCostType == null){
			return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
		}
		
		this.lifeCostTypeService.delete(lifeCostType.getId());
		return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
	}
     
     @Override
     public String getALLCostTypes() {
         List<LifeCostType> result = this.lifeCostTypeService.findAllCostTypes();
         return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
     }
}
 
