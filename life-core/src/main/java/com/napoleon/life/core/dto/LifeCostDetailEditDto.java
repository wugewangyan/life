package com.napoleon.life.core.dto;

import java.math.BigDecimal;

import com.napoleon.life.common.util.validator.Validator;
import com.napoleon.life.framework.base.BaseDto;

public class LifeCostDetailEditDto extends BaseDto {

	@Validator(desc = "花费ID", nullable = true, isLong = true)
	private String detailId;

	@Validator(desc = "花费类型", nullable = false)
	private String costType;
	
	@Validator(desc = "商品名称", nullable = false)
	private String goodsName;
	
	private BigDecimal goodsPrice;
	
	private BigDecimal quantity;
	
	private String unit;
	
	private String payWay;
	
	private String description;

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
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

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
