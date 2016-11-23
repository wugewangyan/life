package com.napoleon.life.core.dao;

import java.util.Date;
import java.util.List;

import com.napoleon.life.common.persistence.GenericDao;
import com.napoleon.life.core.entity.LifeWeight;

public interface LifeWeightDao extends GenericDao<LifeWeight> {

	
	public List<LifeWeight> findByMonth(String userNo, Integer year, Integer month);
	
	
	public List<LifeWeight> findByWeek(String userNo, Integer year, Integer week);
	
	
	public List<LifeWeight> findByDate(String userNo, Date startTime,
			Date endTime);
	
	
	public LifeWeight findById(Long weightId);


	public LifeWeight findRecentWeightInfo(String userNo);
	
	
	
}
