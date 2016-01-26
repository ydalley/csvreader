package com.ebsl.utils;

import java.util.List;

public class PageBean {
	
	private List data;
	private int draw;
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	private long recordsTotal;
	private long recordsFiltered;
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
	

	
}
