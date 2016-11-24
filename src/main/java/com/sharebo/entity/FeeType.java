package com.sharebo.entity;

import java.io.Serializable;
/**
 * 收费模式
 * @author Administrator
 *
 */
public class FeeType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Double money;
	private Integer feeModel;//(1:按小时收费   2:按次收费)
	private Double maxMoney;//最高费用  未设置 为-1
	private Integer freeMin;//免费分钟

	public Integer getFreeMin() {
		return freeMin;
	}
	public void setFreeMin(Integer freeMin) {
		this.freeMin = freeMin;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getFeeModel() {
		return feeModel;
	}
	public void setFeeModel(Integer feeModel) {
		this.feeModel = feeModel;
	}
	public Double getMaxMoney() {
		return maxMoney;
	}
	public void setMaxMoney(Double maxMoney) {
		this.maxMoney = maxMoney;
	}
	@Override
	public String toString() {
		return "FeeType [money=" + money + ", feeModel=" + feeModel
				+ ", maxMoney=" + maxMoney + "]";
	}
	
}
