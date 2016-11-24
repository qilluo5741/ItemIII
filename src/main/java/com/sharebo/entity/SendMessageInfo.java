package com.sharebo.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发送消息信息
 * @author niewei
 *
 */
public class SendMessageInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vehicleId;//行驶Id
	private String carNo;//车牌号
	private String commId;//小区号
	private String carFrom;
	private String eqNo;//设备号
	private String smImg;//小图片
	private String bigImg;//大图片
	@SuppressWarnings("unused")
	private String date;
	private Integer isoutin;//是否进出 1：进 2：出
	private Double money;//费用
	private String feeInstructions;//收费模式说明
	public String getCarNo() {
		return carNo;
	}
	
	public String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getCarFrom() {
		return carFrom;
	}

	public void setCarFrom(String carFrom) {
		this.carFrom = carFrom;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCommId() {
		return commId;
	}
	public void setCommId(String commId) {
		this.commId = commId;
	}
	public String getEqNo() {
		return eqNo;
	}
	public void setEqNo(String eqNo) {
		this.eqNo = eqNo;
	}
	public String getSmImg() {
		return smImg;
	}
	public void setSmImg(String smImg) {
		this.smImg = smImg;
	}
	public String getBigImg() {
		return bigImg;
	}
	public void setBigImg(String bigImg) {
		this.bigImg = bigImg;
	}
	public Integer getIsoutin() {
		return isoutin;
	}
	public void setIsoutin(Integer isoutin) {
		this.isoutin = isoutin;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getFeeInstructions() {
		return feeInstructions;
	}
	public void setFeeInstructions(String feeInstructions) {
		this.feeInstructions = feeInstructions;
	}
	public SendMessageInfo() {
		super();
	}
	public SendMessageInfo(String carNo, String commId, String eqNo,
			String smImg, String bigImg, Integer isoutin,String carFrom,String vehicleId) {
		super();
		this.carNo = carNo;
		this.commId = commId;
		this.eqNo = eqNo;
		this.smImg = smImg;
		this.bigImg = bigImg;
		this.isoutin = isoutin;
		this.carFrom=carFrom;
		this.vehicleId=vehicleId;
	}
	public SendMessageInfo(String carNo, String commId, String eqNo,
			String smImg, String bigImg, Integer isoutin, Double money,
			String feeInstructions) {
		super();
		this.carNo = carNo;
		this.commId = commId;
		this.eqNo = eqNo;
		this.smImg = smImg;
		this.bigImg = bigImg;
		this.isoutin = isoutin;
		this.money = money;
		this.feeInstructions = feeInstructions;
	}
	@Override
	public String toString() {
		return "SendMessageInfo [carNo=" + carNo + ", commId=" + commId
				+ ", eqNo=" + eqNo + ", smImg=" + smImg + ", bigImg=" + bigImg
				+ ", isoutin=" + isoutin + ", money=" + money
				+ ", feeInstructions=" + feeInstructions + "]";
	}
	
}
