package com.sharebo.service;

import com.sharebo.entity.SendMessageInfo;

public interface IMQService {
	//�����ı���Ϣ
	public void sendMessage(String queueStr, final String message);
	//�����ֽ���
	public void sendSendMessageInfo(String queueStr,final SendMessageInfo sm);
	//����map
	//����Object
	//����byte
}
