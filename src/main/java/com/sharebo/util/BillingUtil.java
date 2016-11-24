package com.sharebo.util;

import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONObject;

import com.sharebo.entity.FeeType;

/**
 * 计费
 * @author niewei
 *
 */
public class BillingUtil {
	//根据收费模式和分钟数计费
	public synchronized static  double computationalCost(String jsonStrFeeType,long diffMinutes){
		//解析
		FeeType ft=(FeeType) JSONObject.toBean(JSONObject.fromObject(jsonStrFeeType), FeeType.class);
		return cc(diffMinutes, ft);
	}
	/**
	 * 根据收费模式和进入时间进行极端费用
	 * @param jsonStrFeeType
	 * @param inTime
	 * @return 整收的价格
	 */
	public synchronized static double computationalCost(String jsonStrFeeType,Date inTime){
		//解析
		FeeType ft=(FeeType) JSONObject.toBean(JSONObject.fromObject(jsonStrFeeType), FeeType.class);
		Calendar c1=Calendar.getInstance();
		c1.setTime(inTime);//设置进去时间
		//计算两个时间相差分钟数
		Calendar c2=Calendar.getInstance();
		//两个时间相差分钟数
		long diffMinutes = (c2.getTimeInMillis()-c1.getTimeInMillis()) / (60 * 1000);
		return cc(diffMinutes, ft);
	}
	public static double cc(long diffMinutes,FeeType ft){
		//定义返回参数
		double money=0;
		
		//判断当前时间减去免费分钟数是否大于进去时间
		 if((diffMinutes-ft.getFreeMin())<=0){
			 //在免费时间段
			 money=0;
		 }else{
			 //判断是按次收费还是按照小时收费
			if(ft.getFeeModel()==1){//按次收费
				money= ft.getMoney();
			}else{//按照小时收费
				//判断分钟数是否小于60
				if(diffMinutes<60){//未满一个小时
					money=ft.getMoney();//收费一个小时
				}else{//超过一小时
					//方式一： 按照分钟计算
					//System.out.println(Double.parseDouble(diffMinutes+"")/60);
					//方式二：按照超过一分钟计算一小时
					//double aa=diffMinutes%60;
					//int hour=Integer.valueOf(((diffMinutes/60)+(aa>0?1:0))+"");//多少小时
					//cr.getMoney()*(Double.parseDouble(diffMinutes+"")/60);
					money=(Double.valueOf(diffMinutes)/60)*ft.getMoney();
				}
			}
			//判断封顶费用
			if(ft.getMaxMoney()!=-1){//设置封顶费用
				if(money>ft.getMaxMoney()){//应收费用 比封顶费用大  返回封顶费用
					money= ft.getMaxMoney();
				}
			}
		 }
		 return  money;
	}
}
