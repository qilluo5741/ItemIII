package com.sharebo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.service.CommunityService;
import com.sharebo.util.ResultDto;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("community")
public class CommunityControlller {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommunityService service;
	/**
	 * community/updateparkCount.do?parkCount=100&commId=24602080450744070
	 * @param parkCount
	 * @param commId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateparkCount")
	public ResultDto equipmentName(int psCount,String commId){
		if(commId==null && psCount==0){
			return new ResultDto(2001,"�����������Ϊ�գ�");
		}
		try {
			if(service.updateparkCountBycommId(psCount, commId)>=0){
				return new ResultDto(200,"�޸ĳ�λ�����ɹ���");
			}
		} catch (Exception e) {
			log.error("�޸ĳ�λ�����쳣");
		}
		return new ResultDto(2003,"�޸�ʧ�ܣ���λ�����쳣��");
	}
	/**
	 * ��ѯС��ʣ�೵λ����
	 * @param commId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCommPsCount")
	public ResultDto getCommPsCount(String commId){
		if(commId==null){
			return new ResultDto(3001,"�����������Ϊ��");
		}
		//�õ�С��ʣ�೵λ��
		int psCount=service.getPsCountByCommId(commId);
		return new ResultDto(200,"��ѯ�ɹ�",psCount);
	}
	/**
	 * ��ѯС���Ƿ�����������������
	 * @param commId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getisBanOnForeignCars")
	public ResultDto getisBanOnForeignCars(String commId){
		if(commId==null){
			return new ResultDto(3001,"�����������Ϊ��");
		}
		int isBanOnForeignCars=service.getisBanOnForeignCars(commId);
		return new ResultDto(200,"��ѯ�ɹ�",isBanOnForeignCars);
	}
	/**
	 * �޸�С���Ƿ������������������״̬
	 * @param commId
	 * @param isBanOnForeignCars
	 * @return
	 */
	@RequestMapping("updateisBanOnForeignCars")
	@ResponseBody
	public ResultDto updateisBanOnForeignCars(String commId,Integer isBanOnForeignCars){
		if(commId==null||isBanOnForeignCars==null){
			return new ResultDto(3001,"�����������Ϊ��");
		}
		if(service.updateisBanOnForeignCars(commId, isBanOnForeignCars)>0){
			return new ResultDto(200,"�޸ĳɹ�");
		}
		return new ResultDto(3002,"�޸�ʧ��");
	}
}
