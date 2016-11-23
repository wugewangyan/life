package com.napoleon.life.core.entity;  
   
 import java.math.BigDecimal;
import java.sql.Timestamp;

import com.napoleon.life.common.persistence.Entity;
 
 public class LifeWeight implements Entity<Long>{  
   
	private static final long serialVersionUID = 1300104577063263709L;

	/**
	 *  自增主键
	 */
    private Long id;  
    
 	/**
	 *  用户名
	 */
    private String userNo;  
    
 	/**
	 *  体重重量（KG）
	 */
    private BigDecimal weight;  
    
 	/**
	 *  身体质量指标（体重公斤除以身高米的平方）
	 */
    private BigDecimal bmi;  
    
    /**
     * 静态代谢率 男：((10 * 体重［KG］) + (6.25 * 身高［CM］) - (5 * 年龄) ＋ 5)
     * 静态代谢率 女：((10 * 体重［KG］) + (6.25 * 身高［CM］) - (5 * 年龄) － 161)
     */
    private BigDecimal rmr;
    
 	/**
	 *  测量时间
	 */
    private Timestamp measurementTime;  
    
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
    
    public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
  
    public BigDecimal getWeight() {  
        return weight;  
    }  
      
    public void setWeight(BigDecimal weight) {  
        this.weight = weight;  
    }  
    public BigDecimal getBmi() {  
        return bmi;  
    }  
      
    public void setBmi(BigDecimal bmi) {  
        this.bmi = bmi;  
    }  
    public Timestamp getMeasurementTime() {  
        return measurementTime;  
    }  
      
    public void setMeasurementTime(Timestamp measurementTime) {  
        this.measurementTime = measurementTime;  
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

	public BigDecimal getRmr() {
		return rmr;
	}

	public void setRmr(BigDecimal rmr) {
		this.rmr = rmr;
	}  
 }  
