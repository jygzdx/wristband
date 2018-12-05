package com.slogan.wristband.wristband.api.model.base;

import java.util.List;

/**
 * 分页返回接口数据类
 * @author quanz
 *
 * @param <T>
 */
public class PageResp<T extends Object> extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 返回结果数据
	 */
	private List<T> data;

	/**
	 * 当前页号
	 */
	private Integer page;

	/**
	 * 每页大小
	 */
	private Integer limit;

	/**
	 * 页数量
	 */
	private Integer pageCount;

	/**
	 * 行数
	 */
	private Integer rowCount;

	/**
	 * 总数
	 */
	private Integer total;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
