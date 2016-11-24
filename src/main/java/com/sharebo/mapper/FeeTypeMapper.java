package com.sharebo.mapper;

import org.apache.ibatis.annotations.Param;



public interface FeeTypeMapper {
	/**
	 * ����commId��ѯFeeId
	 * @param commId
	 * @return
	 */
	public String getFeeIdByCommId(@Param("commId")String commId);
	/**
	 * �޸��շ�ģʽ
	 * @param feeId
	 * @param json
	 * @return
	 */
	public int updateFeeType(@Param("feeId")String feeId,@Param("feeModelContext")String feeModelContext);
	/**
	 * ��ѯ�շ�ģʽ
	 * @param commId
	 * @return
	 */
	public String getFeeType(@Param("commId")String commId);
	/**
	 * �޸Ľ�����ʷ���ƺ�
	 * @param vehicleId
	 * @return
	 */
	public int updateCarNoByVehicleId(@Param("vehicleId")String vehicleId,@Param("carNo")String carNo);
	/**
	 * ȷ���շ�
	 * @param payType
	 * @param paidInFee
	 * @param carType
	 * @return
	 */
	public int confirmCharge(@Param("payType")Integer payType,@Param("paidInFee")Double paidInFee,@Param("carType")Integer carType,@Param("vehicleId")String vehicleId);
}
