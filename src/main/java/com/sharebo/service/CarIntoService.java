package com.sharebo.service;

import java.util.Map;

public interface CarIntoService {
	/**
	 * 得到当前设备是否进出
	 * 
	 * @param serialno
	 * @return
	 */
	public int getCarIsInout(String serialno);

	/**
	 * 验证车辆是否为白名单
	 * 
	 * @param carNo
	 * @return
	 */
	public boolean valCarIsWhiteList(String carNo, String serialno);

	// 通过设备得到合作方
	public Map<String, Object> getPartnerBySerialno(String serialno);
}
