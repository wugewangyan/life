package com.napoleon.life.core.dao.impl;  
   
 import org.springframework.stereotype.Repository;  
 import java.util.Map;  
 import java.util.HashMap;  
 import com.napoleon.life.common.persistence.GenericDaoDefault;  
 import com.napoleon.life.core.entity.LifeCostType;  
 import com.napoleon.life.core.dao.LifeCostTypeDao;  
 
 @Repository
 public class LifeCostTypeDaoImpl extends GenericDaoDefault<LifeCostType> implements LifeCostTypeDao{

	@Override
	public LifeCostType findByCostTypeNo(String costTypeNo){
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("costTypeNo", costTypeNo);
        return (LifeCostType)super.queryOne("findByCostTypeNo", params);
	}
}
 
