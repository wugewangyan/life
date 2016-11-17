package com.napoleon.life.core.dto;

import com.napoleon.life.common.util.validator.Validator;
import com.napoleon.life.framework.base.BaseDto;

public class LifeDeleteDto extends BaseDto {
	
	@Validator(desc = "业务主键ID", nullable = false)
	private String entityId;

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
}
