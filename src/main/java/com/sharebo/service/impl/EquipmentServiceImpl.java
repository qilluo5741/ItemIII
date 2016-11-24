package com.sharebo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.dto.EquipmentDto;
import com.sharebo.mapper.EquipmentMapper;
import com.sharebo.service.EquipmentService;
@Service
public class EquipmentServiceImpl implements EquipmentService {
	@Autowired
	private EquipmentMapper mapper;
	public List<EquipmentDto> getselectEquipmentlist(String commId) {
		return mapper.getselectEquipmentlist(commId);
	}
	public int updateequipmentName(String equipmentName,String equipmentNumber) {
		return mapper.updateequipmentName(equipmentName,equipmentNumber);
	}
	/**
	 * 取消收费亭
	 */
	@Override
	public int updateIsTollBooths(String equipmentNumber, Integer isTollBooths) {
		// TODO Auto-generated method stub
		return mapper.updateIsTollBooths(equipmentNumber, isTollBooths);
	}
}
