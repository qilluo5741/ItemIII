package com.sharebo.mapper;


import org.apache.ibatis.annotations.Param;


public interface CommunityMapper {
	/**
	 * 修改车位总数
	 * @param parkCount
	 * @param commId
	 * @return
	 */
	public int updateparkCountBycommId(@Param("psCount")int psCount,@Param("commId")String commId);
	/**
	 * 查询车位总数
	 * @param commId
	 * @return
	 */
	public int getPsCountByCommId(@Param("commId")String commId);
	/**
	 * 查询小区父级
	 * @param commId
	 * @return
	 */
	public String getCommParent(@Param("commId")String commId);
	/**
	 * 查询小区是否允许外来车辆进入
	 * @param commId
	 * @return
	 */
	public int getisBanOnForeignCars(@Param("commId")String commId);
	/**
	 * 修改小区允许外来车辆进入的状态
	 * @param commId
	 * @return
	 */
	public int updateisBanOnForeignCars(@Param("commId")String commId,@Param("isBanOnForeignCars")Integer isBanOnForeignCars);
}
