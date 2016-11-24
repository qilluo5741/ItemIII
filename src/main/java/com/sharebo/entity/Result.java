package com.sharebo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
	@JsonProperty("PlateResult")
	private Plateresult plateresult;

	public Plateresult getPlateresult() {
		return plateresult;
	}

	public void setPlateresult(Plateresult plateresult) {
		this.plateresult = plateresult;
	}

}
