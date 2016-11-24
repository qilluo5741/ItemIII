package com.sharebo.service;

public interface FeeTypeService {
	/**
	 * ����commId��ѯFeeId
	 * @param commId
	 * @return
	 */
	public String getFeeIdByCommId(String commId);
	/**
	 * �޸��շ�ģʽ
	 * @param feeId
	 * @param json
	 * @return
	 */
	public int updateFeeType(String feeId,String feeModelContext);
	/**
	 * ��ѯ�շ�ģʽ
	 * @param commId
	 * @return
	 */
	public String getFeeType(String commId);
	/**
	 * �޸Ľ�����ʷ���ƺ�
	 * @param vehicleId
	 * @return
	 */
	public int updateCarNoByVehicleId(String vehicleId,String carNo);
	/**
	 * ȷ���շ�
	 * @param payType
	 * @param paidInFee
	 * @param carType
	 * @return
	 */
	public int confirmCharge(Integer payType,Double paidInFee,Integer carType,String vehicleId);
}
