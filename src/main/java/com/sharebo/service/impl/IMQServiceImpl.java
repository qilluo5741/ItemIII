package com.sharebo.service.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.sharebo.entity.SendMessageInfo;
import com.sharebo.service.IMQService;
import com.sharebo.util.SpringContextUtil;


@Service
public class IMQServiceImpl implements IMQService{
	@Autowired
	private JmsTemplate jmsTemplate;
	//发送文本消息
	public void sendMessage(String queueStr, final String message){
		//得到bean
		//得到容器中配置通知目标属性
		Destination destination = (Destination) SpringContextUtil.getBean(queueStr);
		//System.out.println("发送：" + message);
		jmsTemplate.send(destination, new MessageCreator() {
					public Message createMessage(Session session) throws JMSException {
						return session.createTextMessage(message);
					}
				});
	}
	@Override
	public void sendSendMessageInfo(String queueStr,final SendMessageInfo sm) {
		// TODO Auto-generated method stub
		//得到bean
				//得到容器中配置通知目标属性
				Destination destination = (Destination) SpringContextUtil.getBean(queueStr);
				System.out.println("发送：" + sm.toString());
				jmsTemplate.send(destination, new MessageCreator() {
							public Message createMessage(Session session) throws JMSException {
								return session.createObjectMessage(sm);
							}
						});
	}
}
