package com.napoleon.life.core.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.napoleon.life.common.util.StringUtil;
import com.napoleon.life.core.dao.LifeWeightDao;
import com.napoleon.life.core.entity.LifeWeight;
import com.napoleon.life.core.service.LifeWeightService;
import com.napoleon.life.exception.CommonException;
import com.napoleon.life.exception.CommonResultCode;

@Service
public class LifeWeightServiceImpl implements LifeWeightService {

	@Autowired
	private LifeWeightDao lifeWeightDao;

	@Override
	public List<LifeWeight> findByMonth(String userNo, Integer year, Integer month) {
		return this.lifeWeightDao.findByMonth(userNo, year, month);
	}

	@Override
	public List<LifeWeight> findByWeek(String userNo, Integer year, Integer week) {
		return this.lifeWeightDao.findByWeek(userNo, year, week);
	}

	@Override
	public List<LifeWeight> findByDate(String userNo, Timestamp startTime,
			Timestamp endTime) {
		return this.lifeWeightDao.findByDate(userNo, startTime, endTime);
	}

	@Transactional
	@Override
	public void insertOrUpdate(LifeWeight weightInfo) {
		if(weightInfo != null){
			if(StringUtil.notEmpty(weightInfo.getId())){
				this.lifeWeightDao.update(weightInfo);
			}else{
				this.lifeWeightDao.add(weightInfo);
			}
		}else{
			throw new CommonException(CommonResultCode.SYSTEM_ERR);
		}
	}

	@Override
	public LifeWeight findByWeightId(Long weightId) {
		return this.lifeWeightDao.findById(weightId);
	}
	
	
	@Override
	public LifeWeight findRecentWeightInfo(String userNo) {
		return this.lifeWeightDao.findRecentWeightInfo(userNo);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		this.lifeWeightDao.delete(id);
	}
}
