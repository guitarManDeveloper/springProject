package com.bigmoney.testproject.item.vo;

public class ItemVO extends Item{
	private String viewType = "list";
	private int pageSize = 10;
	private int offset;
	private int page = 1;
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		int offset = (page-1)*pageSize;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
