package com.sharebo.mapper;

import org.apache.ibatis.annotations.Param;



public interface FeeTypeMapper {
	/**
	 * 根据commId查询FeeId
	 * @param commId
	 * @return
	 */
	public String getFeeIdByCommId(@Param("commId")String commId);
	/**
	 * 修改收费模式
	 * @param feeId
	 * @param json
	 * @return
	 */
	public int updateFeeType(@Param("feeId")String feeId,@Param("feeModelContext")String feeModelContext);
	/**
	 * 查询收费模式
	 * @param commId
	 * @return
	 */
	public String getFeeType(@Param("commId")String commId);
	/**
	 * 修改进口历史车牌号
	 * @param vehicleId
	 * @return
	 */
	public int updateCarNoByVehicleId(@Param("vehicleId")String vehicleId,@Param("carNo")String carNo);
	/**
	 * 确认收费
	 * @param payType
	 * @param paidInFee
	 * @param carType
	 * @return
	 */
	public int confirmCharge(@Param("payType")Integer payType,@Param("paidInFee")Double paidInFee,@Param("carType")Integer carType,@Param("vehicleId")String vehicleId);
}
