package com.sharebo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Plateresult {
	 private int bright;
	    @JsonProperty("carBright")
	    private int carbright;
	    @JsonProperty("carColor")
	    private int carcolor;
	    @JsonProperty("colorType")
	    private int colortype;
	    @JsonProperty("colorValue")
	    private int colorvalue;
	    private int confidence;
	    private int direction;
	    @JsonProperty("imagePath")
	    private String imagepath;
	    private String license;
	    private Location location;
	    @JsonProperty("timeStamp")
	    private Timestamp timestamp;
	    @JsonProperty("timeUsed")
	    private int timeused;
	    @JsonProperty("triggerType")
	    private int triggertype;
	    private int type;
		public int getBright() {
			return bright;
		}
		public void setBright(int bright) {
			this.bright = bright;
		}
		public int getCarbright() {
			return carbright;
		}
		public void setCarbright(int carbright) {
			this.carbright = carbright;
		}
		public int getCarcolor() {
			return carcolor;
		}
		public void setCarcolor(int carcolor) {
			this.carcolor = carcolor;
		}
		public int getColortype() {
			return colortype;
		}
		public void setColortype(int colortype) {
			this.colortype = colortype;
		}
		public int getColorvalue() {
			return colorvalue;
		}
		public void setColorvalue(int colorvalue) {
			this.colorvalue = colorvalue;
		}
		public int getConfidence() {
			return confidence;
		}
		public void setConfidence(int confidence) {
			this.confidence = confidence;
		}
		public int getDirection() {
			return direction;
		}
		public void setDirection(int direction) {
			this.direction = direction;
		}
		public String getImagepath() {
			return imagepath;
		}
		public void setImagepath(String imagepath) {
			this.imagepath = imagepath;
		}
		public String getLicense() {
			return license;
		}
		public void setLicense(String license) {
			this.license = license;
		}
		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
		public Timestamp getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Timestamp timestamp) {
			this.timestamp = timestamp;
		}
		public int getTimeused() {
			return timeused;
		}
		public void setTimeused(int timeused) {
			this.timeused = timeused;
		}
		public int getTriggertype() {
			return triggertype;
		}
		public void setTriggertype(int triggertype) {
			this.triggertype = triggertype;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
	    
}
