package com.sharebo.service;


public interface CommunityService {
	/**
	 * �޸ĳ�λ����
	 * @param parkCount
	 * @param commId
	 * @return
	 */
	public int updateparkCountBycommId(int psCount,String commId);
	/**
	 * ��ѯ��λ����
	 * @param commId
	 * @return
	 */
	public int getPsCountByCommId(String commId);
	/**
	 * ��ѯС������
	 * @param commId
	 * @return
	 */
	public String getCommParent(String commId);
	/**
	 * ��ѯС���Ƿ�����������������
	 * @param commId
	 * @return
	 */
	public int getisBanOnForeignCars(String commId);
	/**
	 * �޸�С�������������������״̬
	 * @param commId
	 * @return
	 */
	public int updateisBanOnForeignCars(String commId,Integer isBanOnForeignCars);
}
