package com.sharebo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.mapper.CommunityMapper;
import com.sharebo.service.CommunityService;
@Service
public class CommunityServiceImpl implements CommunityService {
	@Autowired
	private CommunityMapper mapper;
	
	@Override
	public int updateparkCountBycommId(int psCount, String commId) {
		return mapper.updateparkCountBycommId(psCount, commId);
	}

	@Override
	public int getPsCountByCommId(String commId) {
		return mapper.getPsCountByCommId(commId);
	}

	@Override
	public String getCommParent(String commId) {
		return mapper.getCommParent(commId);
	}

	@Override
	public int getisBanOnForeignCars(String commId) {
		// TODO Auto-generated method stub
		return mapper.getisBanOnForeignCars(commId);
	}

	@Override
	public int updateisBanOnForeignCars(String commId, Integer isBanOnForeignCars) {
		// TODO Auto-generated method stub
		return mapper.updateisBanOnForeignCars(commId, isBanOnForeignCars);
	}
}
