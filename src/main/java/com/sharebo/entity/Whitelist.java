package com.sharebo.entity;

import java.io.Serializable;

public class Whitelist implements Serializable{
	private static final long serialVersionUID = 1L;
	private String commId;//����
	private String carNo;//���ƺ�
	private String periodvalidity;//��������Ч��
	private String name;//��������
	private String address;//������ַ�Ż�
	private String phone;//�����绰
	public String getCommId() {
		return commId;
	}
	public void setCommId(String commId) {
		this.commId = commId;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getPeriodvalidity() {
		return periodvalidity;
	}
	public void setPeriodvalidity(String periodvalidity) {
		this.periodvalidity = periodvalidity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
