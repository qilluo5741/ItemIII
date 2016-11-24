package com.sharebo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.service.IMQService;
import com.sharebo.util.HttpUtil;

/**
 * 测试
 * @author niewei
 *
 */
@Controller
public class TestController {
	@Autowired
	private IMQService service;
	@RequestMapping("sendMessage")
	public @ResponseBody String test1(String carNo,String eqNo,String commId){
		//SendMessageInfo s=new SendMessageInfo(carNo,commId,eqNo, "xiao","da", 1,"外来车辆");
		//service.sendMessage("sendShareboManager",JSONObject.fromObject(s).toString());
		//service.sendSendMessageInfo("sendShareboManager", s);
		return "hello";
	}
	
	public static void main(String[] args) {
		 /* String t1="沪A12345";
		  List<String> strs=new ArrayList<String>();
		  strs.add("沪A12346");
		  strs.add("沪A12357");
		  strs.add("沪A12368");
		  strs.add("沪A12379");
		  Map<String,Integer> map=new HashMap<String, Integer>();
		  
		  char[] ch=new char[t1.length()];
		  for(int i=0;i<t1.length();i++){
			  ch[i]=t1.charAt(i);
		  }
		  for (String str : strs) {
			  map.put(str, 0);
			 for(int i=0;i<str.length();i++){
				 if(str.charAt(i)==ch[i]){
					 Integer c=map.get(str);
					 map.put(str,c+1);
				 }
			 }
		  }
		  
		  for(String i:map.keySet()){
			  System.out.println(i+"  "+map.get(i));
		  }*/
		String msg="{\"Title\":\"YIXING\",\"PlateNumber\":\"京A12345\",\"Start\":\"2015/08/01 12:00:00\",\"End\":\"2017/06/01 12:00:00\",\"Type\":\"W\",\"Action\":\"add\"}";
		System.out.println(msg);
		  System.out.println(HttpUtil.request_post("http://101.82.251.53:8888", msg));
	}
	//下发白名单
	@RequestMapping("W")
	@ResponseBody
	public String w(String msg,String ip){
		System.out.println(ip);
		return HttpUtil.request_post(ip, msg);
	}
}
