package com.sharebo.entity.dto;

import java.io.Serializable;
/**
 * С����Ϣ
 * @author Administrator
 *
 */
public class CommunityDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String commId;
	private String cname;//С������
	private Integer psCount;//��λ����
	private String commparent;//С������
	public String getCommId() {
		return commId;
	}
	public void setCommId(String commId) {
		this.commId = commId;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getPsCount() {
		return psCount;
	}
	public void setPsCount(Integer psCount) {
		this.psCount = psCount;
	}
	public String getCommparent() {
		return commparent;
	}
	public void setCommparent(String commparent) {
		this.commparent = commparent;
	}
}
