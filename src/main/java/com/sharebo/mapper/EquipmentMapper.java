package com.sharebo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sharebo.entity.dto.EquipmentDto;

public interface EquipmentMapper {
	public List<EquipmentDto> getselectEquipmentlist(@Param("commId")String commId);
	public int updateequipmentName(@Param("equipmentName")String equipmentName,@Param("equipmentNumber")String equipmentNumber);
	/**
	 * 取消收费亭
	 * @param equipmentNumber
	 * @param isTollBooths
	 * @return
	 */
	public int updateIsTollBooths(@Param("equipmentNumber")String equipmentNumber,@Param("isTollBooths")Integer isTollBooths);
}
