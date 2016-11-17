package com.napoleon.life.core.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.napoleon.life.common.persistence.GenericDaoDefault;
import com.napoleon.life.core.dao.LifeWaistDao;
import com.napoleon.life.core.entity.LifeWaist;

@Repository
public class LifeWaistDaoImpl extends GenericDaoDefault<LifeWaist> implements
	LifeWaistDao {

	@Override
	public List<LifeWaist> findByMonth(String userNo, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("year", year);
		map.put("month", month);
		return super.query("findByMonth", map);
	}

	@Override
	public List<LifeWaist> findByDate(String userNo, Date startTime,
			Date endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return super.query("findByDate", map);
	}

	@Override
	public LifeWaist findById(Long waistId) {
		return super.get(waistId);
	}

}
