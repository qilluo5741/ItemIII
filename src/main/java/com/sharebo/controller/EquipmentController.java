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
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
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
				return new ResultDto(2002,"请求参数为空！");
			}
			StringBuffer sb = new StringBuffer();
		    String[] temp = commId.split(",");
		    for (int i = 0; i < temp.length; i++) {
			    sb.append("'" + temp[i] + "',");
		    }
		    String result = sb.toString().substring(0,sb.length()-1);
			List<EquipmentDto> list=service.getselectEquipmentlist(result);
			return new ResultDto(200,"成功！",list);
		} catch (Exception e) {
			log.error("查询设备异常");
		}
		return new ResultDto(2003,"暂无参数");
	}
	/**
	 * equip/updatequiName.do?equipmentNumber=111111&equipmentName=设备名字（用来标记设备出入口2132）
	 * 修改equipmentName
	 * @param equipmentNumber
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updatequiName")
	public ResultDto equipmentName(String equipmentNumber,String equipmentName){
		try {
			if(equipmentNumber==null && equipmentName==null){
				return new ResultDto(2001,"请求参数不能为空！");
			}
			if(service.updateequipmentName(equipmentName,equipmentNumber)>=0){
				return new ResultDto(200,"修改成功！");
			}
		} catch (Exception e) {
			log.error("修改设备名字异常");
		}
		return new ResultDto(2003,"修改失败！你的请求参数异常！");
	}
	/**
	 * 取消/设置收费亭
	 * @param equipmentNumber
	 * @param isTollBooths
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateIsTollBooths")
	public ResultDto updateIsTollBooths(String equipmentNumber,Integer isTollBooths){
		if(equipmentNumber==null){
			return new ResultDto(3001,"请求参数不能为空");
		}
		if(service.updateIsTollBooths(equipmentNumber, isTollBooths)>0){
			return new ResultDto(200,"设置成功");
		}
		return new ResultDto(3002,"设置失败");
	}
}
