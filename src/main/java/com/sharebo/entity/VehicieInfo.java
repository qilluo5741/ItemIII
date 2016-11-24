package com.sharebo.entity;

import java.util.Date;

/**
 * 车辆进出记录
 * @author niewei
 *
 */
public class VehicieInfo {
	private String vehicleId;
	private Date inTime;//进入时间
	private Date outTime;//出来时间
	private String carNo;//车牌号
	private String feeModel;//收费模式
	private Double paidInFee;//实收费用
	private Double  arFee;//应收费用
	private Integer iswl;//是否白名单
	private String inMac;//进入地址
	private String outMac;//驶出地址
	private long forMinutes;//停留分钟数
	private Date feeTime;//缴费时间
	
	public Date getFeeTime() {
		return feeTime;
	}
	public void setFeeTime(Date feeTime) {
		this.feeTime = feeTime;
	}
	public long getForMinutes() {
		return forMinutes;
	}
	public void setForMinutes(long forMinutes) {
		this.forMinutes = forMinutes;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getFeeModel() {
		return feeModel;
	}
	public void setFeeModel(String feeModel) {
		this.feeModel = feeModel;
	}
	public Double getPaidInFee() {
		return paidInFee;
	}
	public void setPaidInFee(Double paidInFee) {
		this.paidInFee = paidInFee;
	}
	public Double getArFee() {
		return arFee;
	}
	public void setArFee(Double arFee) {
		this.arFee = arFee;
	}
	public Integer getIswl() {
		return iswl;
	}
	public void setIswl(Integer iswl) {
		this.iswl = iswl;
	}
	public String getInMac() {
		return inMac;
	}
	public void setInMac(String inMac) {
		this.inMac = inMac;
	}
	public String getOutMac() {
		return outMac;
	}
	public void setOutMac(String outMac) {
		this.outMac = outMac;
	}
	public VehicieInfo(String vehicleId, Date inTime, Date outTime,
			String carNo, String feeModel, Double paidInFee, Double arFee,
			Integer iswl, String inMac, String outMac) {
		super();
		this.vehicleId = vehicleId;
		this.inTime = inTime;
		this.outTime = outTime;
		this.carNo = carNo;
		this.feeModel = feeModel;
		this.paidInFee = paidInFee;
		this.arFee = arFee;
		this.iswl = iswl;
		this.inMac = inMac;
		this.outMac = outMac;
	}
	public VehicieInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	//进去
	public VehicieInfo(String vehicleId, Date inTime, String carNo,
			Integer iswl, String inMac,String feeModel) {
		super();
		this.vehicleId = vehicleId;
		this.feeModel = feeModel;
		this.inTime = inTime;
		this.carNo = carNo;
		this.iswl = iswl;
		this.inMac = inMac;
	}
	//出来
	public VehicieInfo(String vehicleId, Date outTime, String carNo,
			Double paidInFee, Double arFee, String outMac) {
		super();
		this.vehicleId = vehicleId;
		this.outTime = outTime;
		this.carNo = carNo;
		this.paidInFee = paidInFee;
		this.arFee = arFee;
		this.outMac = outMac;
	}
	@Override
	public String toString() {
		return "VehicieInfo [vehicleId=" + vehicleId + ", inTime=" + inTime
				+ ", outTime=" + outTime + ", carNo=" + carNo + ", feeModel="
				+ feeModel + ", paidInFee=" + paidInFee + ", arFee=" + arFee
				+ ", iswl=" + iswl + ", inMac=" + inMac + ", outMac=" + outMac
				+ ", forMinutes=" + forMinutes + ", feeTime=" + feeTime + "]";
	}
	

}
