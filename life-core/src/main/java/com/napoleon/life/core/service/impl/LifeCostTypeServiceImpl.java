package com.napoleon.life.core.service.impl;  
   
 import java.util.List;

import com.napoleon.life.core.entity.LifeCostType;  

import org.springframework.stereotype.Service;  

import com.napoleon.life.exception.CommonResultCode;  
import com.napoleon.life.common.util.StringUtil;  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.transaction.annotation.Transactional;  

import com.napoleon.life.exception.CommonException;  
import com.napoleon.life.core.dao.LifeCostTypeDao;  
import com.napoleon.life.core.service.LifeCostTypeService;  
 
 @Service
 public class LifeCostTypeServiceImpl implements LifeCostTypeService{

	@Autowired
	private LifeCostTypeDao lifeCostTypeDao;

	@Transactional
	@Override
	public void insertOrUpdate(LifeCostType info) {
		if(info != null){
			if(StringUtil.notEmpty(info.getId())){
				this.lifeCostTypeDao.update(info);
			}else{
				this.lifeCostTypeDao.add(info);
			}
		}else{
			throw new CommonException(CommonResultCode.SYSTEM_ERR);
		}
	}

	@Override
	public LifeCostType findByCostTypeNo(String costTypeNo){
        return this.lifeCostTypeDao.findByCostTypeNo(costTypeNo);
	}

	@Override
	public LifeCostType findByEntityId(Long entityId) {
		return this.lifeCostTypeDao.get(entityId);
	}
	
	@Transactional
	@Override
	public void delete(Long entityId) {
		this.lifeCostTypeDao.delete(entityId);
	}
	
	@Override
	public List<LifeCostType> findAllCostTypes() {
		return this.lifeCostTypeDao.query("list");
	}
}
 
