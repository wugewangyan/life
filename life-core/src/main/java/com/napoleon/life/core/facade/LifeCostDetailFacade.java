package com.napoleon.life.core.facade;

import com.napoleon.life.core.dto.LifeCostDetailEditDto;
import com.napoleon.life.core.dto.LifeCostDetailQueryDto;
import com.napoleon.life.core.dto.LifeDeleteDto;
import com.napoleon.life.core.dto.LifeQueryNoWeekInfoDto;



public interface LifeCostDetailFacade {
	
	/**
	 * 添加和编辑用户花费信息
	 * @param editDto
	 * @return
	 */
	public String editCostDetailInfo(LifeCostDetailEditDto editDto);
	
	/**
	 * 根据不同的查询类型来查询具体的花费信息
	 * @param waistQueryDto
	 * @return
	 */
	public String getCostDetailInfo(LifeCostDetailQueryDto queryDto);
	
	/**
	 * 删除用户花费信息
	 * @param deleteInfo
	 * @return
	 */
	public String deleteCostDetailInfo(LifeDeleteDto deleteInfo);
}
