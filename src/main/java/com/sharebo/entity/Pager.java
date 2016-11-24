package com.sharebo.entity;

import java.util.List;

/**
 * 分页工具
 * @author niewei
 */
public class Pager<T> {
	private Integer pageIndex;// 当前页
	private Integer pageSize;// 查询的条数
	private Integer totalPages;// 总页数
	private Integer totalNumber;// 总条数
	private List<T> list;

	public Integer getPageIndex() {
		return pageIndex;
	}

	// 设置页
	public void setPageIndex(Integer pageIndex) {
		// 如果页数小于等于0 设置成1
		if (pageIndex <= 0) {
			this.pageIndex = 1;
		}
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize <= 0) {
			this.pageSize = 1;
		}
		this.pageSize = pageSize;
	}

	// 获取总页数
	public Integer getTotalPages() {
		// totalPages 是否为null
		if(totalPages==null){
			setTotalPages();
		}
		return totalPages;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public void setTotalPages() {
		Integer tp = this.totalNumber / this.pageSize;// 整取得的页数
		if (this.totalNumber % this.pageSize != 0) {
			tp += 1;
		}
		this.totalPages = tp;
	}
	// 是否有上一页
	public Boolean isPreviousPage() {
		return this.pageIndex == 1 ? false : true;
	}

	// 是否有下一页
	public Boolean isNextPage() {
		return this.getTotalPages() == this.pageIndex ? false : true;
	}

	// 第一页页码
	public Integer getFirstPage() {
		return 1;
	}

	// 最后一页的页码
	public Integer getLastPage() {
		return this.getTotalPages();
	}
}
