package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.mapper.FeeTypeMapper;
import com.sharebo.service.FeeTypeService;


@Service
public class FeeTypeServiceImpl implements FeeTypeService {
	@Autowired
	private FeeTypeMapper mapper;

	@Override
	public String getFeeIdByCommId(String commId) {
		// TODO Auto-generated method stub
		return mapper.getFeeIdByCommId(commId);
	}

	@Override
	public int updateFeeType(String feeId,String feeModelContext) {
		// TODO Auto-generated method stub
		return mapper.updateFeeType(feeId, feeModelContext);
	}

	@Override
	public String getFeeType(String commId) {
		// TODO Auto-generated method stub
		return mapper.getFeeType(commId);
	}

	@Override
	public int updateCarNoByVehicleId(String vehicleId, String carNo) {
		// TODO Auto-generated method stub
		return mapper.updateCarNoByVehicleId(vehicleId, carNo);
	}
	@Override
	public int confirmCharge(Integer payType, Double paidInFee, Integer carType, String vehicleId) {
		// TODO Auto-generated method stub
		return mapper.confirmCharge(payType, paidInFee, carType, vehicleId);
	}
}
