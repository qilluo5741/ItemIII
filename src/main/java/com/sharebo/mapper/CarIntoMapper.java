package com.sharebo.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CarIntoMapper {
	/**
	 * �õ���ǰ�豸�Ƿ����
	 * 
	 * @param serialno
	 * @return
	 */
	public int getCarIsInout(@Param("serialno") String serialno);

	/**
	 * ��֤�����Ƿ�Ϊ������
	 * 
	 * @param carNo
	 * @return
	 */
	public Integer valCarIsWhiteList(@Param("carNo") String carNo,
			@Param("serialno") String serialno);

	// ͨ���豸�õ�������
	public Map<String, Object> getPartnerBySerialno(
			@Param("serialno") String serialno);

	// ͨ��С��Id��ѯ�Ƿ������ͣ����
	public int getCommcommparent(@Param("commId") String commId);

	// ��ѯ�Ӽ�����İ�����
	public int getChidenWByCommIdAndCarNo(@Param("commId") String commId,
			@Param("carNo") String carNo);

	// �޸ĳ�λ����
	public Integer updatePsConutByCommId(@Param("commId") String commId,
			@Param("number") Integer number);

	// ����볡��¼
	public Integer addInVehicie(com.sharebo.entity.VehicieInfo v);

	// ����ͣ����������ѯ�շ�ģʽ
	public String getParkFeeModelByCommId(@Param("commId") String commId);

	// ��ѯ��λ����
	public Map<String, Integer> getpsCountAndIsBanOnForeignCarsByCommId(
			@Param("commId") String commId);

	// �õ��볡��Ϣ
	public com.sharebo.entity.VehicieInfo getOnDataByCarNo(
			@Param("commId") String commId, @Param("carNo") String carNo);

	// �Ʒ��޸�ʻ����Ϣ
	public int updateOutInfo(com.sharebo.entity.VehicieInfo v);

	// ���ݳ��ƺ�С��Id �ⲿ����ʱ�� ��ѯ
	public com.sharebo.entity.VehicieInfo getChidenVehiceByCarNOAndCommIdAndInTime(
			@Param("commId") String commId, @Param("carNo") String carNo,
			@Param("inTime") Date inTime);

	// ����С��Id��ѯȫ��δ�����ĳ�����Ϣ
	public List<com.sharebo.entity.MatchLicensePlateInfo> getvehicleAllBycommId(
			@Param("commId") String commId);

	// <!-- ͨ����������Id��ѯ��Ϣ -->
	public com.sharebo.entity.VehicieInfo getvehicleByvehicleId(
			@Param("vehicleId") String vehicleId);
}
