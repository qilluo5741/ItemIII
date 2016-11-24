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
	 * �޸��շ�ģʽ
	 * @param commId
	 * @param fee
	 * @return
	 */
	@RequestMapping("updateFeeType")
	@ResponseBody
	public ResultDto updateFeeType(String commId,FeeType fee){
		if(commId==null){
			return new ResultDto(3001,"�����������Ϊ��");
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
			return new ResultDto(200,"�ɹ��޸��շ�ģʽ");
		}
		return new ResultDto(3002,"�޸��շ�ģʽʧ��");
	}
	/**
	 * ��ѯ�շ�ģʽ
	 * @param commId
	 * @return
	 */
	@RequestMapping("getFeeType")
	@ResponseBody
	public ResultDto getFeeType(String commId){
		if(commId==null){
			return new ResultDto(3001,"�������Ϊ��");
		}
		if(service.getFeeType(commId)!=null){
			return new ResultDto(200,"��ѯ�ɹ�",service.getFeeType(commId));
		}
		return new ResultDto(3004,"��ѯʧ��");
	}
	/**
	 * �޸ĳ�����ʷ���ƺ�
	 * @param carNo
	 * @param vehicleId
	 * @return
	 */
	@RequestMapping("updateCarNo")
	@ResponseBody
	public ResultDto updateCarNoByVehicleId(String carNo,String vehicleId){
		if(carNo==null||vehicleId==null){
			return new ResultDto(3001,"�����������Ϊ��");
		}
		if(service.updateCarNoByVehicleId(vehicleId, carNo)>0){
			return new ResultDto(200,"�ɹ��޸ĳ��ƺ�");
		}
		return new ResultDto(3002,"�޸ĳ��ƺ�ʧ��");
	}
	/**
	 * ȷ���շ�
	 * @param vehicleId
	 * @param carType  ��������(3����������  0����������  1����ҵ����  2�����ó���)
	 * @param paidInFee  ʵ�ռ۸�
	 * @param payType   ֧������(0:δ֧����1�������շѣ�2������֧������)
	 * @return
	 */
	@RequestMapping("confirmCharge")
	@ResponseBody
	public ResultDto comfirmCharge(String vehicleId,Integer carType,Double paidInFee,Integer payType){
		if(vehicleId==null||carType==null||paidInFee==null){
			return new ResultDto(3001,"�����������Ϊ��");
		}
		payType=1;
		if(service.confirmCharge(payType, paidInFee, carType, vehicleId)>0){
			return new ResultDto(200,"�շѳɹ�");
		}
		return new ResultDto(3002,"�շ�ʧ��");
	}
}
