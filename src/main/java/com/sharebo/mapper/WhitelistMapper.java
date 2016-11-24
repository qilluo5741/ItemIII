package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Whitelist;
import com.sharebo.entity.dto.WhitelistDto;
import com.sun.org.glassfish.gmbal.ParameterNames;

public interface WhitelistMapper {
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
	public int valserwhitelistExists(@Param("carNo") String carNo,@Param("commId")String commId);
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
	public int delectisfailure(@Param("whitelistId")String whitelistId);
	/**
	 * ����commId��ҳ��ѯ������
	 * @param map
	 * @return
	 */
	public List<WhitelistDto> getselectWhitelist(Map<String,Object> map);
	/**
	 * ����commId��ҳ��ѯ����
	 * @param userid
	 * @return
	 */
	public int selectWhitelistCount(@Param("commId")String commId,@Param("carNo")String carNo,@Param("name")String name,@Param("address")String address);
	/**
	 * �޸İ�������Ч��
	 * @param commId
	 * @param carNo
	 * @param periodvalidity
	 * @param name
	 * @param address
	 * @param phone
	 * @param chargeTimeType
	 * @return
	 */
	public int updatePeriodvalidity(@Param("commId")String commId,@Param("carNo")String carNo,@Param("periodvalidity")String periodvalidity,@Param("name")String name,@Param("address")String address,@Param("phone")String phone,@Param("chargeTimeType")Integer chargeTimeType);
	/**
	 * �޸İ�����ʧЧ
	 * @param whitelistId(0 ����   1  ʧЧ)
	 * @return
	 */
	public int updateIsfailure(@Param("whitelistId")String whitelistId,@Param("isfailure")Integer isfailure);
	/**
	 * ��ѯ������������Ϣ
	 * @param whitelistId
	 * @return
	 */
	public WhitelistDto getWhitelistInfo(@Param("whitelistId")String whitelistId);
}
