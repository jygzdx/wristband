package com.slogan.wristband.wristband.requestengine.factory;

/**
 * 
 * @author andydroid
 *
 */
public interface IHttpListener {
	
	 void handleError(String err) ;

	void decodeResult(String result) ;
}
