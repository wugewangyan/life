package com.napoleon.life.core.dto;

import com.napoleon.life.common.util.StringUtil;
import com.napoleon.life.common.util.validator.Validator;
import com.napoleon.life.core.enums.OpTypeEnum;
import com.napoleon.life.framework.base.BaseDto;

public class LifeQueryNoWeekInfoDto extends BaseDto{
	
	@Validator(desc = "操作类型", nullable = false, enumScope = OpTypeEnum.class)
	private String opType;
	
	@Validator(desc = "查询开始时间", nullable = false)
	private Long queryStartTime;
	
	@Validator(desc = "查询结束时间", nullable = true, dependesOn = "validTime")
	private Long queryEndTime;
	
	public boolean validTime(){
		if(OpTypeEnum.QUERY_WITH_DATE_RANGE.getCode().equals(this.opType)){
			if(StringUtil.notEmpty(this.queryEndTime)){
				return true;
			}else{
				return false;
			}
		}else if(OpTypeEnum.QUERY_WITH_WEEK.getCode().equals(this.opType)){
			return false;
		}
		
		return true;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public Long getQueryStartTime() {
		return queryStartTime;
	}

	public void setQueryStartTime(Long queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	public Long getQueryEndTime() {
		return queryEndTime;
	}

	public void setQueryEndTime(Long queryEndTime) {
		this.queryEndTime = queryEndTime;
	}
}
