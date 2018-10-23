package com.slogan.wristband.wristband.db;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBOpenHandler extends SQLiteOpenHelper
{
    
    private static final int DB_VERSION = 1;
    
    public static final String TABLE_NAME_MESSAGES = "messages";
    
    public static final String TABLE_NAME_MESSAGES_REMOTE = "messages_remote";
    
    public DBOpenHandler(Context context, String name, CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }
    
    public DBOpenHandler(Context context, String userId)
    {
        super(context, userId, null, DB_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE IF NOT EXISTS messages(k_id INTEGER primary key autoincrement,id VARCHAR(50) ,m_id VARCHAR(50),jumpUrl VARCHAR(400), body VARCHAR(200), image VARCHAR(200),head VARCHAR(200),level INTEGER,gender INTEGER,relatioin INTEGER,type INTEGER, fromUid  BIGINT,fromUsername VARCHAR(50), toUid  BIGINT ,toName  VARCHAR(50) ,sendtime BIGINT, lon real, lat real,sound VARCHAR(200),giftIcon VARCHAR(100),giftCharm INTEGER,giftExp INTEGER ,state INTEGER, direction INTEGER, sound_time INTEGER,imageLarge VARCHAR(200),imageSmall VARCHAR(200),systemContent VARCHAR(200),systemTitle VARCHAR(200),systemImage VARCHAR(200),systemButton VARCHAR(20),systemTarget INTEGER,systemId VARCHAR(20),systemUrl VARCHAR(200),systemName VARCHAR(20),field1 INTEGER,field2 INTEGER,field3 INTEGER,field4 INTEGER,field5 INTEGER,field6 INTEGER,field7 INTEGER,field8 INTEGER,field9 INTEGER,field10 INTEGER,field11 VARCHAR(200),field12 VARCHAR(200),field13 VARCHAR(200),field14 VARCHAR(200),field15 VARCHAR(200),field16 VARCHAR(200),field17 VARCHAR(200),field18 VARCHAR(200),field19 VARCHAR(200),field20 VARCHAR(200),field21 VARCHAR(50),field22 VARCHAR(50),field23 VARCHAR(50),field24 VARCHAR(50),field25 VARCHAR(50),field26 VARCHAR(50),field27 VARCHAR(50),field28 VARCHAR(50),field29 VARCHAR(50),field30 VARCHAR(50))");
        db.execSQL("CREATE TABLE IF NOT EXISTS messages_remote(id VARCHAR(50) ,m_id VARCHAR(50),body VARCHAR(200),head VARCHAR(200),type INTEGER,username VARCHAR(50), uid  BIGINT,level INTEGER,gender INTEGER,relatioin INTEGER,sendtime BIGINT,state INTEGER, direction INTEGER,unreadcount INTEGER,field1 INTEGER,field2 INTEGER,field3 INTEGER,field4 INTEGER,field5 INTEGER,field6 INTEGER,field7 INTEGER,field8 INTEGER,field9 INTEGER,field10 INTEGER,field11 VARCHAR(200),field12 VARCHAR(200),field13 VARCHAR(200),field14 VARCHAR(200),field15 VARCHAR(200),field16 VARCHAR(200),field17 VARCHAR(200),field18 VARCHAR(200),field19 VARCHAR(200),field20 VARCHAR(200),field21 VARCHAR(50),field22 VARCHAR(50),field23 VARCHAR(50),field24 VARCHAR(50),field25 VARCHAR(50),field26 VARCHAR(50),field27 VARCHAR(50),field28 VARCHAR(50),field29 VARCHAR(50),field30 VARCHAR(50))");

    }
    
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
    {
        // TODO Auto-generated method stub
        Log.v("DBOpenHandler", "数据升级" + arg1);
        
        onCreate(arg0);
    }
    
    @TargetApi(11)
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
        
    }
    
}
