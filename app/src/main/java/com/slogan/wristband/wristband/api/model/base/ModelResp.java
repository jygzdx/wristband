package com.slogan.wristband.wristband.api.model.base;

/**
 * 接口对象返回类
 * @author quanz
 *
 * @param <T>
 */
public class ModelResp<T> extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 返回结果数据
	 */
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
