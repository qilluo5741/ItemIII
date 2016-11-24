package com.sharebo.entity.dto;

import java.io.Serializable;
/**
 * 小区信息
 * @author Administrator
 *
 */
public class CommunityDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String commId;
	private String cname;//小区名字
	private Integer psCount;//车位总数
	private String commparent;//小区父级
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
