package com.sharebo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharebo.config.ResLedconfig;
import com.sharebo.entity.InOut_Info;
import com.sharebo.entity.Response_AlarmInfoPlate;
import com.sharebo.entity.Response_SerialData;
import com.sharebo.entity.SerialData;
import com.sharebo.service.CarDealService;
import com.sharebo.service.CarIntoService;
import com.sharebo.util.ResUtil;
import com.sharebo.util.SpringContextUtil;

import net.sf.json.JSONObject;

/**
 * 测试
 * @author niewei
 *
 */
@RestController
@RequestMapping("carinto")
public class CarIntoController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CarIntoService service;
	
	// 车辆进出，显示费用
	@RequestMapping("carinorout2")
	public Map<String, Object> test2(HttpServletRequest request) throws IOException{
		StringBuffer sb = new StringBuffer();
		BufferedReader br = request.getReader();
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb);
		JSONObject baseJo = JSONObject.fromObject(JSONObject.fromObject(
				sb.toString()).get("AlarmInfoPlate"));
		// 车牌相关信息
		JSONObject jo = JSONObject.fromObject(JSONObject.fromObject(
				baseJo.get("result")).get("PlateResult"));
		// 解析车牌.get("result").get("PlateResult")
		String license = jo.getString("license");
		// 得到设备号
		String serialno = baseJo.getString("serialno");
		serialno="eqno1";
		// 排除无车牌
		if (license.equals("_无_")) {
			license ="沪A12345";//
		}
		 com.sharebo.service.CarDealService cds=null;
		//通过设备号得到合作方
		Map<String, Object> map=service.getPartnerBySerialno(serialno);
		String partner=(String) map.get("partner");
		String commId=(String) map.get("commId");
		//取得图片
		String bImg="";
		try {
			bImg = jo.getString("imageFile");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("图片位解析未上传~");
		}
		InOut_Info inout=new InOut_Info(commId,serialno,license,0,bImg,null);
		//定义返回数据
		if(license==null){//没有车牌
			cds=(CarDealService) SpringContextUtil.getBean("NoCarNumberServiceImpl");
		}else{
			if(partner.equals("xb")){
				Integer isTollBooths=(Integer) map.get("isTollBooths");
				inout.setIsTollBooths(isTollBooths==null?0:isTollBooths);
				cds=(CarDealService) SpringContextUtil.getBean("ShareboDealServiceImpl");
			}else if(partner.equals("hx")){
				cds=(CarDealService) SpringContextUtil.getBean("HeXieDelServiceImpl");
			}else{
				//其他
				System.out.println("fack!what's this!");
			}
		}
		return ResUtil.res(cds.deal(inout));
	}
	// 车辆进出，显示费用
	@RequestMapping("carinorout")
	public Map<String, Object> aa(HttpServletRequest request)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = request.getReader();
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		JSONObject baseJo = JSONObject.fromObject(JSONObject.fromObject(
				sb.toString()).get("AlarmInfoPlate"));
		// 车牌相关信息
		JSONObject jo = JSONObject.fromObject(JSONObject.fromObject(
				baseJo.get("result")).get("PlateResult"));
		// 解析车牌.get("result").get("PlateResult")
		String license = jo.getString("license");
		System.out.println(license);
		// 得到设备号
		String serialno = baseJo.getString("serialno");
		// 修正无车牌
		if (license.equals("_无_")) {
			license =null;
		}
		// 定义响应数据
		String data = null;// LED显示内容
		String info = "err";// 是否开闸 默认不开
		String content = "识别成功！";
		// 得到是否进出
		int inandout = 0;// 1:进 2：出
		// 校验车牌
		if (license == null) {
			// 没有车牌
			data = ResLedconfig.notCarNo.replace("@0", "没有车牌");
		} else {
			// 得到是否进出
			inandout = service.getCarIsInout(serialno);// 1:进 2：出
			/***********处理自身业务***************/
			// 检验车牌是否是白名单
			boolean iswhitelist = service.valCarIsWhiteList(license, serialno);
			System.out.println("是否为白名单" + iswhitelist);
			if (iswhitelist) {// true :是白名单
				info = "ok";// 开闸
				// 显示内容
				if (inandout == 2) {
					data = ResLedconfig.out2.replace("@0", license);
				} else if (inandout == 1) {// 驶出 欢迎回家
					data = ResLedconfig.in.replace("@0", license);
				} else {
					// 数据错误
					log.error("数据错误,小区出入状态出现其他：" + inandout+"请检查数据库！");
				}
			} else {// 非白名单
				//*********计费***************/
				if (inandout == 2) {
					// 计费
					
				}
				if (inandout == 2) {
					data = ResLedconfig.out2.replace("@0", license);// 一路顺风
				} else if (inandout == 1) {//驶出 欢迎回家
					data = ResLedconfig.in2.replace("@0", license);// 驶入，外来车辆
				} else {
					// 数据错误
					log.error("数据错误,小区出入状态出现其他：" + inandout);
				}
			}
		}
		System.out.println("测试区域：==============================================");
		System.out.println("车牌号: " + license);
		System.out.println("进出：" + inandout);
		System.out.println("开闸信息：" + info);
		System.out.println("LED显示信息：" + data);
		System.out.println("测试区域：==============================================");
		// 相应数据编码
		String dataval=new String(org.apache.commons.codec.binary.Base64.encodeBase64(data.getBytes("gbk")));
		// 响应数据
		Response_AlarmInfoPlate responseAlarmInfoPlate = new Response_AlarmInfoPlate();
		responseAlarmInfoPlate.setInfo(info);// 是否开闸
		responseAlarmInfoPlate.setContent(content);
		responseAlarmInfoPlate.setIs_pay("true");
		SerialData sdata1 = new SerialData();
		sdata1.setDataLen(127);
		sdata1.setData(dataval);
		sdata1.setSerialChannel(0);
		List<SerialData> list = new ArrayList<SerialData>();
		list.add(sdata1);
		responseAlarmInfoPlate.setSerialData(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Response_AlarmInfoPlate", responseAlarmInfoPlate);
		return map;
	}
	//串口推送
	@RequestMapping("ck")
	public @ResponseBody  Map<String, Object> ck() throws UnsupportedEncodingException{
		System.out.println("来过！");
		Response_SerialData rd=new Response_SerialData();
		rd.setInfo("ok");
		SerialData sdata = new SerialData();
		sdata.setDataLen(127);
		sdata.setData(new String(org.apache.commons.codec.binary.Base64.encodeBase64("!#001%ZA02%ZH0020%C1@0$$".getBytes("gbk"))));
		sdata.setSerialChannel(1);
		SerialData sdata1 = new SerialData();
		sdata1.setDataLen(127);
		sdata1.setData(new String(org.apache.commons.codec.binary.Base64.encodeBase64("!#001%ZA02%ZH0020%C1@0$$".getBytes("gbk"))));
		sdata1.setSerialChannel(0);
		List<SerialData> list = new ArrayList<SerialData>();
		list.add(sdata);
		list.add(sdata1);
		rd.setSerialData(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Response_SerialData", rd);
		return map;
	}
}
