package com.sharebo.entity;

import java.util.List;

public class Response_AlarmInfoPlate {
	private String info;//»Ø¸´ok  ¿ªÕ¢
	private String content;//
	private String is_pay;
	private List<SerialData> serialData;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIs_pay() {
		return is_pay;
	}
	public void setIs_pay(String is_pay) {
		this.is_pay = is_pay;
	}
	public List<SerialData> getSerialData() {
		return serialData;
	}
	public void setSerialData(List<SerialData> serialData) {
		this.serialData = serialData;
	}
}