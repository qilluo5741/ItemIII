package com.sharebo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
	@JsonProperty("RECT")
	private Rect rect;

	public void setRect(Rect rect) {
		this.rect = rect;
	}

	public Rect getRect() {
		return rect;
	}
}
