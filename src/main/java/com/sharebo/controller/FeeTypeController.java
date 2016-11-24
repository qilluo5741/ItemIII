package com.sharebo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.FeeType;
import com.sharebo.service.FeeTypeService;
import com.sharebo.util.ResultDto;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("fee")
public class FeeTypeController {
	@Autowired
	private FeeTypeService service;
	/**
	 * 修改收费模式
	 * @param commId
	 * @param fee
	 * @return
	 */
	@RequestMapping("updateFeeType")
	@ResponseBody
	public ResultDto updateFeeType(String commId,FeeType fee){
		if(commId==null){
			return new ResultDto(3001,"请求参数不能为空");
		}
	    fee.setMoney(fee.getMoney());
		fee.setMaxMoney(fee.getMaxMoney());
		fee.setFeeModel(fee.getFeeModel());
		fee.setFreeMin(fee.getFreeMin());
		String feeId=service.getFeeIdByCommId(commId);
		System.out.println(feeId);
		JSONObject feetype=JSONObject.fromObject(fee);
		String feeModelContext=feetype.toString();
		System.out.println(feeModelContext);
		System.out.println(feetype);
		if(service.updateFeeType(feeId, feeModelContext)>0){
			return new ResultDto(200,"成功修改收费模式");
		}
		return new ResultDto(3002,"修改收费模式失败");
	}
	/**
	 * 查询收费模式
	 * @param commId
	 * @return
	 */
	@RequestMapping("getFeeType")
	@ResponseBody
	public ResultDto getFeeType(String commId){
		if(commId==null){
			return new ResultDto(3001,"请求参数为空");
		}
		if(service.getFeeType(commId)!=null){
			return new ResultDto(200,"查询成功",service.getFeeType(commId));
		}
		return new ResultDto(3004,"查询失败");
	}
	/**
	 * 修改车辆历史车牌号
	 * @param carNo
	 * @param vehicleId
	 * @return
	 */
	@RequestMapping("updateCarNo")
	@ResponseBody
	public ResultDto updateCarNoByVehicleId(String carNo,String vehicleId){
		if(carNo==null||vehicleId==null){
			return new ResultDto(3001,"请求参数不能为空");
		}
		if(service.updateCarNoByVehicleId(vehicleId, carNo)>0){
			return new ResultDto(200,"成功修改车牌号");
		}
		return new ResultDto(3002,"修改车牌号失败");
	}
	/**
	 * 确认收费
	 * @param vehicleId
	 * @param carType  车辆类型(3：其他车辆  0：外来车辆  1：物业车辆  2：军用车辆)
	 * @param paidInFee  实收价格
	 * @param payType   支付类型(0:未支付，1：线下收费，2：其他支付待定)
	 * @return
	 */
	@RequestMapping("confirmCharge")
	@ResponseBody
	public ResultDto comfirmCharge(String vehicleId,Integer carType,Double paidInFee,Integer payType){
		if(vehicleId==null||carType==null||paidInFee==null){
			return new ResultDto(3001,"请求参数不能为空");
		}
		payType=1;
		if(service.confirmCharge(payType, paidInFee, carType, vehicleId)>0){
			return new ResultDto(200,"收费成功");
		}
		return new ResultDto(3002,"收费失败");
	}
}
