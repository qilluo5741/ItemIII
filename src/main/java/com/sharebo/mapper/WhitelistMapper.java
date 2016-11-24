package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Whitelist;
import com.sharebo.entity.dto.WhitelistDto;
import com.sun.org.glassfish.gmbal.ParameterNames;

public interface WhitelistMapper {
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
	public int valserwhitelistExists(@Param("carNo") String carNo,@Param("commId")String commId);
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
	public int delectisfailure(@Param("whitelistId")String whitelistId);
	/**
	 * 根据commId分页查询白名单
	 * @param map
	 * @return
	 */
	public List<WhitelistDto> getselectWhitelist(Map<String,Object> map);
	/**
	 * 根据commId分页查询总数
	 * @param userid
	 * @return
	 */
	public int selectWhitelistCount(@Param("commId")String commId,@Param("carNo")String carNo,@Param("name")String name,@Param("address")String address);
	/**
	 * 修改白名单有效期
	 * @param commId
	 * @param carNo
	 * @param periodvalidity
	 * @param name
	 * @param address
	 * @param phone
	 * @param chargeTimeType
	 * @return
	 */
	public int updatePeriodvalidity(@Param("commId")String commId,@Param("carNo")String carNo,@Param("periodvalidity")String periodvalidity,@Param("name")String name,@Param("address")String address,@Param("phone")String phone,@Param("chargeTimeType")Integer chargeTimeType);
	/**
	 * 修改白名单失效
	 * @param whitelistId(0 正常   1  失效)
	 * @return
	 */
	public int updateIsfailure(@Param("whitelistId")String whitelistId,@Param("isfailure")Integer isfailure);
	/**
	 * 查询单个白名单信息
	 * @param whitelistId
	 * @return
	 */
	public WhitelistDto getWhitelistInfo(@Param("whitelistId")String whitelistId);
}
