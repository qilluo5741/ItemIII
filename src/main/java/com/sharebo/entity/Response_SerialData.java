package com.sharebo.entity;

import java.util.List;

public class Response_SerialData {
	private String info;
	private List<SerialData> serialData;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<SerialData> getSerialData() {
		return serialData;
	}
	public void setSerialData(List<SerialData> serialData) {
		this.serialData = serialData;
	}
	
}
