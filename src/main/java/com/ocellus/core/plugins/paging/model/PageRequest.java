package com.ocellus.core.plugins.paging.model;


public class PageRequest implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ThreadLocal<PageRequest> tl = new ThreadLocal<PageRequest>();

	public PageRequest() {
	}

	public static PageRequest get() {
		PageRequest req = tl.get();
		if (req == null) {
			req = new PageRequest();
			tl.set(req);
		}
		return req;
	}
	public static void remove(){
		tl.remove();
	}
	// 是否分页
	private boolean paging;
	// 当前页码
	private int page;
	// 页面可显示行数
	private int rows;
	// 用于排序的列名
	private String sidx;
	// 排序的方式desc/asc
	private String sord;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public boolean isPaging() {
		return paging;
	}

	public void setPaging(boolean paging) {
		this.paging = paging;
	}

}
