package com.sharebo.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.dto.EquipmentDto;
import com.sharebo.service.EquipmentService;
import com.sharebo.util.ResultDto;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 *
 */
@Controller
@RequestMapping("equip")
public class EquipmentController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EquipmentService service;
	/**
	 * equip/equipment.do?commId=
	 * @param commId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("equipment")
	public ResultDto equipment(String commId){
		System.out.println(commId);
		try {
			if(commId==null){
				return new ResultDto(2002,"�������Ϊ�գ�");
			}
			StringBuffer sb = new StringBuffer();
		    String[] temp = commId.split(",");
		    for (int i = 0; i < temp.length; i++) {
			    sb.append("'" + temp[i] + "',");
		    }
		    String result = sb.toString().substring(0,sb.length()-1);
			List<EquipmentDto> list=service.getselectEquipmentlist(result);
			return new ResultDto(200,"�ɹ���",list);
		} catch (Exception e) {
			log.error("��ѯ�豸�쳣");
		}
		return new ResultDto(2003,"���޲���");
	}
	/**
	 * equip/updatequiName.do?equipmentNumber=111111&equipmentName=�豸���֣���������豸�����2132��
	 * �޸�equipmentName
	 * @param equipmentNumber
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updatequiName")
	public ResultDto equipmentName(String equipmentNumber,String equipmentName){
		try {
			if(equipmentNumber==null && equipmentName==null){
				return new ResultDto(2001,"�����������Ϊ�գ�");
			}
			if(service.updateequipmentName(equipmentName,equipmentNumber)>=0){
				return new ResultDto(200,"�޸ĳɹ���");
			}
		} catch (Exception e) {
			log.error("�޸��豸�����쳣");
		}
		return new ResultDto(2003,"�޸�ʧ�ܣ������������쳣��");
	}
	/**
	 * ȡ��/�����շ�ͤ
	 * @param equipmentNumber
	 * @param isTollBooths
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateIsTollBooths")
	public ResultDto updateIsTollBooths(String equipmentNumber,Integer isTollBooths){
		if(equipmentNumber==null){
			return new ResultDto(3001,"�����������Ϊ��");
		}
		if(service.updateIsTollBooths(equipmentNumber, isTollBooths)>0){
			return new ResultDto(200,"���óɹ�");
		}
		return new ResultDto(3002,"����ʧ��");
	}
}
