package com.napoleon.life.core.enums;

public enum OpTypeEnum {

	QUERY_WITH_MONTH("month", "按月查询"),
	QUERY_WITH_WEEK("week", "按周查询"),
	QUERY_WITH_DATE_RANGE("date_range", "按时间段来查询");
	
	private String code;
	private String desc;
	
	private OpTypeEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String toDesc(String code) {
		for (OpTypeEnum category : OpTypeEnum.values()) {
			if (category.getCode().equals(code)) {
				return category.getDesc();
			}
		}
		return null;
	}
	
	public static OpTypeEnum toEnum(String code) {
        for (OpTypeEnum category : OpTypeEnum.values()) {
            if (category.getCode().equals(code)) {
                    return category;
            }
        }
        return null;
    }
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
