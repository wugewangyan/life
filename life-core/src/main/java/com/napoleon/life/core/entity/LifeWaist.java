package com.napoleon.life.core.entity;  
   
 import java.math.BigDecimal;
import java.sql.Timestamp;

import com.napoleon.life.common.persistence.Entity;
 
 public class LifeWaist implements Entity<Long>{  
   
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
	 *  腰围（CM）
	 */
    private BigDecimal waist;  
    
 	/**
	 *  体脂率 男：((腰围(CM)*0.74) - [体重(KG) * 0.082 + 44.74]) / 体重(KG) * 100%
	 *  体脂率 女：((腰围(CM)*0.74) - [体重(KG) * 0.082 + 34.89]) / 体重(KG) * 100%
	 */
    private BigDecimal bfr;  
    
    /**
     * 腰围身高比(腰围（CM）／身高（CM） * 100%)
     */
    private BigDecimal whr;
    
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

	public BigDecimal getWaist() {
		return waist;
	}

	public void setWaist(BigDecimal waist) {
		this.waist = waist;
	}

	public BigDecimal getBfr() {
		return bfr;
	}

	public void setBfr(BigDecimal bfr) {
		this.bfr = bfr;
	}

	public BigDecimal getWhr() {
		return whr;
	}

	public void setWhr(BigDecimal whr) {
		this.whr = whr;
	}
 }  