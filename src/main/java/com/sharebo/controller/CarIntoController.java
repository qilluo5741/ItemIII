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
 * ����
 * @author niewei
 *
 */
@RestController
@RequestMapping("carinto")
public class CarIntoController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CarIntoService service;
	
	// ������������ʾ����
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
		// ���������Ϣ
		JSONObject jo = JSONObject.fromObject(JSONObject.fromObject(
				baseJo.get("result")).get("PlateResult"));
		// ��������.get("result").get("PlateResult")
		String license = jo.getString("license");
		// �õ��豸��
		String serialno = baseJo.getString("serialno");
		serialno="eqno1";
		// �ų��޳���
		if (license.equals("_��_")) {
			license ="��A12345";//
		}
		 com.sharebo.service.CarDealService cds=null;
		//ͨ���豸�ŵõ�������
		Map<String, Object> map=service.getPartnerBySerialno(serialno);
		String partner=(String) map.get("partner");
		String commId=(String) map.get("commId");
		//ȡ��ͼƬ
		String bImg="";
		try {
			bImg = jo.getString("imageFile");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("ͼƬλ����δ�ϴ�~");
		}
		InOut_Info inout=new InOut_Info(commId,serialno,license,0,bImg,null);
		//���巵������
		if(license==null){//û�г���
			cds=(CarDealService) SpringContextUtil.getBean("NoCarNumberServiceImpl");
		}else{
			if(partner.equals("xb")){
				Integer isTollBooths=(Integer) map.get("isTollBooths");
				inout.setIsTollBooths(isTollBooths==null?0:isTollBooths);
				cds=(CarDealService) SpringContextUtil.getBean("ShareboDealServiceImpl");
			}else if(partner.equals("hx")){
				cds=(CarDealService) SpringContextUtil.getBean("HeXieDelServiceImpl");
			}else{
				//����
				System.out.println("fack!what's this!");
			}
		}
		return ResUtil.res(cds.deal(inout));
	}
	// ������������ʾ����
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
		// ���������Ϣ
		JSONObject jo = JSONObject.fromObject(JSONObject.fromObject(
				baseJo.get("result")).get("PlateResult"));
		// ��������.get("result").get("PlateResult")
		String license = jo.getString("license");
		System.out.println(license);
		// �õ��豸��
		String serialno = baseJo.getString("serialno");
		// �����޳���
		if (license.equals("_��_")) {
			license =null;
		}
		// ������Ӧ����
		String data = null;// LED��ʾ����
		String info = "err";// �Ƿ�բ Ĭ�ϲ���
		String content = "ʶ��ɹ���";
		// �õ��Ƿ����
		int inandout = 0;// 1:�� 2����
		// У�鳵��
		if (license == null) {
			// û�г���
			data = ResLedconfig.notCarNo.replace("@0", "û�г���");
		} else {
			// �õ��Ƿ����
			inandout = service.getCarIsInout(serialno);// 1:�� 2����
			/***********��������ҵ��***************/
			// ���鳵���Ƿ��ǰ�����
			boolean iswhitelist = service.valCarIsWhiteList(license, serialno);
			System.out.println("�Ƿ�Ϊ������" + iswhitelist);
			if (iswhitelist) {// true :�ǰ�����
				info = "ok";// ��բ
				// ��ʾ����
				if (inandout == 2) {
					data = ResLedconfig.out2.replace("@0", license);
				} else if (inandout == 1) {// ʻ�� ��ӭ�ؼ�
					data = ResLedconfig.in.replace("@0", license);
				} else {
					// ���ݴ���
					log.error("���ݴ���,С������״̬����������" + inandout+"�������ݿ⣡");
				}
			} else {// �ǰ�����
				//*********�Ʒ�***************/
				if (inandout == 2) {
					// �Ʒ�
					
				}
				if (inandout == 2) {
					data = ResLedconfig.out2.replace("@0", license);// һ·˳��
				} else if (inandout == 1) {//ʻ�� ��ӭ�ؼ�
					data = ResLedconfig.in2.replace("@0", license);// ʻ�룬��������
				} else {
					// ���ݴ���
					log.error("���ݴ���,С������״̬����������" + inandout);
				}
			}
		}
		System.out.println("��������==============================================");
		System.out.println("���ƺ�: " + license);
		System.out.println("������" + inandout);
		System.out.println("��բ��Ϣ��" + info);
		System.out.println("LED��ʾ��Ϣ��" + data);
		System.out.println("��������==============================================");
		// ��Ӧ���ݱ���
		String dataval=new String(org.apache.commons.codec.binary.Base64.encodeBase64(data.getBytes("gbk")));
		// ��Ӧ����
		Response_AlarmInfoPlate responseAlarmInfoPlate = new Response_AlarmInfoPlate();
		responseAlarmInfoPlate.setInfo(info);// �Ƿ�բ
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
	//��������
	@RequestMapping("ck")
	public @ResponseBody  Map<String, Object> ck() throws UnsupportedEncodingException{
		System.out.println("������");
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
