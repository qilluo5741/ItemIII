package com.sharebo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Whitelist;
import com.sharebo.entity.dto.WhitelistDto;

public interface WhitelistService {
	/**
	 * ��Ӱ�����
	 * @return
	 */
	public int addWhitelist(WhitelistDto whitelist);
	/**
	 * ��֤�Ƿ����
	 * @param carNo
	 * @return
	 */
	public int valserwhitelistExists(String carNo,String commId);
	/**
	 * �޸İ�����
	 * @param whitelist
	 * @return
	 */
	public int updatewhitelist(WhitelistDto whitelist);
	/**
	 * ɾ��������
	 * @param commId
	 * @param carNo
	 * @return
	 */
	public int delectisfailure(String whitelistId);
	/**
	 * ����commId��ҳ��ѯ������
	 * @return
	 */
	public List<WhitelistDto> getselectWhitelist(Map<String,Object> map);
	/**
	 * ����commId��ҳ��ѯ����
	 * @param userid
	 * @return
	 */
	public int selectWhitelistCount(String commId,String carNo,String name,String address);
	/**
	 * �޸İ�����ʧЧ
	 * @param whitelistId(0 ����   1  ʧЧ)
	 * @return
	 * @throws Exception 
	 */
	public boolean updateIsfailure(String whitelistId,Integer isfailure,String periodvalidity,Integer chargeTimeType) throws Exception;
}
