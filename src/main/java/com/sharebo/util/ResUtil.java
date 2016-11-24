package com.sharebo.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sharebo.config.ResLedconfig;
import com.sharebo.entity.Response_AlarmInfoPlate;
import com.sharebo.entity.SerialData;

/**
 * 返回给相机参数
 * 
 * @author niewei
 *
 */
public class ResUtil {
	/**
	 * 封装响应参数
	 * @param res
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public synchronized static Map<String, Object> res(com.sharebo.entity.ResInfo res) throws UnsupportedEncodingException {
		res.setData(ResLedconfig.led.replace("@0", res.getData()));//设置参数
		// 响应数据
		Response_AlarmInfoPlate responseAlarmInfoPlate = new Response_AlarmInfoPlate();
		responseAlarmInfoPlate.setInfo(res.getInfo());// 是否开闸
		responseAlarmInfoPlate.setContent("");
		responseAlarmInfoPlate.setIs_pay("true");
		SerialData sdata1 = new SerialData();
		sdata1.setDataLen(127);
		//编码赋值
		String dataval=new String(org.apache.commons.codec.binary.Base64.encodeBase64("!#001%ZA02%ZH0020%ZI01%C1111111$$".getBytes("gbk")));
		//String dataval=new String(org.apache.commons.codec.binary.Base64.encodeBase64(res.getData().getBytes("gbk")));
		sdata1.setData(dataval);
		sdata1.setSerialChannel(0);
		
		SerialData sdata2 = new SerialData();
		sdata2.setDataLen(128);
		//编码赋值
		String datava2=new String(org.apache.commons.codec.binary.Base64.encodeBase64("!#001%ZA02%ZH0020%ZI02%C1222222$$".getBytes("gbk")));
		sdata2.setData(datava2);
		sdata2.setSerialChannel(0);
		List<SerialData> list = new ArrayList<SerialData>();
		list.add(sdata1);
		list.add(sdata2);
		responseAlarmInfoPlate.setSerialData(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Response_AlarmInfoPlate", responseAlarmInfoPlate);
		return map;
	}
}
