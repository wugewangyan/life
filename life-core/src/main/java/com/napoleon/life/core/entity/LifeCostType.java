package com.napoleon.life.core.entity;  
   
 import java.util.Date;  
 
 import com.napoleon.life.common.persistence.Entity;
 
 public class LifeCostType implements Entity<Long>{  
   
 	/**
	 *  自增主键
	 */
    private Long id;  
    
 	/**
	 *  花费类型编号
	 */
    private String costTypeNo;
    
    /**
     * 花费类型
     */
    private String costType;
    
    /**
     * 描述
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
    
 
    public Long getId() {  
        return id;  
    }  
      
    public void setId(Long id) {  
        this.id = id;  
    }  
    public String getCostTypeNo() {  
        return costTypeNo;  
    }  
      
    public void setCostTypeNo(String costTypeNo) {  
        this.costTypeNo = costTypeNo;  
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

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 
 }  