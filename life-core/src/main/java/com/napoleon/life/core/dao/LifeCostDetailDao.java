package com.napoleon.life.core.dao;  
   
 import java.util.List;
import java.util.Map;

import com.napoleon.life.core.entity.LifeCostDetail;  
 
import com.napoleon.life.common.persistence.GenericDao;
 
 
 public interface LifeCostDetailDao extends GenericDao<LifeCostDetail>{
 
	public LifeCostDetail findByCostDetailNo(String costDetailNo);

	public List<LifeCostDetail> findCostDetail(Map<String, Object> params);
}
 
