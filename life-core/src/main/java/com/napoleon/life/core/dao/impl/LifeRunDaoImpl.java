package com.napoleon.life.core.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.napoleon.life.common.persistence.GenericDaoDefault;
import com.napoleon.life.core.bean.SumMonthRunBean;
import com.napoleon.life.core.bean.SumWeekRunBean;
import com.napoleon.life.core.dao.LifeRunDao;
import com.napoleon.life.core.entity.LifeRun;
import com.napoleon.life.core.enums.QuarterEnum;

@Repository
public class LifeRunDaoImpl extends GenericDaoDefault<LifeRun> implements
		LifeRunDao {

	@Override
	public List<LifeRun> findByDay(String userNo, Date startTime, Date endTime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		return (List<LifeRun>)super.query("findByDay", map);
	}
	
	
	@Override
	public List<LifeRun> findByYearAndWeek(String userNo, Integer year,
			Integer week) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("year", year);
		map.put("week", week);
		
		return (List<LifeRun>)super.query("findByYearAndWeek", map);
	}

	@Override
	public List<LifeRun> findByYearAndQuarter(String userNo, Integer year,
			QuarterEnum quarter) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("year", year);
		map.put("quarter", quarter.getCode());
		
		return (List<LifeRun>)super.query("findByYearAndQuarter", map);
	}
	
	
	/**
	 * --------------------------------------------------------------------------------------
	 */
	
	
	@Override
	public List<LifeRun> findByYear(String userNo, Integer year) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("year", year);
		return (List<LifeRun>)super.query("findByYear", map);
	}
	
	@Override
	public List<LifeRun> findByYearAndMonth(String userNo, Integer year,
			Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("year", year);
		map.put("month", month);
		
		return (List<LifeRun>)super.query("findByYearAndMonth", map);
	}

	@Override
	public List<SumWeekRunBean> groupByYearAndWeek(String userNo, Integer year) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("year", year);
		return super.query("groupByYearAndWeek", map);
	}

	@Override
	public List<SumMonthRunBean> groupByYearAndMonth(String userNo, Integer year) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("year", year);
		return super.query("groupByYearAndMonth", map);
	}

}
