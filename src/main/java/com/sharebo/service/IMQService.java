package com.sharebo.service;

import com.sharebo.entity.SendMessageInfo;

public interface IMQService {
	//发送文本消息
	public void sendMessage(String queueStr, final String message);
	//发送字节流
	public void sendSendMessageInfo(String queueStr,final SendMessageInfo sm);
	//发送map
	//发送Object
	//发送byte
}
