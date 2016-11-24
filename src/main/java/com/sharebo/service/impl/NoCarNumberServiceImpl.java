package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONObject;

import com.sharebo.entity.InOut_Info;
import com.sharebo.entity.ResInfo;
import com.sharebo.entity.SendMessageInfo;
import com.sharebo.service.CarDealService;
import com.sharebo.service.IMQService;
/**
 * 没有车牌
 * @author niewei
 *
 */
public class NoCarNumberServiceImpl implements CarDealService {
	@Autowired
	private IMQService service;
	//处理业务
	public synchronized ResInfo deal(InOut_Info info) {
		ResInfo res=new ResInfo();
		res.setData("没有车牌");
		res.setInfo("no");
		//发送mq消息
		SendMessageInfo s=new SendMessageInfo("没有车牌",info.getCommId(),info.getSerialno(), "data:image/png;base64,"+info.getImageFragmentFile(),"data:image/png;base64,"+info.getImageFile(), 1,"没有车牌",null);
		service.sendMessage("sendShareboManager",JSONObject.fromObject(s).toString());
		return res;
	}

}
