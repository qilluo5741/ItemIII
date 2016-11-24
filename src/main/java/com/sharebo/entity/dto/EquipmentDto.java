package com.sharebo.entity.dto;

import java.io.Serializable;

public class EquipmentDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String equipmentId;
	private String commId;
	private String equipmentNumber;
	private Integer isinout;
	private String equipmentName;
	private String cname;
	private String caddress;
	private String commparent;
	private Integer isTollBooths;//(是否收费亭 (0:不是  1:是) 针对出口设备)
	public Integer getIsTollBooths() {
		return isTollBooths;
	}
	public void setIsTollBooths(Integer isTollBooths) {
		this.isTollBooths = isTollBooths;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getCommId() {
		return commId;
	}
	public void setCommId(String commId) {
		this.commId = commId;
	}
	public String getEquipmentNumber() {
		return equipmentNumber;
	}
	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}
	public Integer getIsinout() {
		return isinout;
	}
	public void setIsinout(Integer isinout) {
		this.isinout = isinout;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCaddress() {
		return caddress;
	}
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	public String getCommparent() {
		return commparent;
	}
	public void setCommparent(String commparent) {
		this.commparent = commparent;
	}
	@Override
	public String toString() {
		return "EquipmentDto [equipmentId=" + equipmentId + ", commId=" + commId + ", equipmentNumber="
				+ equipmentNumber + ", isinout=" + isinout + ", equipmentName=" + equipmentName + ", cname=" + cname
				+ ", caddress=" + caddress + "]";
	}
}
