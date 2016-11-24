package com.sharebo.service;

public interface FeeTypeService {
	/**
	 * 根据commId查询FeeId
	 * @param commId
	 * @return
	 */
	public String getFeeIdByCommId(String commId);
	/**
	 * 修改收费模式
	 * @param feeId
	 * @param json
	 * @return
	 */
	public int updateFeeType(String feeId,String feeModelContext);
	/**
	 * 查询收费模式
	 * @param commId
	 * @return
	 */
	public String getFeeType(String commId);
	/**
	 * 修改进口历史车牌号
	 * @param vehicleId
	 * @return
	 */
	public int updateCarNoByVehicleId(String vehicleId,String carNo);
	/**
	 * 确认收费
	 * @param payType
	 * @param paidInFee
	 * @param carType
	 * @return
	 */
	public int confirmCharge(Integer payType,Double paidInFee,Integer carType,String vehicleId);
}
