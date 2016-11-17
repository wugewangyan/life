package com.napoleon.life.core.service;

import java.util.List;

import com.github.abel533.echarts.Option;
import com.napoleon.life.core.bean.SumMonthRunBean;
import com.napoleon.life.core.entity.LifeRun;

public interface LifeRunService {
	
	
	/**
	 * 查询用户在某年某月的跑步详情
	 * @param userNo  用户注册的email账号
	 * @param year
	 * @param month
	 * @return
	 */
	public List<LifeRun> findByYearAndMonth(String userNo, Integer year, Integer month);
	
	
	
	public Option findByYear(String userNo, Integer year);
	
	
	public List<SumMonthRunBean> groupByYearAndMonth(String userNo, Integer year);
	
	public Option groupByYearAndWeek(String userNo, Integer year);
}
