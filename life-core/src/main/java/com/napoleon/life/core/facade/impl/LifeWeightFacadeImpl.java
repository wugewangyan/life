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
import com.napoleon.life.core.dto.LifeQueryInfoDto;
import com.napoleon.life.core.dto.LifeWeightEditDto;
import com.napoleon.life.core.entity.LifeWeight;
import com.napoleon.life.core.enums.OpTypeEnum;
import com.napoleon.life.core.facade.LifeWeightFacade;
import com.napoleon.life.core.service.LifeWeightService;
import com.napoleon.life.framework.result.CommonRltUtil;
import com.napoleon.life.user.code.UserModelCode;
import com.napoleon.life.user.enums.UserSexEnum;
import com.napoleon.life.user.service.CommonSerialNoService;


@Service
public class LifeWeightFacadeImpl implements LifeWeightFacade {

	private static final Logger logger = LoggerFactory.getLogger(LifeWeightFacadeImpl.class);
	
	@Autowired
	private LifeWeightService weightService;
	
	@Autowired
	private CommonSerialNoService serialNoService;

	@Override
	public String editWeight(LifeWeightEditDto weightEditDto) {
		LifeWeight weightInfo = null;
		if(StringUtil.notEmpty(weightEditDto.getWeightId())){
			weightInfo = weightService.findByWeightId(Long.valueOf(weightEditDto.getWeightId()));
			if(weightInfo == null){
				return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
			}else if(!weightEditDto.getUserNo().equals(weightInfo.getUserNo())){
				return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
			}
		}else{
			weightInfo = new LifeWeight();
			weightInfo.setUserNo(weightEditDto.getUserNo());
		}

		if(StringUtil.notEmpty(weightEditDto.getMeasurementTime())){
			Timestamp measureTime = new Timestamp(Long.parseLong(weightEditDto.getMeasurementTime()));
			weightInfo.setMeasurementTime(measureTime);
		}else{
			weightInfo.setMeasurementTime(new Timestamp(new Date().getTime()));
		}
		
		weightInfo.setWeight(new BigDecimal(weightEditDto.getWeight()));
		
		if(StringUtil.notEmpty(weightEditDto.getHeight())){
			weightInfo.setBmi(this.calculateBMI(weightEditDto.getWeight(), weightEditDto.getHeight()));
			if(StringUtil.notEmpty(weightEditDto.getBirthday()) && StringUtil.notEmpty(weightEditDto.getSex())){
				weightInfo.setRmr(this.calculateRMR(weightEditDto.getWeight(), weightEditDto.getHeight(),
						weightEditDto.getSex(), weightEditDto.getBirthday()));
			}
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(weightInfo.getMeasurementTime());
		weightInfo.setYear(calendar.get(Calendar.YEAR));
		weightInfo.setMonth(calendar.get(Calendar.MONTH) + 1);
		weightInfo.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		weightInfo.setWeek(calendar.get(Calendar.WEEK_OF_YEAR));
		
		this.weightService.insertOrUpdate(weightInfo);
		return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
	}
	
	@Override
	public String deleteWeightInfo(LifeDeleteDto deleteInfo) {
		LifeWeight weightInfo = this.weightService.findByWeightId(Long.valueOf(deleteInfo.getEntityId()));
		if(weightInfo == null){
			return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
		}else if(!deleteInfo.getUserNo().equals(weightInfo.getUserNo())){
			return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
		}
		
		this.weightService.delete(weightInfo.getId());
		return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
	}

	
	
	@Override
	public String getWeightInfo(LifeQueryInfoDto weightQueryDto) {
		List<LifeWeight> result = null;
		
		Calendar queryTime = Calendar.getInstance();
		queryTime.setTime(new Date(weightQueryDto.getQueryStartTime()));
		
		if(OpTypeEnum.QUERY_WITH_WEEK.getCode().equals(weightQueryDto.getOpType())){
			result = this.weightService.findByWeek(weightQueryDto.getUserNo(), queryTime.get(Calendar.YEAR), queryTime.get(Calendar.WEEK_OF_YEAR));
		}else if(OpTypeEnum.QUERY_WITH_MONTH.getCode().equals(weightQueryDto.getOpType())){
			result = this.weightService.findByMonth(weightQueryDto.getUserNo(), queryTime.get(Calendar.YEAR), queryTime.get(Calendar.MONTH) + 1);
		}else if(OpTypeEnum.QUERY_WITH_DATE_RANGE.getCode().equals(weightQueryDto.getOpType())){
			result = this.weightService.findByDate(weightQueryDto.getUserNo(), new Timestamp(weightQueryDto.getQueryStartTime()),
					new Timestamp(weightQueryDto.getQueryEndTime()));
		}else{
			return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
		}
		
		return CommonRltUtil.createCommonRltToString(UserModelCode.USER_ERROR);
	}

	private BigDecimal calculateBMI(String weight, Integer height) {
		return new BigDecimal(weight).divide(new BigDecimal(height).divide(new BigDecimal(100)).pow(2), 2, BigDecimal.ROUND_HALF_UP);
	}
	
	private BigDecimal calculateRMR(String weight, Integer height, String sex, Timestamp birthday){
		Integer age = this.getAge(birthday);
		BigDecimal base = new BigDecimal(10).multiply(new BigDecimal(weight))
				.add(new BigDecimal(6.25).multiply(new BigDecimal(height))).subtract(new BigDecimal(5).multiply(new BigDecimal(age)));
		if(UserSexEnum.MALE.getCode().equals(sex)){
			return base.add(new BigDecimal(5)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else{
			return base.subtract(new BigDecimal(161)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	
	
	private Integer getAge(Date birthday){
		if(birthday != null){
			Calendar birthdayTime = Calendar.getInstance();
			birthdayTime.setTime(birthday);
			Calendar nowTime = Calendar.getInstance();
			nowTime.setTime(new Date());
			
			if (birthdayTime.after(nowTime)) {
				throw new IllegalArgumentException("Can't be born in the future");
			}
			
			Integer age = nowTime.get(Calendar.YEAR) - birthdayTime.get(Calendar.YEAR);
			if (nowTime.get(Calendar.DAY_OF_YEAR) < birthdayTime.get(Calendar.DAY_OF_YEAR)) {
				age -= 1;
			}
			
			return age;
		}else{
			throw new IllegalArgumentException("Can't be born in the future");
		}
		
	}
}
