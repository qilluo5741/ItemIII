/*package com.sharebo.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
*//**
 * 消费mq消息
 * @author niewei
 *
 *//*
public class MQConsumerListener implements MessageListener{

	public void onMessage(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			System.out.println("读取的数据：" +textMsg.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
*/