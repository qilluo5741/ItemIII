package com.sharebo.entity;
/**
 * 匹配车牌
 * @author niewei
 *
 */
public class MatchLicensePlateInfo {
	private String vehicleId;//出入记录
	private String carNo;//车牌号码
	private int notSimilarity;//不相似度
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public int getNotSimilarity() {
		return notSimilarity;
	}
	public void setNotSimilarity(int notSimilarity) {
		this.notSimilarity = notSimilarity;
	}
	public MatchLicensePlateInfo(String vehicleId, String carNo,
			int notSimilarity) {
		super();
		this.vehicleId = vehicleId;
		this.carNo = carNo;
		this.notSimilarity = notSimilarity;
	}
	public MatchLicensePlateInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
