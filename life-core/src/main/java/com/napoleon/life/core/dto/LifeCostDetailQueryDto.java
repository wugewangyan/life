package com.napoleon.life.core.dto;

import com.napoleon.life.common.util.validator.Validator;
import com.napoleon.life.framework.base.BaseDto;

public class LifeCostDetailQueryDto extends BaseDto {

	private String costDetailNo;
	
	@Validator(desc = "花费类型", nullable = true)
	private String costType;
	
	@Validator(desc = "商品名称", nullable = true)
	private String goodsName;
	
	private String payWay;
	
	@Validator(desc = "查询开始时间", nullable = true)
	private Long queryStartTime;
	
	@Validator(desc = "查询结束时间", nullable = true)
	private Long queryEndTime; 

	public String getCostDetailNo() {
		return costDetailNo;
	}

	public void setCostDetailNo(String costDetailNo) {
		this.costDetailNo = costDetailNo;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
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
