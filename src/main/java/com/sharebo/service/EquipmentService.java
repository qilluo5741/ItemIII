package com.sharebo.service;

import java.util.List;


import com.sharebo.entity.dto.EquipmentDto;

public interface EquipmentService {
	public List<EquipmentDto> getselectEquipmentlist(String commId);
	public int updateequipmentName(String equipmentName,String equipmentNumber);
	/**
	 * 取消收费亭
	 * @param equipmentNumber
	 * @param isTollBooths
	 * @return
	 */
	public int updateIsTollBooths(String equipmentNumber,Integer isTollBooths);
}
