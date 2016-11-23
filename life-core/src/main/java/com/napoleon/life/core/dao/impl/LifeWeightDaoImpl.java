package com.napoleon.life.core.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.napoleon.life.common.persistence.GenericDaoDefault;
import com.napoleon.life.core.dao.LifeWeightDao;
import com.napoleon.life.core.entity.LifeWeight;

@Repository
public class LifeWeightDaoImpl extends GenericDaoDefault<LifeWeight> implements
		LifeWeightDao {

	@Override
	public List<LifeWeight> findByMonth(String userNo, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("year", year);
		map.put("month", month);
		return super.query("findByMonth", map);
	}

	@Override
	public List<LifeWeight> findByWeek(String userNo, Integer year, Integer week){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("year", year);
		map.put("week", week);
		return super.query("findByWeek", map);
	}

	@Override
	public List<LifeWeight> findByDate(String userNo, Date startTime,
			Date endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return super.query("findByDate", map);
	}

	@Override
	public LifeWeight findById(Long weightId) {
		return super.get(weightId);
	}
	
	@Override
	public LifeWeight findRecentWeightInfo(String userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		return (LifeWeight)super.queryOne("findRecentWeightInfo", map);
	}

}
