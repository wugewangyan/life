package com.napoleon.life.core.service;  
   
 import java.util.List;
import java.util.Map;

import com.napoleon.life.core.entity.LifeCostDetail;  
 
public interface LifeCostDetailService{

	public void insertOrUpdate(LifeCostDetail info);
	
	
	public LifeCostDetail findByCostDetailNo(String costDetailNo);


	public LifeCostDetail findByEntityId(Long entityId);
	

	public void delete(Long entityId);


	public List<LifeCostDetail> findCostDetail(Map<String, Object> params);
}
 
