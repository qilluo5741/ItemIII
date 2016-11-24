package com.sharebo.entity;
/**
 * 进入参数
 * @author niewei
 *
 */
public class InOut_Info {
	private String commId;
	private String serialno;//设备号
	private String license;//车牌号
	private Integer confidence;//识别结果可行度 1-100
	private String imageFile;//大图片  base64加密
	private String imageFragmentFile;//小图片 base64加密
	private Integer isTollBooths;//是否是收费口 1:是  其他不是
	
	public Integer getIsTollBooths() {
		return isTollBooths;
	}
	public void setIsTollBooths(Integer isTollBooths) {
		this.isTollBooths = isTollBooths;
	}
	public Integer getConfidence() {
		return confidence;
	}
	public void setConfidence(Integer confidence) {
		this.confidence = confidence;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getImageFragmentFile() {
		return imageFragmentFile;
	}
	public void setImageFragmentFile(String imageFragmentFile) {
		this.imageFragmentFile = imageFragmentFile;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	
	public InOut_Info(String commId, String serialno, String license,
			Integer confidence, String imageFile, String imageFragmentFile) {
		super();
		this.commId = commId;
		this.serialno = serialno;
		this.license = license;
		this.confidence = confidence;
		this.imageFile = imageFile;
		this.imageFragmentFile = imageFragmentFile;
	}
	public InOut_Info() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "InOut_Info [commId=" + commId + ", serialno=" + serialno
				+ ", license=" + license + ", confidence=" + confidence
				+ ", imageFile=" + imageFile + ", imageFragmentFile="
				+ imageFragmentFile + "]";
	}
	public String getCommId() {
		return commId;
	}
	public void setCommId(String commId) {
		this.commId = commId;
	}
	
}
