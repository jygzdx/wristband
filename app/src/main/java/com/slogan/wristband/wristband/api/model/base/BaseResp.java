package com.slogan.wristband.wristband.api.model.base;

import java.io.Serializable;

/**
 * 接口返回数据基类
 * @author quanz
 *
 */
public class BaseResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	public BaseResp() {
		
    }
	
	/**
	 * 返回结果代码
	 */
	private int code;
	
	/**
	 * 时间戳
	 */
	private long timestamp = System.currentTimeMillis();
	
	/**
	 * 具体的错误信息
	 */
	private String msg;
	
	/**
	 * Exception类
	 */
	private String exception;
 
	/**
	 * 是否成功,200表示成功，其他都是失败
	 */
	private boolean success = false;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
