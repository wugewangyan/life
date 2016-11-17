package com.napoleon.life.core.facade.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napoleon.life.common.util.StringUtil;
import com.napoleon.life.core.dto.LifeCostDetailEditDto;
import com.napoleon.life.core.dto.LifeCostDetailQueryDto;
import com.napoleon.life.core.dto.LifeDeleteDto;
import com.napoleon.life.core.entity.LifeCostDetail;
import com.napoleon.life.core.facade.LifeCostDetailFacade;
import com.napoleon.life.core.service.LifeCostDetailService;
import com.napoleon.life.core.util.Constants;
import com.napoleon.life.exception.CommonResultCode;
import com.napoleon.life.framework.result.CommonRltUtil;
import com.napoleon.life.user.service.CommonSerialNoService;


@Service
public class LifeCostDetailFacadeImpl implements LifeCostDetailFacade{

	private static final Logger logger = LoggerFactory.getLogger(LifeCostDetailFacadeImpl.class);
	
	@Autowired
	private LifeCostDetailService costDetailService;
	
	@Autowired
	private CommonSerialNoService serialNoService;

	@Override
	public String editCostDetailInfo(LifeCostDetailEditDto editDto) {
		LifeCostDetail costDetailInfo = null;
		if(StringUtil.notEmpty(editDto.getDetailId())){
			costDetailInfo = costDetailService.findByEntityId(Long.valueOf(editDto.getDetailId()));
			if(costDetailInfo == null){
				return CommonRltUtil.createCommonRltToString(CommonResultCode.COST_ORDER_INFO_NOT_FOUND);
			}else if(!editDto.getUserNo().equals(costDetailInfo.getUserNo())){
				return CommonRltUtil.createCommonRltToString(CommonResultCode.NOT_AUTH_TO_EDIT);
			}
		}else{
			costDetailInfo = new LifeCostDetail();
			costDetailInfo.setUserNo(editDto.getUserNo());
			costDetailInfo.setCreateTime(new Timestamp(new Date().getTime()));
		}

		costDetailInfo.setCostDetailNo(serialNoService.getSerialNo(Constants.COST_DETAIL_NO));
		costDetailInfo.setCostType(editDto.getCostType());
		costDetailInfo.setGoodsName(editDto.getGoodsName());
		costDetailInfo.setGoodsPrice(editDto.getGoodsPrice());
		costDetailInfo.setQuantity(editDto.getQuantity());
		costDetailInfo.setUnit(editDto.getUnit());
		
		BigDecimal subTotal = costDetailInfo.getQuantity().multiply(costDetailInfo.getGoodsPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
		costDetailInfo.setSubTotal(subTotal);
		costDetailInfo.setPayWay(editDto.getPayWay());
		costDetailInfo.setDescription(editDto.getDescription());
		costDetailInfo.setUpdateTime(new Timestamp(new Date().getTime()));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(costDetailInfo.getUpdateTime());
		costDetailInfo.setYear(calendar.get(Calendar.YEAR));
		costDetailInfo.setMonth(calendar.get(Calendar.MONTH) + 1);
		costDetailInfo.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		costDetailInfo.setWeek(calendar.get(Calendar.WEEK_OF_YEAR));
		
		this.costDetailService.insertOrUpdate(costDetailInfo);
		return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS);
	}
	
	@Override
	public String deleteCostDetailInfo(LifeDeleteDto deleteInfo) {
		LifeCostDetail detailInfo = this.costDetailService.findByCostDetailNo(deleteInfo.getEntityId());
		if(detailInfo == null){
			return CommonRltUtil.createCommonRltToString(CommonResultCode.COST_ORDER_INFO_NOT_FOUND);
		}else if(!deleteInfo.getUserNo().equals(detailInfo.getUserNo())){
			return CommonRltUtil.createCommonRltToString(CommonResultCode.NOT_AUTH_TO_EDIT);
		}
		
		this.costDetailService.delete(detailInfo.getId());
		return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS);
	}

	
	
	@Override
	public String getCostDetailInfo(LifeCostDetailQueryDto queryDto) {

		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtil.notEmpty(queryDto.getCostDetailNo())){
			params.put("cost_detail_no", queryDto.getCostDetailNo());
		}
		if(StringUtil.notEmpty(queryDto.getCostType())){
			params.put("cost_type", queryDto.getCostType());
		}
		if(StringUtil.notEmpty(queryDto.getGoodsName())){
			params.put("goods_name", "%" + queryDto.getGoodsName() + "%");
		}
		if(StringUtil.notEmpty(queryDto.getPayWay())){
			params.put("pay_way", queryDto.getPayWay());
		}
		if(StringUtil.notEmpty(queryDto.getQueryStartTime())){
			params.put("start_time", new Timestamp(queryDto.getQueryStartTime()));
		}
		if(StringUtil.notEmpty(queryDto.getQueryEndTime())){
			params.put("end_time", new Timestamp(queryDto.getQueryEndTime()));
		}
			
		List<LifeCostDetail> result = this.costDetailService.findCostDetail(params);
		return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS, result);
	}
}
