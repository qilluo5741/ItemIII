package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONObject;

import com.sharebo.entity.InOut_Info;
import com.sharebo.entity.ResInfo;
import com.sharebo.entity.SendMessageInfo;
import com.sharebo.service.CarDealService;
import com.sharebo.service.IMQService;
/**
 * û�г���
 * @author niewei
 *
 */
public class NoCarNumberServiceImpl implements CarDealService {
	@Autowired
	private IMQService service;
	//����ҵ��
	public synchronized ResInfo deal(InOut_Info info) {
		ResInfo res=new ResInfo();
		res.setData("û�г���");
		res.setInfo("no");
		//����mq��Ϣ
		SendMessageInfo s=new SendMessageInfo("û�г���",info.getCommId(),info.getSerialno(), "data:image/png;base64,"+info.getImageFragmentFile(),"data:image/png;base64,"+info.getImageFile(), 1,"û�г���",null);
		service.sendMessage("sendShareboManager",JSONObject.fromObject(s).toString());
		return res;
	}

}
