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
 * ��ҵ��
 * @author niewei
 *
 */
@Service
public class ShareboDealServiceImpl implements CarDealService {
	//���� ����ӭ�ؼ�
	private static final String IN_GO_HOME="��ӭ�ؼ�";
	//���룺��������
	private static final String IN_FOREIGN_CAR="��������";
	//ʻ��
	private static final String OUT="һ·ƽ��";
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	//�õ�mapper
	@Autowired
	private CarIntoMapper mapper;
	@Autowired
	private IMQService service;
	//����ҵ��
	public synchronized ResInfo  deal(InOut_Info info) {
		//��������
		ResInfo res=new ResInfo();
		//ͨ���豸�õ��Ƿ����
		Integer inandout= mapper.getCarIsInout(info.getSerialno());// 1:�� 2����
		// ���鳵���Ƿ��ǰ�����
		int iswhitelist = mapper.valCarIsWhiteList(info.getLicense(), info.getSerialno());//>0����  �䣬����
		//�Ƿ������ͣ����
		boolean ischild=false;
		//������ǣ�����Ƿ񻹴���
		if(iswhitelist<=0){//���ǰ�����
			//��ѯ��ǰͣ�����У��Ƿ������ͣ����
			if(mapper.getCommcommparent(info.getCommId())>0){//������ͣ����
				//�����Ƿ��ǰ�����
				iswhitelist=mapper.getChidenWByCommIdAndCarNo(info.getCommId(),info.getLicense());
				ischild=true;//������ͣ����
			}
		}
		String vehicleId=null;
		SendMessageInfo s=null;//����
		s=new SendMessageInfo(info.getLicense(),info.getCommId(),info.getSerialno(), "data:image/png;base64,"+info.getImageFragmentFile(),"data:image/png;base64,"+info.getImageFile(), 1,iswhitelist>0?"��ҵ����":"��������",vehicleId);
		//�жϽ���
		if(inandout == 1){//����
			vehicleId=UUID.randomUUID().toString();
			//������songvehicleId
			s.setVehicleId(vehicleId);
			//�ж��Ƿ�Ϊ������
			if(iswhitelist>0){//�ǰ�����
				res.setInfo("ok");//��բ
				res.setData(info.getLicense()+IN_GO_HOME);
				//����볡����
				mapper.addInVehicie(new VehicieInfo(vehicleId, null, info.getLicense(), iswhitelist, info.getSerialno(),mapper.getParkFeeModelByCommId(info.getCommId())));
			}else{//���ǰ�����
				/*****��ǰ��բ��Ҫ��֤�Ƿ���ڿճ�λ �����Ƿ��ǽ�ֹ��������**********/
				Map<String, Integer> commInfo_map=mapper.getpsCountAndIsBanOnForeignCarsByCommId(info.getCommId());
				if(commInfo_map.get("psCount")>0){//�г�λ
					res.setInfo("ok");//��բ
					//����볡����
					mapper.addInVehicie(new VehicieInfo(vehicleId, null, info.getLicense(), iswhitelist, info.getSerialno(),mapper.getParkFeeModelByCommId(info.getCommId())));
					res.setData(info.getLicense()+IN_FOREIGN_CAR);//��������
					//��ȥһ����λ
					mapper.updatePsConutByCommId(info.getCommId(), -1);
				}else if(commInfo_map.get("isBanOnForeignCars")==0){//��ֹ������������
					//����������
					res.setInfo("no");//����բ
					res.setData("��ֹ������������");
				}else{//û�г�λ
					res.setInfo("no");//����բ
					res.setData("û�г�λ");
				}
			}
		}else if(inandout == 2){/*********************ʻ��*********************/
			//�õ�ʻ���¼
			com.sharebo.entity.VehicieInfo v=mapper.getOnDataByCarNo(info.getCommId(), info.getLicense());
			if(v==null){//û���볡��¼
				//System.out.println("û���볡��¼---------��ʼ����ģ��ƥ��----------------------------------------");
				List<MatchLicensePlateInfo> mlps=mapper.getvehicleAllBycommId(info.getCommId());
				MatchLicensePlateInfo mlp=MatchLicensePlateUtil.calculate(mlps, info.getLicense());
				if(mlp!=null&&(mlp.getNotSimilarity()>=info.getLicense().length()-2)){//�ҵ����ƥ�䣬���ж�λ��
					//ͨ��vehicleId;//�����¼���еõ�����
					v=mapper.getvehicleByvehicleId(mlp.getVehicleId());
				}else{
					res.setInfo("ok");
					res.setData("û���볡ʱ��");
					log.warn("���ƺţ�"+info.getLicense()+",û���볡ʱ��!");
				}
			}
			if(v!=null){
				//����ͣ��������
				v.setForMinutes(stopminutes(v.getInTime()));
				//����ʻ���豸��
				v.setOutMac(info.getSerialno() );
				//�ж��Ƿ�Ϊ������
				if(iswhitelist>0){//�ǰ�����
					res.setInfo("ok");//�ǰ�����ֱ�ӿ�բ
					res.setData(info.getLicense()+OUT);
					//�޸�������Ϣ
					v.setArFee(0.0);//����Ϊ0
					mapper.updateOutInfo(v);
				}else if(info.getIsTollBooths()!=1){//���ǰ����� ����Ҳ�����շѿ�  ֱ�ӿ�
					//�������
					double money=BillingUtil.computationalCost(v.getFeeModel(),v.getInTime());
					//��֤��ǰ�豸�Ƿ񻹴������豸  //����
					res.setInfo("ok");//��բ
					res.setData(info.getLicense()+OUT);
					//�޸�������Ϣ
					v.setArFee(money);//����
					s.setMoney(money);// ���ͷ���
					mapper.updateOutInfo(v);
				}else{//���ǰ������������շѿ�
					if(ischild){//������ͣ����  
						System.out.println("***************������ͣ����*********************");
						//�õ���ͣ������ͣ����¼  //�жϵ�ǰʱ����Ƿ���ڽ�������   
						com.sharebo.entity.VehicieInfo chiden_v=mapper.getChidenVehiceByCarNOAndCommIdAndInTime(info.getCommId(), info.getLicense(), v.getInTime());
						//�����������    ��֤�Ƿ�ɷ�  ͣ��������
						if(chiden_v!=null){// ����ͣ����ͣ��
							System.out.println("����ͣ����ͣ����������ͣ��ʱ���ǣ�"+chiden_v.getForMinutes());
							System.out.println(chiden_v.toString());
							double chiden_v_money=0;  //���շ���
							//��֤�Ƿ��Ѿ��շ�
							if(chiden_v.getFeeTime()==null&&chiden_v.getPaidInFee()==null){//�����û���շ�
								//��ǰ���ü��ڲ�����
								chiden_v_money=chiden_v.getArFee();
								//System.out.println("��ͣ����û���շ�Ŷ����������Ӧ���շѣ�"+chiden_v.getArFee());
							}else{//�Ѿ��շ�
								//ֻ�յ�ǰ����
								chiden_v_money=0;
								//System.out.println("��ͣ�����Ѿ��շ���Ŷ����������");
							}
							/********************������;�Ƿ�ʱ begin*******(�����Ƿ��շѣ�����Ҫ����)******************/
							//������;�Ƿ�ʱ
							//�ⲿͣ����������ȥ��ͣ����������  �õ�����бȽ� �Ƿ����30����
							long stayMinutes=v.getForMinutes()-chiden_v.getForMinutes();//��;����������
							if(stayMinutes>20){//��;����ʱ��  ���ж���Ʒ�
								//�ٴ��ⲿ�Ʒ�  :�м䶺���ķ���
								double staymoney=BillingUtil.computationalCost(v.getFeeModel(),stayMinutes);
								chiden_v_money+=staymoney;
								//System.out.println("�����Ʒѣ�"+staymoney);
							}
							//System.out.println("�����涺����ʱ���ǣ�"+v.getForMinutes());
							//�ܹ������� ��
							//System.out.println(chiden_v_money+"    �ܹ���������ô�࣬����");
							/********************������;�Ƿ�ʱ end *************************/
							v.setArFee(chiden_v_money);//���ö���ʱ���δ�շѵ�Ǯ
						}else{
							/**********************û������ͣ����ͣ�������벻������ͣ����ҵ��һ�£�*******************/
							//ֱ�ӼƷ�
							v.setArFee(BillingUtil.computationalCost(v.getFeeModel(),v.getInTime()));
						}
					}else{//��������ͣ����
						//��֤�Ƿ��Ѿ��շ�
						/************* ��������ͣ����*********************");*/
						//ֱ�ӼƷ�
						v.setArFee(BillingUtil.computationalCost(v.getFeeModel(),v.getInTime()));
					}
					//�������
					//double money=(BillingUtil.computationalCost(v.getFeeModel(),v.getInTime()));
					double money=v.getArFee();
					System.out.println(money+"      ���յķ�����");
					if(money==0){
						res.setInfo("ok");
						res.setData(info.getLicense()+OUT);
					}else{
						res.setInfo("no");
						res.setData(info.getLicense()+"�շ�"+(int)money+"Ԫ");
					}
					v.setArFee(Math.rint(v.getArFee()));
					v.setArFee(Double.valueOf(v.getArFee().toString().substring(0, v.getArFee().toString().indexOf("."))));
					mapper.updateOutInfo(v);
					//���Ͽճ�λ
					mapper.updatePsConutByCommId(info.getCommId(), +1);
					//System.out.println("����㣺"+v.toString());
				}
			}
			//�����ƶ�vehicleId
			
			if(v==null){
				s.setVehicleId("");
				s.setFeeInstructions("û���볡��¼");
			}else{
				s.setMoney(v.getArFee());
				s.setVehicleId(v.getVehicleId());
				s.setFeeInstructions("<span>ͣ��ʱ����<b>"+v.getForMinutes()+"</b>���ӣ�ʻ��ʱ�䣺<b>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(v.getInTime())+"</b></span> ");
			}
		}else{
			//��������
			// ���ݴ���
			log.error("���ݴ���,С������״̬����������" + inandout+"�������ݿ⣡");
		}
		
		System.out.println("��������==============================================");
		System.out.println("���ƺ�: " + info.getLicense());
		System.out.println("������" + inandout);
		System.out.println("��բ��Ϣ��" + res.getInfo());
		System.out.println("LED��ʾ��Ϣ��" +res.getData());
		System.out.println("��������==============================================");
		//����mq��Ϣ
		try {
			service.sendMessage("sendShareboManager",JSONObject.fromObject(s).toString());
		} catch (Exception e) {
			System.out.println("mq��������ʧ�ܣ����������Ƿ�ʱ��");
			log.error("mq��������ʧ�ܣ����������Ƿ�ʱ��");
		}
		return res;
	}
	
	/**
	 * ������������
	 * @param inTime
	 * @return
	 */
	public static long stopminutes(Date inTime){
		Calendar c1=Calendar.getInstance();
		c1.setTime(inTime);//���ý�ȥʱ��
		//��������ʱ����������
		Calendar c2=Calendar.getInstance();
		//����ʱ����������
		long diffMinutes = (c2.getTimeInMillis()-c1.getTimeInMillis()) / (60 * 1000);
		return diffMinutes;
	}
}
