package com.sharebo.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharebo.entity.AlarminfoPlate;
import com.sharebo.entity.InOut_Info;
import com.sharebo.entity.Location;
import com.sharebo.entity.Plateresult;
import com.sharebo.entity.Rect;
import com.sharebo.entity.ResInfo;
import com.sharebo.entity.Result;
import com.sharebo.entity.Timestamp;
import com.sharebo.entity.Timeval;
import com.sharebo.entity.VehicieInfo;
import com.sharebo.mapper.CarIntoMapper;
import com.sharebo.service.CarDealService;
import com.sharebo.util.HttpUtil;
/**
 * ��Эҵ����
 * @author niewei
 *
 */
@Service
public class HeXieDelServiceImpl implements CarDealService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static String url;
	@Autowired
	private CarIntoMapper mapper;
	
	public static void setUrl(String url) {
		HeXieDelServiceImpl.url = url;
	}
	public synchronized ResInfo deal(InOut_Info info) {
		ResInfo result = new ResInfo();
		AlarminfoPlate ap = new AlarminfoPlate();
		Result res = new Result();
		Plateresult pr = new Plateresult();
		Location l=new Location();
		l.setRect(new Rect());
		pr.setLocation(l);
		Timestamp t=new Timestamp();
		t.setTimeval(new Timeval());
		pr.setTimestamp(t);
		pr.setLicense(info.getLicense());
		res.setPlateresult(pr);
		ap.setSerialno(info.getSerialno());//��
		//ap.setSerialno("222222");//��
		ap.setResult(res);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("AlarmInfoPlate", ap);
		ObjectMapper mapper = new ObjectMapper();
		String jsonVal=null;
		try {
			jsonVal = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e1) {
			log.error("jsonת��ʧ�ܣ�");
		}
		System.out.println(jsonVal);
		//String url = "http://test.e-shequ.com/mobileInterface/client/recvCarInfoSDO.do";
		String resData=null;
		try {
			resData = HttpUtil.request_post(url, jsonVal);
		} catch (Exception e1) {
			log.error("����ʧ�ܣ����������Ϊ��"+jsonVal);
		}
		log.info("��Ӧ���ݣ�"+resData);
		System.out.println(resData);
		JSONObject Response_AlarmInfoPlate=JSONObject.fromObject(resData).getJSONObject("Response_AlarmInfoPlate");
		JSONArray jo1=JSONObject.fromObject(Response_AlarmInfoPlate).getJSONArray("serialData");
		//�����Ƿ�բ
		result.setInfo(Response_AlarmInfoPlate.getString("info"));
		String data=JSONObject.fromObject(jo1.get(0)).getString("data");
		try {
			String d=new String(org.apache.commons.codec.binary.Base64.decodeBase64(data),"gbk");
			result.setData(d);
		} catch (UnsupportedEncodingException e) {
			log.error("data����ʧ�ܣ�");
		}
		//�������
		//ͨ���豸�õ��Ƿ����
		Integer inandout= this.mapper.getCarIsInout(info.getSerialno());// 1:�� 2����
		if(inandout==1){//ʻ��
			String vehicleId=UUID.randomUUID().toString();
			//����볡����
			this.mapper.addInVehicie(new VehicieInfo(vehicleId, null, info.getLicense(), 0, info.getSerialno(),""));
		}else{//ʻ��
			//�õ�ʻ���¼
			com.sharebo.entity.VehicieInfo v=this.mapper.getOnDataByCarNo(info.getCommId(), info.getLicense());
			if(v!=null){
				//�޸�ʻ��
				 v.setOutMac(info.getSerialno());
				this.mapper.updateOutInfo(v);
			}
		}
		System.out.println("��������--------------------------------");
		System.out.println("LED��ʾ��Ϣ��"+result.getData());
		System.out.println("�Ƿ�բ��"+result.getInfo());
		System.out.println("��������--------------------------------");
		if(result.getInfo()==null){
			result.setInfo("no");
		}else if(result.getData()==null){
			result.setData("����ʧ��");
		}
		return result;
	}

}
