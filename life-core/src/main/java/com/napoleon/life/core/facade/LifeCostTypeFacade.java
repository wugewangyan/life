package com.napoleon.life.core.facade;

import com.napoleon.life.core.dto.LifeCostTypeEditDto;
import com.napoleon.life.core.dto.LifeDeleteDto;




public interface LifeCostTypeFacade {

	public String editCostTypeInfo(LifeCostTypeEditDto editDto);

	public String deleteCostTypeInfo(LifeDeleteDto deleteInfo);

	public String getALLCostTypes();
	
}
