package com.sharebo.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class WhitelistDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String whitelistId;//主键
	private String commId;//主键
	private String carNo;//车牌号
	private String periodvalidity;//白名单有效期
	private Date entrytime;
	private String name;//车主名字
	private String address;//车主地址门户
	private String phone;//车主电话
	private String cname;
	private Integer isDisable;//是否失效(0: 已失效   1:未失效)
	private Integer chargeTimeType;//收费时间类型(0: 月付  1：季付   2：年付)
	public Integer getChargeTimeType() {
		return chargeTimeType;
	}
	public void setChargeTimeType(Integer chargeTimeType) {
		this.chargeTimeType = chargeTimeType;
	}
	public Integer getIsDisable() {
		return isDisable;
	}
	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}
	public String getWhitelistId() {
		return whitelistId;
	}
	public void setWhitelistId(String whitelistId) {
		this.whitelistId = whitelistId;
	}
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
	public Date getEntrytime() {
		return entrytime;
	}
	public void setEntrytime(Date entrytime) {
		this.entrytime = entrytime;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
