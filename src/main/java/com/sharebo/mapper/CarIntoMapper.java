package com.sharebo.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CarIntoMapper {
	/**
	 * 得到当前设备是否进出
	 * 
	 * @param serialno
	 * @return
	 */
	public int getCarIsInout(@Param("serialno") String serialno);

	/**
	 * 验证车辆是否为白名单
	 * 
	 * @param carNo
	 * @return
	 */
	public Integer valCarIsWhiteList(@Param("carNo") String carNo,
			@Param("serialno") String serialno);

	// 通过设备得到合作方
	public Map<String, Object> getPartnerBySerialno(
			@Param("serialno") String serialno);

	// 通过小区Id查询是否存在子停车场
	public int getCommcommparent(@Param("commId") String commId);

	// 查询子集下面的白名单
	public int getChidenWByCommIdAndCarNo(@Param("commId") String commId,
			@Param("carNo") String carNo);

	// 修改车位总数
	public Integer updatePsConutByCommId(@Param("commId") String commId,
			@Param("number") Integer number);

	// 添加入场记录
	public Integer addInVehicie(com.sharebo.entity.VehicieInfo v);

	// 根据停车场主键查询收费模式
	public String getParkFeeModelByCommId(@Param("commId") String commId);

	// 查询车位总数
	public Map<String, Integer> getpsCountAndIsBanOnForeignCarsByCommId(
			@Param("commId") String commId);

	// 得到入场信息
	public com.sharebo.entity.VehicieInfo getOnDataByCarNo(
			@Param("commId") String commId, @Param("carNo") String carNo);

	// 计费修改驶出信息
	public int updateOutInfo(com.sharebo.entity.VehicieInfo v);

	// 根据车牌号小区Id 外部进入时间 查询
	public com.sharebo.entity.VehicieInfo getChidenVehiceByCarNOAndCommIdAndInTime(
			@Param("commId") String commId, @Param("carNo") String carNo,
			@Param("inTime") Date inTime);

	// 根据小区Id查询全部未出来的车辆信息
	public List<com.sharebo.entity.MatchLicensePlateInfo> getvehicleAllBycommId(
			@Param("commId") String commId);

	// <!-- 通过车辆进出Id查询信息 -->
	public com.sharebo.entity.VehicieInfo getvehicleByvehicleId(
			@Param("vehicleId") String vehicleId);
}
