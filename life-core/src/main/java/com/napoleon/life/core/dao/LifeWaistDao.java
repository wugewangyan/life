package com.napoleon.life.core.dao;

import java.util.Date;
import java.util.List;

import com.napoleon.life.common.persistence.GenericDao;
import com.napoleon.life.core.entity.LifeWaist;

public interface LifeWaistDao extends GenericDao<LifeWaist> {

	
	public List<LifeWaist> findByMonth(String userNo, Integer year, Integer month);
	
	
	public List<LifeWaist> findByDate(String userNo, Date startTime, Date endTime);
	
	
	public LifeWaist findById(Long waistId);
	
	
	
}
