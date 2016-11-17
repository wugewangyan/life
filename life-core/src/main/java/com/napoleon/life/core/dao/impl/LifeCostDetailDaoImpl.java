package com.napoleon.life.core.dao.impl;  
   
 import org.springframework.stereotype.Repository;  

import java.util.List;
import java.util.Map;  
import java.util.HashMap;  

import com.napoleon.life.common.persistence.GenericDaoDefault;  
import com.napoleon.life.core.entity.LifeCostDetail;  
import com.napoleon.life.core.dao.LifeCostDetailDao;  
 
 @Repository
 public class LifeCostDetailDaoImpl extends GenericDaoDefault<LifeCostDetail> implements LifeCostDetailDao{

	@Override
	public LifeCostDetail findByCostDetailNo(String costDetailNo){
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("costDetailNo", costDetailNo);
        return (LifeCostDetail)super.queryOne("findByCostDetailNo", params);
	}
	
	@Override
	public List<LifeCostDetail> findCostDetail(Map<String, Object> params) {
		return super.query("findCostDetail", params);
	}
}
 
