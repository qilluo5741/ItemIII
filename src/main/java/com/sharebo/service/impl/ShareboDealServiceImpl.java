package com.sharebo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.InOut_Info;
import com.sharebo.entity.MatchLicensePlateInfo;
import com.sharebo.entity.ResInfo;
import com.sharebo.entity.SendMessageInfo;
import com.sharebo.entity.VehicieInfo;
import com.sharebo.mapper.CarIntoMapper;
import com.sharebo.service.CarDealService;
import com.sharebo.service.IMQService;
import com.sharebo.util.BillingUtil;
import com.sharebo.util.MatchLicensePlateUtil;
/**
 * 享泊业务
 * @author niewei
 *
 */
@Service
public class ShareboDealServiceImpl implements CarDealService {
	//进入 ：欢迎回家
	private static final String IN_GO_HOME="欢迎回家";
	//进入：外来车辆
	private static final String IN_FOREIGN_CAR="外来车辆";
	//驶出
	private static final String OUT="一路平安";
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	//得到mapper
	@Autowired
	private CarIntoMapper mapper;
	@Autowired
	private IMQService service;
	//处理业务
	public synchronized ResInfo  deal(InOut_Info info) {
		//返回数据
		ResInfo res=new ResInfo();
		//通过设备得到是否进出
		Integer inandout= mapper.getCarIsInout(info.getSerialno());// 1:进 2：出
		// 检验车牌是否是白名单
		int iswhitelist = mapper.valCarIsWhiteList(info.getLicense(), info.getSerialno());//>0：是  其，不是
		//是否存在子停车场
		boolean ischild=false;
		//如果不是，检查是否还存在
		if(iswhitelist<=0){//不是白名单
			//查询当前停车场中，是否存在子停车场
			if(mapper.getCommcommparent(info.getCommId())>0){//存在子停车场
				//检验是否是白名单
				iswhitelist=mapper.getChidenWByCommIdAndCarNo(info.getCommId(),info.getLicense());
				ischild=true;//存在子停车场
			}
		}
		String vehicleId=null;
		SendMessageInfo s=null;//推送
		s=new SendMessageInfo(info.getLicense(),info.getCommId(),info.getSerialno(), "data:image/png;base64,"+info.getImageFragmentFile(),"data:image/png;base64,"+info.getImageFile(), 1,iswhitelist>0?"物业车辆":"外来车辆",vehicleId);
		//判断进出
		if(inandout == 1){//进入
			vehicleId=UUID.randomUUID().toString();
			//设置推songvehicleId
			s.setVehicleId(vehicleId);
			//判断是否为白名单
			if(iswhitelist>0){//是白名单
				res.setInfo("ok");//开闸
				res.setData(info.getLicense()+IN_GO_HOME);
				//添加入场数据
				mapper.addInVehicie(new VehicieInfo(vehicleId, null, info.getLicense(), iswhitelist, info.getSerialno(),mapper.getParkFeeModelByCommId(info.getCommId())));
			}else{//不是白名单
				/*****当前开闸需要验证是否存在空车位 或者是否是禁止外来入内**********/
				Map<String, Integer> commInfo_map=mapper.getpsCountAndIsBanOnForeignCarsByCommId(info.getCommId());
				if(commInfo_map.get("psCount")>0){//有车位
					res.setInfo("ok");//开闸
					//添加入场数据
					mapper.addInVehicie(new VehicieInfo(vehicleId, null, info.getLicense(), iswhitelist, info.getSerialno(),mapper.getParkFeeModelByCommId(info.getCommId())));
					res.setData(info.getLicense()+IN_FOREIGN_CAR);//外来车辆
					//减去一个车位
					mapper.updatePsConutByCommId(info.getCommId(), -1);
				}else if(commInfo_map.get("isBanOnForeignCars")==0){//禁止外来车辆入内
					//不允许入内
					res.setInfo("no");//不开闸
					res.setData("禁止入内外来车辆");
				}else{//没有车位
					res.setInfo("no");//不开闸
					res.setData("没有车位");
				}
			}
		}else if(inandout == 2){/*********************驶出*********************/
			//得到驶入记录
			com.sharebo.entity.VehicieInfo v=mapper.getOnDataByCarNo(info.getCommId(), info.getLicense());
			if(v==null){//没有入场记录
				//System.out.println("没有入场记录---------开始进行模糊匹配----------------------------------------");
				List<MatchLicensePlateInfo> mlps=mapper.getvehicleAllBycommId(info.getCommId());
				MatchLicensePlateInfo mlp=MatchLicensePlateUtil.calculate(mlps, info.getLicense());
				if(mlp!=null&&(mlp.getNotSimilarity()>=info.getLicense().length()-2)){//找到最高匹配，并判断位数
					//通过vehicleId;//出入记录进行得到数据
					v=mapper.getvehicleByvehicleId(mlp.getVehicleId());
				}else{
					res.setInfo("ok");
					res.setData("没有入场时间");
					log.warn("车牌号："+info.getLicense()+",没有入场时间!");
				}
			}
			if(v!=null){
				//计算停留分钟数
				v.setForMinutes(stopminutes(v.getInTime()));
				//设置驶出设备号
				v.setOutMac(info.getSerialno() );
				//判断是否为白名单
				if(iswhitelist>0){//是白名单
					res.setInfo("ok");//是白名单直接开闸
					res.setData(info.getLicense()+OUT);
					//修改其他信息
					v.setArFee(0.0);//费用为0
					mapper.updateOutInfo(v);
				}else if(info.getIsTollBooths()!=1){//不是白名单 但是也不是收费口  直接开
					//计算费用
					double money=BillingUtil.computationalCost(v.getFeeModel(),v.getInTime());
					//验证当前设备是否还存在外设备  //检验
					res.setInfo("ok");//开闸
					res.setData(info.getLicense()+OUT);
					//修改其他信息
					v.setArFee(money);//费用
					s.setMoney(money);// 推送费用
					mapper.updateOutInfo(v);
				}else{//不是白名单或者是收费口
					if(ischild){//存在子停车场  
						System.out.println("***************存在子停车场*********************");
						//得到子停车场中停车记录  //判断当前时间段是否存在进出数据   
						com.sharebo.entity.VehicieInfo chiden_v=mapper.getChidenVehiceByCarNOAndCommIdAndInTime(info.getCommId(), info.getLicense(), v.getInTime());
						//如果存在数据    验证是否缴费  停留分钟数
						if(chiden_v!=null){// 在子停车场停车
							System.out.println("在子停车场停过车。。。停车时间是："+chiden_v.getForMinutes());
							System.out.println(chiden_v.toString());
							double chiden_v_money=0;  //最终费用
							//验证是否已经收费
							if(chiden_v.getFeeTime()==null&&chiden_v.getPaidInFee()==null){//里面的没有收费
								//当前费用加内部费用
								chiden_v_money=chiden_v.getArFee();
								//System.out.println("子停车场没有收费哦！！！！！应该收费："+chiden_v.getArFee());
							}else{//已经收费
								//只收当前费用
								chiden_v_money=0;
								//System.out.println("子停车场已经收费了哦！！！！！");
							}
							/********************计算中途是否超时 begin*******(无论是否收费，都需要计算)******************/
							//计算中途是否超时
							//外部停车分钟数减去子停车场分钟数  得到差，进行比较 是否大于30分钟
							long stayMinutes=v.getForMinutes()-chiden_v.getForMinutes();//中途逗留分钟数
							if(stayMinutes>20){//中途超出时间  进行额外计费
								//再次外部计费  :中间逗留的费用
								double staymoney=BillingUtil.computationalCost(v.getFeeModel(),stayMinutes);
								chiden_v_money+=staymoney;
								//System.out.println("逗留计费："+staymoney);
							}
							//System.out.println("在外面逗留的时间是："+v.getForMinutes());
							//总共费用是 ：
							//System.out.println(chiden_v_money+"    总共费用是这么多，。。");
							/********************计算中途是否超时 end *************************/
							v.setArFee(chiden_v_money);//设置逗留时间和未收费的钱
						}else{
							/**********************没有在子停车场停过车（与不存在子停车场业务一致）*******************/
							//直接计费
							v.setArFee(BillingUtil.computationalCost(v.getFeeModel(),v.getInTime()));
						}
					}else{//不存在子停车场
						//验证是否已经收费
						/************* 不存在子停车场*********************");*/
						//直接计费
						v.setArFee(BillingUtil.computationalCost(v.getFeeModel(),v.getInTime()));
					}
					//计算费用
					//double money=(BillingUtil.computationalCost(v.getFeeModel(),v.getInTime()));
					double money=v.getArFee();
					System.out.println(money+"      最终的费用是");
					if(money==0){
						res.setInfo("ok");
						res.setData(info.getLicense()+OUT);
					}else{
						res.setInfo("no");
						res.setData(info.getLicense()+"收费"+(int)money+"元");
					}
					v.setArFee(Math.rint(v.getArFee()));
					v.setArFee(Double.valueOf(v.getArFee().toString().substring(0, v.getArFee().toString().indexOf("."))));
					mapper.updateOutInfo(v);
					//加上空车位
					mapper.updatePsConutByCommId(info.getCommId(), +1);
					//System.out.println("最外层："+v.toString());
				}
			}
			//设置推动vehicleId
			
			if(v==null){
				s.setVehicleId("");
				s.setFeeInstructions("没有入场记录");
			}else{
				s.setMoney(v.getArFee());
				s.setVehicleId(v.getVehicleId());
				s.setFeeInstructions("<span>停车时长：<b>"+v.getForMinutes()+"</b>分钟，驶入时间：<b>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(v.getInTime())+"</b></span> ");
			}
		}else{
			//其他数据
			// 数据错误
			log.error("数据错误,小区出入状态出现其他：" + inandout+"请检查数据库！");
		}
		
		System.out.println("测试区域：==============================================");
		System.out.println("车牌号: " + info.getLicense());
		System.out.println("进出：" + inandout);
		System.out.println("开闸信息：" + res.getInfo());
		System.out.println("LED显示信息：" +res.getData());
		System.out.println("测试区域：==============================================");
		//发送mq消息
		try {
			service.sendMessage("sendShareboManager",JSONObject.fromObject(s).toString());
		} catch (Exception e) {
			System.out.println("mq发送数据失败！请检查链接是否超时。");
			log.error("mq发送数据失败！请检查链接是否超时。");
		}
		return res;
	}
	
	/**
	 * 计算相差分钟数
	 * @param inTime
	 * @return
	 */
	public static long stopminutes(Date inTime){
		Calendar c1=Calendar.getInstance();
		c1.setTime(inTime);//设置进去时间
		//计算两个时间相差分钟数
		Calendar c2=Calendar.getInstance();
		//两个时间相差分钟数
		long diffMinutes = (c2.getTimeInMillis()-c1.getTimeInMillis()) / (60 * 1000);
		return diffMinutes;
	}
}
