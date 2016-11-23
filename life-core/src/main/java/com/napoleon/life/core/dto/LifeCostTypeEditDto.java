package com.napoleon.life.core.dto;  
   
 import com.napoleon.life.common.util.validator.Validator;  
 import com.napoleon.life.framework.base.BaseDto;  
 
 public class LifeCostTypeEditDto extends BaseDto{  
   
    @Validator(desc = "主键ID", nullable = true, isLong = true)
    private String entityId;
   
 	/**
	 *  花费类型
	 */
	@Validator(desc = "花费类型", nullable = true)
    private String costType;  
 	/**
	 *  描述
	 */
	@Validator(desc = "描述", nullable = true)
    private String description;  
 
 	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
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