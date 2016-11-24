package com.sharebo.entity;

public class SerialData {
	private Integer serialChannel;
	private String data;
	private Integer dataLen;
	public Integer getSerialChannel() {
		return serialChannel;
	}
	public void setSerialChannel(Integer serialChannel) {
		this.serialChannel = serialChannel;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getDataLen() {
		return dataLen;
	}
	public void setDataLen(Integer dataLen) {
		this.dataLen = dataLen;
	}
}