package com.slogan.wristband.wristband.api.model.base;

/**
 * token令牌返回类
 * @author quanz
 *
 */
public class TokenResp extends BaseResp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * token
	 */
	private String token;
	
	/**
	 * 多少秒过期
	 */
	private long expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
}
