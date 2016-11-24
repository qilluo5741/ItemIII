package com.sharebo.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.mapper.CarIntoMapper;
import com.sharebo.service.CarIntoService;

@Service
public class CarIntoServiceImpl implements CarIntoService {
	@Autowired
	private CarIntoMapper mapper;

	public int getCarIsInout(String serialno) {
		return mapper.getCarIsInout(serialno);// 1 £º½ø 2£º³ö
	}
	public boolean valCarIsWhiteList(String carNo,String serialno) {
		return mapper.valCarIsWhiteList(carNo,serialno)>0?true:false;
	}
	public Map<String, Object> getPartnerBySerialno(String serialno) {
		// TODO Auto-generated method stub
		return mapper.getPartnerBySerialno(serialno);
	}

}
