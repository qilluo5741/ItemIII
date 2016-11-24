package com.sharebo.entity;
/**
 * �������
 * @author niewei
 *
 */
public class InOut_Info {
	private String commId;
	private String serialno;//�豸��
	private String license;//���ƺ�
	private Integer confidence;//ʶ�������ж� 1-100
	private String imageFile;//��ͼƬ  base64����
	private String imageFragmentFile;//СͼƬ base64����
	private Integer isTollBooths;//�Ƿ����շѿ� 1:��  ��������
	
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
