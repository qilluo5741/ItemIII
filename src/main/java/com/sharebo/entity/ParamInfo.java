package com.sharebo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;



public class ParamInfo {
	@JsonProperty("AlarmInfoPlate")
	private AlarminfoPlate alarminfoplate;

	public void setAlarminfoplate(AlarminfoPlate alarminfoplate) {
		this.alarminfoplate = alarminfoplate;
	}

	public AlarminfoPlate getAlarminfoplate() {
		return alarminfoplate;
	}
}
