package com.sharebo.util;

import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONObject;

import com.sharebo.entity.FeeType;

/**
 * �Ʒ�
 * @author niewei
 *
 */
public class BillingUtil {
	//�����շ�ģʽ�ͷ������Ʒ�
	public synchronized static  double computationalCost(String jsonStrFeeType,long diffMinutes){
		//����
		FeeType ft=(FeeType) JSONObject.toBean(JSONObject.fromObject(jsonStrFeeType), FeeType.class);
		return cc(diffMinutes, ft);
	}
	/**
	 * �����շ�ģʽ�ͽ���ʱ����м��˷���
	 * @param jsonStrFeeType
	 * @param inTime
	 * @return ���յļ۸�
	 */
	public synchronized static double computationalCost(String jsonStrFeeType,Date inTime){
		//����
		FeeType ft=(FeeType) JSONObject.toBean(JSONObject.fromObject(jsonStrFeeType), FeeType.class);
		Calendar c1=Calendar.getInstance();
		c1.setTime(inTime);//���ý�ȥʱ��
		//��������ʱ����������
		Calendar c2=Calendar.getInstance();
		//����ʱ����������
		long diffMinutes = (c2.getTimeInMillis()-c1.getTimeInMillis()) / (60 * 1000);
		return cc(diffMinutes, ft);
	}
	public static double cc(long diffMinutes,FeeType ft){
		//���巵�ز���
		double money=0;
		
		//�жϵ�ǰʱ���ȥ��ѷ������Ƿ���ڽ�ȥʱ��
		 if((diffMinutes-ft.getFreeMin())<=0){
			 //�����ʱ���
			 money=0;
		 }else{
			 //�ж��ǰ����շѻ��ǰ���Сʱ�շ�
			if(ft.getFeeModel()==1){//�����շ�
				money= ft.getMoney();
			}else{//����Сʱ�շ�
				//�жϷ������Ƿ�С��60
				if(diffMinutes<60){//δ��һ��Сʱ
					money=ft.getMoney();//�շ�һ��Сʱ
				}else{//����һСʱ
					//��ʽһ�� ���շ��Ӽ���
					//System.out.println(Double.parseDouble(diffMinutes+"")/60);
					//��ʽ�������ճ���һ���Ӽ���һСʱ
					//double aa=diffMinutes%60;
					//int hour=Integer.valueOf(((diffMinutes/60)+(aa>0?1:0))+"");//����Сʱ
					//cr.getMoney()*(Double.parseDouble(diffMinutes+"")/60);
					money=(Double.valueOf(diffMinutes)/60)*ft.getMoney();
				}
			}
			//�жϷⶥ����
			if(ft.getMaxMoney()!=-1){//���÷ⶥ����
				if(money>ft.getMaxMoney()){//Ӧ�շ��� �ȷⶥ���ô�  ���طⶥ����
					money= ft.getMaxMoney();
				}
			}
		 }
		 return  money;
	}
}
