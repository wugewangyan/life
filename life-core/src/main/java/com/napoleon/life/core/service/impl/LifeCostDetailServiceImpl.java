package com.napoleon.life.core.service.impl;  
   
 import java.util.List;
import java.util.Map;

import com.napoleon.life.core.entity.LifeCostDetail;  

import org.springframework.stereotype.Service;  

import com.napoleon.life.exception.CommonResultCode;  
import com.napoleon.life.common.util.StringUtil;  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.transaction.annotation.Transactional;  

import com.napoleon.life.exception.CommonException;  
import com.napoleon.life.core.dao.LifeCostDetailDao;  
import com.napoleon.life.core.service.LifeCostDetailService;  
 
 @Service
 public class LifeCostDetailServiceImpl implements LifeCostDetailService{

	@Autowired
	private LifeCostDetailDao lifeCostDetailDao;

	@Transactional
	@Override
	public void insertOrUpdate(LifeCostDetail info) {
		if(info != null){
			if(StringUtil.notEmpty(info.getId())){
				this.lifeCostDetailDao.update(info);
			}else{
				this.lifeCostDetailDao.add(info);
			}
		}else{
			throw new CommonException(CommonResultCode.SYSTEM_ERR);
		}
	}

	@Override
	public LifeCostDetail findByCostDetailNo(String costDetailNo){
        return this.lifeCostDetailDao.findByCostDetailNo(costDetailNo);
	}

	@Override
	public LifeCostDetail findByEntityId(Long entityId) {
		return this.lifeCostDetailDao.get(entityId);
	}
	
	@Transactional
	@Override
	public void delete(Long entityId) {
		this.lifeCostDetailDao.delete(entityId);
	}
	
	@Override
	public List<LifeCostDetail> findCostDetail(Map<String, Object> params) {
		return this.lifeCostDetailDao.findCostDetail(params);
	}
}
 
