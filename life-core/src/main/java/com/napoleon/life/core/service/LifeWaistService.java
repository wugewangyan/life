package com.napoleon.life.core.service;

import java.sql.Timestamp;
import java.util.List;

import com.napoleon.life.core.entity.LifeWaist;


public interface LifeWaistService {
	
	/**
	 * 通过主键获取腰围信息
	 * @param waistId
	 * @return
	 */
	public LifeWaist findByWaistId(Long waistId);
	
	/**
	 * 获取某个用户的某个月的腰围信息
	 * @param userNo
	 * @param month
	 * @return
	 */
	public List<LifeWaist> findByMonth(String userNo, Integer year, Integer month);
	
	/**
	 * 获取某个时间段内的腰围信息
	 * @param userNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<LifeWaist> findByDate(String userNo, Timestamp startTime, Timestamp endTime);
	
	/**
	 * 插入LifeWaist信息
	 * @param waistInfo
	 */
	public void insertOrUpdate(LifeWaist waistInfo);
	
	/**
	 * 删除LifeWaist信息
	 * @param waistInfo
	 */
	public void delete(Long waistInfo);
}


