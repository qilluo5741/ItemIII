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
			return new ResultDto(2001,"请求参数不能为空！");
		}
		try {
			if(service.updateparkCountBycommId(psCount, commId)>=0){
				return new ResultDto(200,"修改车位总数成功！");
			}
		} catch (Exception e) {
			log.error("修改车位总数异常");
		}
		return new ResultDto(2003,"修改失败！车位总数异常！");
	}
	/**
	 * 查询小区剩余车位总数
	 * @param commId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCommPsCount")
	public ResultDto getCommPsCount(String commId){
		if(commId==null){
			return new ResultDto(3001,"请求参数不能为空");
		}
		//得到小区剩余车位数
		int psCount=service.getPsCountByCommId(commId);
		return new ResultDto(200,"查询成功",psCount);
	}
	/**
	 * 查询小区是否允许外来车辆进入
	 * @param commId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getisBanOnForeignCars")
	public ResultDto getisBanOnForeignCars(String commId){
		if(commId==null){
			return new ResultDto(3001,"请求参数不能为空");
		}
		int isBanOnForeignCars=service.getisBanOnForeignCars(commId);
		return new ResultDto(200,"查询成功",isBanOnForeignCars);
	}
	/**
	 * 修改小区是否允许外来车辆进入的状态
	 * @param commId
	 * @param isBanOnForeignCars
	 * @return
	 */
	@RequestMapping("updateisBanOnForeignCars")
	@ResponseBody
	public ResultDto updateisBanOnForeignCars(String commId,Integer isBanOnForeignCars){
		if(commId==null||isBanOnForeignCars==null){
			return new ResultDto(3001,"请求参数不能为空");
		}
		if(service.updateisBanOnForeignCars(commId, isBanOnForeignCars)>0){
			return new ResultDto(200,"修改成功");
		}
		return new ResultDto(3002,"修改失败");
	}
}
