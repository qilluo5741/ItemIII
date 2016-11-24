package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharebo.entity.Whitelist;
import com.sharebo.entity.dto.WhitelistDto;
import com.sharebo.mapper.WhitelistMapper;
import com.sharebo.service.WhitelistService;
@Service
@Transactional(rollbackFor = Exception.class)
public class WhitelistServiceImpl implements WhitelistService {
	@Autowired
	private WhitelistMapper mapper;
	public int addWhitelist(WhitelistDto whitelist) {
		return mapper.addWhitelist(whitelist);
	}
	public int valserwhitelistExists(String carNo,String commId) {
		return mapper.valserwhitelistExists(carNo,commId);
	}
	public int updatewhitelist(WhitelistDto whitelist) {
		return mapper.updatewhitelist(whitelist);
	}
	public int delectisfailure(String whitelistId) {
		return mapper.delectisfailure(whitelistId);
	}
	public List<WhitelistDto> getselectWhitelist(Map<String, Object> map) {
		return mapper.getselectWhitelist(map);
	}
	public int selectWhitelistCount(String commId,String carNo,String name,String address) {
		return mapper.selectWhitelistCount(commId,carNo,name,address);
	}
	@Override
	public boolean updateIsfailure(String whitelistId,Integer isfailure,String periodvalidity,Integer chargeTimeType) throws Exception {
		//得到白名单 信息
		WhitelistDto whitelist=mapper.getWhitelistInfo(whitelistId);
		if(whitelist==null){//该白名单不存在
			return false;
		}
		whitelist.setPeriodvalidity(periodvalidity);
		whitelist.setChargeTimeType(chargeTimeType);
		if(mapper.updateIsfailure(whitelistId, 1)<0){
			return false;
		}
		//判断重新添加白名单是否成功
	     if(mapper.addWhitelist(whitelist)<=0){
	    	 throw new Exception("添加白名单异常!");
	     }
		return true;
	}
}
