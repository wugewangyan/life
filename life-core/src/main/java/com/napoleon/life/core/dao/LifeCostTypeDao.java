package com.napoleon.life.core.dao;  
   
 import com.napoleon.life.core.entity.LifeCostType;  
 
import com.napoleon.life.common.persistence.GenericDao;
 
 
 public interface LifeCostTypeDao extends GenericDao<LifeCostType>{
 
	public LifeCostType findByCostTypeNo(String costTypeNo);
}
 
