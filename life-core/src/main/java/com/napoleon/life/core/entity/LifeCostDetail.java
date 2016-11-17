package com.napoleon.life.core.entity;  
   
 import java.math.BigDecimal;  
 import java.util.Date;  
 
 import com.napoleon.life.common.persistence.Entity;
 
 public class LifeCostDetail implements Entity<Long>{  
   
 	/**
	 *  自增主键
	 */
    private Long id;  
    
 	/**
	 *  花费详情编号(Y_NO)
	 */
    private String costDetailNo;  
    
 	/**
	 *  用户编号
	 */
    private String userNo;  
    
 	/**
	 *  花费类型
	 */
    private String costType;  
    
 	/**
	 *  商品名称
	 */
    private String goodsName;  
    
 	/**
	 *  商品单价（元）
	 */
    private BigDecimal goodsPrice;  
    
 	/**
	 *  商品数量
	 */
    private BigDecimal quantity;  
    
 	/**
	 *  单位（斤，个）
	 */
    private String unit;  
    
 	/**
	 *  商品小记（元）
	 */
    private BigDecimal subTotal;  
    
 	/**
	 *  支付方式
	 */
    private String payWay;  
    
 	/**
	 *  描述
	 */
    private String description;  
    
 	/**
	 *  创建时间
	 */
    private Date createTime;  
    
 	/**
	 *  最后更新时间
	 */
    private Date updateTime;  
    
 	/**
	 *  年份
	 */
    private Integer year;  
    
 	/**
	 *  月份
	 */
    private Integer month;  
    
 	/**
	 *  天
	 */
    private Integer day;  
    
 	/**
	 *  所在的星期
	 */
    private Integer week;  
    
 
    public Long getId() {  
        return id;  
    }  
      
    public void setId(Long id) {  
        this.id = id;  
    }  
    public String getCostDetailNo() {  
        return costDetailNo;  
    }  
      
    public void setCostDetailNo(String costDetailNo) {  
        this.costDetailNo = costDetailNo;  
    }  
    public String getUserNo() {  
        return userNo;  
    }  
      
    public void setUserNo(String userNo) {  
        this.userNo = userNo;  
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
    public BigDecimal getSubTotal() {  
        return subTotal;  
    }  
      
    public void setSubTotal(BigDecimal subTotal) {  
        this.subTotal = subTotal;  
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
    public Date getCreateTime() {  
        return createTime;  
    }  
      
    public void setCreateTime(Date createTime) {  
        this.createTime = createTime;  
    }  
    public Date getUpdateTime() {  
        return updateTime;  
    }  
      
    public void setUpdateTime(Date updateTime) {  
        this.updateTime = updateTime;  
    }  
    public Integer getYear() {  
        return year;  
    }  
      
    public void setYear(Integer year) {  
        this.year = year;  
    }  
    public Integer getMonth() {  
        return month;  
    }  
      
    public void setMonth(Integer month) {  
        this.month = month;  
    }  
    public Integer getDay() {  
        return day;  
    }  
      
    public void setDay(Integer day) {  
        this.day = day;  
    }  
    public Integer getWeek() {  
        return week;  
    }  
      
    public void setWeek(Integer week) {  
        this.week = week;  
    }  
 }  