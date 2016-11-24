package com.sharebo.service;


public interface CommunityService {
	/**
	 * 修改车位总数
	 * @param parkCount
	 * @param commId
	 * @return
	 */
	public int updateparkCountBycommId(int psCount,String commId);
	/**
	 * 查询车位总数
	 * @param commId
	 * @return
	 */
	public int getPsCountByCommId(String commId);
	/**
	 * 查询小区父级
	 * @param commId
	 * @return
	 */
	public String getCommParent(String commId);
	/**
	 * 查询小区是否允许外来车辆进入
	 * @param commId
	 * @return
	 */
	public int getisBanOnForeignCars(String commId);
	/**
	 * 修改小区允许外来车辆进入的状态
	 * @param commId
	 * @return
	 */
	public int updateisBanOnForeignCars(String commId,Integer isBanOnForeignCars);
}
