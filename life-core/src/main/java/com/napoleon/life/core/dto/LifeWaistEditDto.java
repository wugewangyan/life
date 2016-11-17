package com.napoleon.life.core.dto;

import com.napoleon.life.common.util.validator.Validator;
import com.napoleon.life.framework.base.BaseDto;

public class LifeWaistEditDto extends BaseDto {

	@Validator(desc = "腰围ID", nullable = true, isInteger = true)
	private String waistId;

	@Validator(desc = "用户腰围", nullable = false, isDouble = true)
	private String waist;
	
	@Validator(desc = "测量时间", nullable = false, isDate = true)
	private String measurementTime;

	public String getWaistId() {
		return waistId;
	}

	public void setWaistId(String waistId) {
		this.waistId = waistId;
	}

	public String getWaist() {
		return waist;
	}

	public void setWaist(String waist) {
		this.waist = waist;
	}

	public String getMeasurementTime() {
		return measurementTime;
	}

	public void setMeasurementTime(String measurementTime) {
		this.measurementTime = measurementTime;
	}
}
