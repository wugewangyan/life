package com.napoleon.life.core.dto;

import com.napoleon.life.common.util.validator.Validator;
import com.napoleon.life.framework.base.BaseDto;

public class LifeRunYearDetailDto extends BaseDto {

	/**
	 * 年份
	 */
	@Validator(desc = "年份", nullable = false)
	private Integer year;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
