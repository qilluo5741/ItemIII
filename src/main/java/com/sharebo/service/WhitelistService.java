package com.sharebo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Whitelist;
import com.sharebo.entity.dto.WhitelistDto;

public interface WhitelistService {
	/**
	 * 添加白名单
	 * @return
	 */
	public int addWhitelist(WhitelistDto whitelist);
	/**
	 * 验证是否存在
	 * @param carNo
	 * @return
	 */
	public int valserwhitelistExists(String carNo,String commId);
	/**
	 * 修改白名单
	 * @param whitelist
	 * @return
	 */
	public int updatewhitelist(WhitelistDto whitelist);
	/**
	 * 删除白名单
	 * @param commId
	 * @param carNo
	 * @return
	 */
	public int delectisfailure(String whitelistId);
	/**
	 * 根据commId分页查询白名单
	 * @return
	 */
	public List<WhitelistDto> getselectWhitelist(Map<String,Object> map);
	/**
	 * 根据commId分页查询总数
	 * @param userid
	 * @return
	 */
	public int selectWhitelistCount(String commId,String carNo,String name,String address);
	/**
	 * 修改白名单失效
	 * @param whitelistId(0 正常   1  失效)
	 * @return
	 * @throws Exception 
	 */
	public boolean updateIsfailure(String whitelistId,Integer isfailure,String periodvalidity,Integer chargeTimeType) throws Exception;
}
