package com.napoleon.life.core.dao;

import java.util.Date;
import java.util.List;

import com.napoleon.life.common.persistence.GenericDao;
import com.napoleon.life.core.bean.SumMonthRunBean;
import com.napoleon.life.core.bean.SumWeekRunBean;
import com.napoleon.life.core.entity.LifeRun;
import com.napoleon.life.core.enums.QuarterEnum;

public interface LifeRunDao extends GenericDao<LifeRun> {

	/**
	 * 查询某个时间段的跑步情况
	 * @param userNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<LifeRun> findByDay(String userNo, Date startTime, Date endTime);
	
	
	
	
	/**
	 * 查询某位用户在某年某周的跑步情况
	 * @param userNo
	 * @param year
	 * @param week
	 * @return
	 */
	public List<LifeRun> findByYearAndWeek(String userNo, Integer year, Integer week); 
	
	
	/**
	 * 查询某位用户在某年某季度的跑步情况
	 * @param userNo
	 * @param year
	 * @param querter
	 * @return
	 */
	public List<LifeRun> findByYearAndQuarter(String userNo, Integer year, QuarterEnum quarter);
	
	
	/**
	 * --------------------------------------------------------------------------------------
	 */
	
	
	/**
	 * 查询某人某年的跑步情况
	 * @param userNo
	 * @param year
	 * @return
	 */
	public List<LifeRun> findByYear(String userNo, Integer year);
	
	
	/**
	 * 查询某位用户在某年某月的跑步情况
	 * @param userNo
	 * @param year
	 * @param month
	 * @return
	 */
	public List<LifeRun> findByYearAndMonth(String userNo, Integer year, Integer month);
	
	
	
	/**
	 * 统计某位用户在某年每个星期的跑步情况（长度）
	 * @param userNo
	 * @param year
	 * @return
	 */
	public List<SumWeekRunBean> groupByYearAndWeek(String userNo, Integer year);
	
	
	/**
	 * 统计某位用户在某年每个月的跑步情况（长度）
	 * @param userNo
	 * @param year
	 * @return
	 */
	public List<SumMonthRunBean> groupByYearAndMonth(String userNo, Integer year);
	
}
