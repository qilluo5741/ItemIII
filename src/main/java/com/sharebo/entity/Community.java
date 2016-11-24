package com.sharebo.entity;

import java.io.Serializable;

public class Community implements Serializable{
	private static final long serialVersionUID = 1L;
	private String commId;//Ö÷¼ü
	private int parkCount;
	public String getCommId() {
		return commId;
	}
	public void setCommId(String commId) {
		this.commId = commId;
	}
	public int getParkCount() {
		return parkCount;
	}
	public void setParkCount(int parkCount) {
		this.parkCount = parkCount;
	}
}
