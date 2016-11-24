package com.sharebo.util;

import java.util.List;

import com.sharebo.entity.MatchLicensePlateInfo;

/**
 * ��ʶ��
 * @author niewei
 *
 */
public class MatchLicensePlateUtil {
	//ͨ��һ�鳵�Ƽ���
	public static MatchLicensePlateInfo calculate(List<MatchLicensePlateInfo> matchLicensePlateInfos,String carNo){
		MatchLicensePlateInfo resM=null;
		char[] ch=new char[carNo.length()];
		//ƴװ
		for(int i=0;i<carNo.length();i++){
		  ch[i]=carNo.charAt(i);
		}
		//����
		for (MatchLicensePlateInfo m : matchLicensePlateInfos) {
			try {
				int xs_=0;
				for (int i = 0; i < m.getCarNo().length(); i++) {
					if(ch[i]==m.getCarNo().charAt(i)){
						xs_+=1;
					}
				}
				if(resM==null){
					 resM=m;
				}else{
					if(resM.getNotSimilarity()<xs_){
						resM=new MatchLicensePlateInfo(m.getVehicleId(), m.getCarNo(), xs_);
					}
				}
			} catch (Exception e) {
				continue;
			}
		}
		return resM;
	}
}
