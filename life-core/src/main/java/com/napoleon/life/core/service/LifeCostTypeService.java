package com.napoleon.life.core.service;  
   
 import java.util.List;

import com.napoleon.life.core.entity.LifeCostType;  
 
public interface LifeCostTypeService{

	public void insertOrUpdate(LifeCostType info);
	
	
	public LifeCostType findByCostTypeNo(String costTypeNo);


	public LifeCostType findByEntityId(Long entityId);
	

	public void delete(Long entityId);
	
	public List<LifeCostType> findAllCostTypes();
}
 
