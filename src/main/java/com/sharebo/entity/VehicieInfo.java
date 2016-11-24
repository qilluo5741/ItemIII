package com.sharebo.entity;

import java.util.Date;

/**
 * ����������¼
 * @author niewei
 *
 */
public class VehicieInfo {
	private String vehicleId;
	private Date inTime;//����ʱ��
	private Date outTime;//����ʱ��
	private String carNo;//���ƺ�
	private String feeModel;//�շ�ģʽ
	private Double paidInFee;//ʵ�շ���
	private Double  arFee;//Ӧ�շ���
	private Integer iswl;//�Ƿ������
	private String inMac;//�����ַ
	private String outMac;//ʻ����ַ
	private long forMinutes;//ͣ��������
	private Date feeTime;//�ɷ�ʱ��
	
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
	//��ȥ
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
	//����
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
