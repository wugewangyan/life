package com.napoleon.life.core.dto;

import com.napoleon.life.common.util.validator.Validator;
import com.napoleon.life.framework.base.BaseDto;

public class LifeWeightEditDto extends BaseDto {
	
	@Validator(desc = "体重ID", nullable = true, isInteger = true)
	private String weightId;

	@Validator(desc = "用户体重", nullable = false, isDouble = true)
	private String weight;
	
	@Validator(desc = "测量时间", nullable = true, isDate = true)
	private String measurementTime;
	
	private String key;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWeightId() {
		return weightId;
	}

	public void setWeightId(String weightId) {
		this.weightId = weightId;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getMeasurementTime() {
		return measurementTime;
	}

	public void setMeasurementTime(String measurementTime) {
		this.measurementTime = measurementTime;
	}
}
