package com.napoleon.life.core.facade;

import com.napoleon.life.core.dto.LifeDeleteDto;
import com.napoleon.life.core.dto.LifeWaistEditDto;
import com.napoleon.life.core.dto.LifeQueryNoWeekInfoDto;



public interface LifeWaistFacade {
	
	/**
	 * 添加和编辑用户腰围信息
	 * @param waistEditDto
	 * @return
	 */
	public String editWaist(LifeWaistEditDto waistEditDto);
	
	/**
	 * 根据不同的查询类型来查询具体的腰围信息
	 * @param waistQueryDto
	 * @return
	 */
	public String getWaistInfo(LifeQueryNoWeekInfoDto waistQueryDto);
	
	/**
	 * 删除用户腰围信息
	 * @param deleteInfo
	 * @return
	 */
	public String deleteWaistInfo(LifeDeleteDto deleteInfo);
}
