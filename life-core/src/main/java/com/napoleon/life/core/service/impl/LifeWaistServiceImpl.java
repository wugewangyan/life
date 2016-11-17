package com.napoleon.life.core.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.napoleon.life.common.util.StringUtil;
import com.napoleon.life.core.dao.LifeWaistDao;
import com.napoleon.life.core.entity.LifeWaist;
import com.napoleon.life.core.service.LifeWaistService;
import com.napoleon.life.exception.CommonException;
import com.napoleon.life.exception.CommonResultCode;

@Service
public class LifeWaistServiceImpl implements LifeWaistService {

	@Autowired
	private LifeWaistDao lifeWaistDao;

	@Override
	public List<LifeWaist> findByMonth(String userNo, Integer year, Integer month) {
		return this.lifeWaistDao.findByMonth(userNo, year, month);
	}

	@Override
	public List<LifeWaist> findByDate(String userNo, Timestamp startTime,
			Timestamp endTime) {
		return this.lifeWaistDao.findByDate(userNo, startTime, endTime);
	}

	@Transactional
	@Override
	public void insertOrUpdate(LifeWaist waistInfo) {
		if(waistInfo != null){
			if(StringUtil.notEmpty(waistInfo.getId())){
				this.lifeWaistDao.update(waistInfo);
			}else{
				this.lifeWaistDao.add(waistInfo);
			}
		}else{
			throw new CommonException(CommonResultCode.SYSTEM_ERR);
		}
	}

	@Override
	public LifeWaist findByWaistId(Long waistId) {
		return this.lifeWaistDao.findById(waistId);
	}
	
	@Transactional
	@Override
	public void delete(Long waistId) {
		this.lifeWaistDao.delete(waistId);
	}
}
