package com.napoleon.life.core.facade;

import com.napoleon.life.core.dto.LifeDeleteDto;
import com.napoleon.life.core.dto.LifeWeightEditDto;
import com.napoleon.life.core.dto.LifeQueryInfoDto;



public interface LifeWeightFacade {
	
	/**
	 * 添加和编辑用户体重信息
	 * @param weightEditDto
	 * @return
	 */
	public String editWeight(LifeWeightEditDto weightEditDto);
	
	/**
	 * 根据不同的查询类型来查询具体的体重信息
	 * @param weightQueryDto
	 * @return
	 */
	public String getWeightInfo(LifeQueryInfoDto weightQueryDto);
	
	/**
	 * 删除用户的体重信息
	 * @param deleteInfo
	 * @return
	 */
	public String deleteWeightInfo(LifeDeleteDto deleteInfo);
}
