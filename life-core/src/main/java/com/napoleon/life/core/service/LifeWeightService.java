package com.napoleon.life.core.service;

import java.sql.Timestamp;
import java.util.List;

import com.napoleon.life.core.entity.LifeWeight;


public interface LifeWeightService {
	
	/**
	 * 通过主键获取体重信息
	 * @param weightId
	 * @return
	 */
	public LifeWeight findByWeightId(Long weightId);
	
	/**
	 * 获取某个用户的某个月的体重信息
	 * @param userNo
	 * @param month
	 * @return
	 */
	public List<LifeWeight> findByMonth(String userNo, Integer year, Integer month);
	
	/**
	 * 获取某个用户的某个周的体重信息
	 * @param userNo
	 * @param week
	 * @return
	 */
	public List<LifeWeight> findByWeek(String userNo, Integer year, Integer week);
	
	/**
	 * 获取某个时间段内的体重信息
	 * @param userNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<LifeWeight> findByDate(String userNo, Timestamp startTime, Timestamp endTime);
	
	/**
	 * 插入LifeWeight信息
	 * @param weightInfo
	 */
	public void insertOrUpdate(LifeWeight weightInfo);
	
	/**
	 * 查询最新的体重信息
	 * @param userNo
	 * @return
	 */
	public LifeWeight findRecentWeightInfo(String userNo);

	/**
	 * 删除用户的体重信息
	 * @param id
	 */
	public void delete(Long id);
}
