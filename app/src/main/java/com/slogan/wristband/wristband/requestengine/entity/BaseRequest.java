package com.slogan.wristband.wristband.requestengine.entity;



import com.slogan.wristband.wristband.requestengine.factory.HttpConnect;
import com.slogan.wristband.wristband.requestengine.factory.HttpMsg;
import com.slogan.wristband.wristband.requestengine.factory.IHttpListener;
import com.slogan.wristband.wristband.requestengine.factory.RequestTypeCode;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

public class BaseRequest implements RequestTypeCode, IHttpListener
{
    public int callbackInt;
    
    public Object callbackData;
    
    public JSONObject jsonObject;

    public HttpConnect httpConnect;
    
    public int type;
    
    public HttpMsg httpMsg;
    
    public String url;
    
    public void requestActions(Map<String, Object> map, int callbackInt, Object callbackData)
    {
        this.callbackData = callbackData;
        this.callbackInt = callbackInt;
        try
        {
            JSONObject obj = new JSONObject();
            Iterator<String> iter = map.keySet().iterator();
            while (iter.hasNext())
            {
                String key = iter.next();
                Object val = map.get(key);
                if (val != null)
                {
                    obj.put(key, val);
                }
            }
            this.jsonObject = obj;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //System.out.println(type+"----"+jsonObject.toString());
        httpConnect.postData(jsonObject);
    }
    

    @Override
    public void handleError(String err)
    {
        // TODO Auto-generated method stub
    }


    @Override
    public void decodeResult(String result) {

    }

}
