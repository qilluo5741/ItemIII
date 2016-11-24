package com.sharebo.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharebo.entity.AlarminfoPlate;
import com.sharebo.entity.Location;
import com.sharebo.entity.Plateresult;
import com.sharebo.entity.Rect;
import com.sharebo.entity.Result;
import com.sharebo.entity.Timestamp;
import com.sharebo.entity.Timeval;

import sun.misc.BASE64Decoder;     
import sun.misc.BASE64Encoder;  
public class TestMain {
	public static void main(String[] args) throws IOException {
		/*AlarminfoPlate ap = new AlarminfoPlate();
		Result res = new Result();
		Plateresult pr = new Plateresult();
		Location l=new Location();
		l.setRect(new Rect());
		pr.setLocation(l);
		Timestamp t=new Timestamp();
		t.setTimeval(new Timeval());
		pr.setTimestamp(t);
		pr.setLicense("渝A12345");
		res.setPlateresult(pr);
		ap.setSerialno("111111");//进
		//ap.setSerialno("222222");//出
		ap.setResult(res);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("AlarmInfoPlate", ap);
		ObjectMapper mapper = new ObjectMapper();
		String jsonVal = mapper.writeValueAsString(map);
		System.out.println(jsonVal);
		String url = "http://test.e-shequ.com/mobileInterface/client/recvCarInfoSDO.do";
		//String url="http://localhost:8080/sharebodoc/carinto/carinorout.do";
		String resData=HttpUtil.request_post(url, jsonVal);
		System.out.println(resData);
		JSONObject Response_AlarmInfoPlate=JSONObject.fromObject(resData).getJSONObject("Response_AlarmInfoPlate");
		JSONArray jo1=JSONObject.fromObject(Response_AlarmInfoPlate).getJSONArray("serialData");
		
		String data=JSONObject.fromObject(jo1.get(0)).getString("data");
		//1.
		String d=new String(org.apache.commons.codec.binary.Base64.decodeBase64(data),"gbk");
		
		System.out.println(d);*/
		//aa();
	}
/*	public static void aa(){
		//你好！Hello!
		//A0 90 01 50 00 12 60 4F 7D 59 01 FF 48 00 65 00 6C 00 6C 00 6F 00 21 00 D5   
		byte c[]=new byte[100];
		c[0]=0xA0;//报文头 0xA0
		c[1]=0x9;//报文头和卡号高4位。 D7~D4为 0x9，D3~D0为卡号高4位
		c[2]=;
		System.out.println();
	}*/
}
