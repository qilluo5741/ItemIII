package com.sharebo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Timestamp {
	@JsonProperty("Timeval")
	private Timeval timeval;

	public Timeval getTimeval() {
		return timeval;
	}

	public void setTimeval(Timeval timeval) {
		this.timeval = timeval;
	}
	
}
