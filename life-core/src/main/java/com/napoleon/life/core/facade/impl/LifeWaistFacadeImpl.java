package com.napoleon.life.core.facade.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napoleon.life.common.util.StringUtil;
import com.napoleon.life.core.dto.LifeDeleteDto;
import com.napoleon.life.core.dto.LifeWaistEditDto;
import com.napoleon.life.core.dto.LifeQueryNoWeekInfoDto;
import com.napoleon.life.core.entity.LifeWaist;
import com.napoleon.life.core.entity.LifeWeight;
import com.napoleon.life.core.enums.OpTypeEnum;
import com.napoleon.life.core.facade.LifeWaistFacade;
import com.napoleon.life.core.service.LifeWaistService;
import com.napoleon.life.core.service.LifeWeightService;
import com.napoleon.life.exception.CommonResultCode;
import com.napoleon.life.framework.result.CommonRltUtil;
import com.napoleon.life.user.enums.UserSexEnum;
import com.napoleon.life.user.service.CommonSerialNoService;


@Service
public class LifeWaistFacadeImpl implements LifeWaistFacade {

	private static final Logger logger = LoggerFactory.getLogger(LifeWaistFacadeImpl.class);
	
	@Autowired
	private LifeWaistService waistService;
	
	@Autowired
	private LifeWeightService weightService;
	
	@Autowired
	private CommonSerialNoService serialNoService;

	@Override
	public String editWaist(LifeWaistEditDto waistEditDto) {
		LifeWaist waistInfo = null;
		if(StringUtil.notEmpty(waistEditDto.getWaistId())){
			waistInfo = waistService.findByWaistId(Long.valueOf(waistEditDto.getWaistId()));
			if(waistInfo == null){
				return CommonRltUtil.createCommonRltToString(CommonResultCode.WAISTINFO_NOT_FOUND);
			}else if(!waistEditDto.getUserNo().equals(waistInfo.getUserNo())){
				return CommonRltUtil.createCommonRltToString(CommonResultCode.NOT_AUTH_TO_EDIT);
			}
		}else{
			waistInfo = new LifeWaist();
			waistInfo.setUserNo(waistEditDto.getUserNo());
		}

		Timestamp measureTime = new Timestamp(Long.parseLong(waistEditDto.getMeasurementTime()));
		waistInfo.setMeasurementTime(measureTime);
		waistInfo.setWaist(new BigDecimal(waistEditDto.getWaist()));
		
		if(StringUtil.notEmpty(waistEditDto.getHeight())){
			waistInfo.setWhr(this.calculateWHR(waistEditDto.getWaist(), waistEditDto.getHeight()));
			if(StringUtil.notEmpty(waistEditDto.getSex())){
				LifeWeight weightInfo = this.weightService.findRecentWeightInfo(waistEditDto.getUserNo());
				waistInfo.setBfr(this.calculateBFR(waistEditDto.getWaist(), weightInfo.getWeight(),
						waistEditDto.getSex()));
			}
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(measureTime);
		waistInfo.setYear(calendar.get(Calendar.YEAR));
		waistInfo.setMonth(calendar.get(Calendar.MONTH) + 1);
		waistInfo.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		waistInfo.setWeek(calendar.get(Calendar.WEEK_OF_YEAR));
		
		this.waistService.insertOrUpdate(waistInfo);
		return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS);
	}
	
	
	@Override
	public String deleteWaistInfo(LifeDeleteDto deleteInfo) {
		LifeWaist waistInfo = waistService.findByWaistId(Long.valueOf(deleteInfo.getEntityId()));
		if(waistInfo == null){
			return CommonRltUtil.createCommonRltToString(CommonResultCode.WAISTINFO_NOT_FOUND);
		}else if(!deleteInfo.getUserNo().equals(waistInfo.getUserNo())){
			return CommonRltUtil.createCommonRltToString(CommonResultCode.NOT_AUTH_TO_EDIT);
		}
		
		this.waistService.delete(waistInfo.getId());
		return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS);
	}

	
	
	@Override
	public String getWaistInfo(LifeQueryNoWeekInfoDto waistQueryDto) {
		List<LifeWaist> result = null;
		
		Calendar queryTime = Calendar.getInstance();
		queryTime.setTime(new Date(waistQueryDto.getQueryStartTime()));
		
		if(OpTypeEnum.QUERY_WITH_MONTH.getCode().equals(waistQueryDto.getOpType())){
			result = this.waistService.findByMonth(waistQueryDto.getUserNo(), queryTime.get(Calendar.YEAR), queryTime.get(Calendar.MONTH) + 1);
		}else if(OpTypeEnum.QUERY_WITH_DATE_RANGE.getCode().equals(waistQueryDto.getOpType())){
			result = this.waistService.findByDate(waistQueryDto.getUserNo(), new Timestamp(waistQueryDto.getQueryStartTime()),
					new Timestamp(waistQueryDto.getQueryEndTime()));
		}else{
			return CommonRltUtil.createCommonRltToString(CommonResultCode.OP_TYPE_NOT_SUPPORT);
		}
		
		return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS, result);
	}

	private BigDecimal calculateWHR(String waist, Integer height) {
		return new BigDecimal(waist).divide(new BigDecimal(height), 2, BigDecimal.ROUND_HALF_UP);
	}
	
	private BigDecimal calculateBFR(String waist, BigDecimal weight, String sex){
		BigDecimal subtractor = new BigDecimal(0.74).multiply(new BigDecimal(waist)).setScale(4, BigDecimal.ROUND_HALF_UP);
		BigDecimal minuend = new BigDecimal(0.082).multiply(weight).setScale(4, BigDecimal.ROUND_HALF_UP);
		
		if(UserSexEnum.MALE.getCode().equals(sex)){
			minuend = minuend.add(new BigDecimal(44.74));
		}else{
			minuend = minuend.add(new BigDecimal(34.89));
		}
		
		return subtractor.subtract(minuend).setScale(4, BigDecimal.ROUND_HALF_UP).divide(weight, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
	}
}
