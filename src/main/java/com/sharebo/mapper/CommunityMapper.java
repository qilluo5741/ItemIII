package com.sharebo.mapper;


import org.apache.ibatis.annotations.Param;


public interface CommunityMapper {
	/**
	 * �޸ĳ�λ����
	 * @param parkCount
	 * @param commId
	 * @return
	 */
	public int updateparkCountBycommId(@Param("psCount")int psCount,@Param("commId")String commId);
	/**
	 * ��ѯ��λ����
	 * @param commId
	 * @return
	 */
	public int getPsCountByCommId(@Param("commId")String commId);
	/**
	 * ��ѯС������
	 * @param commId
	 * @return
	 */
	public String getCommParent(@Param("commId")String commId);
	/**
	 * ��ѯС���Ƿ�����������������
	 * @param commId
	 * @return
	 */
	public int getisBanOnForeignCars(@Param("commId")String commId);
	/**
	 * �޸�С�������������������״̬
	 * @param commId
	 * @return
	 */
	public int updateisBanOnForeignCars(@Param("commId")String commId,@Param("isBanOnForeignCars")Integer isBanOnForeignCars);
}
