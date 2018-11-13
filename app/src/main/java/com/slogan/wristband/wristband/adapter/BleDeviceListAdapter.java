/**  
* @Title: BleDeviceListAdapter.java
* @Package com.veclink.bledemo
* @Description: TODO(用一句话描述该文件做什么)
* @author Allen 
* @date 2014-9-6 上午11:26:44
* @version V1.0  
*/ 
package com.slogan.wristband.wristband.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.veclink.bracelet.bean.BluetoothDeviceBean;

/**
 * @author Allen
 *
 */
public class BleDeviceListAdapter  extends BaseAdapter{
	
	public List<BluetoothDeviceBean> listItems = new ArrayList<BluetoothDeviceBean>();
	private Context mContext;
	public BleDeviceListAdapter(Context mContext){
		this.mContext = mContext;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	
	public final class ViewHolder{
		public TextView name_view;
		public TextView address_view;
		public TextView rssi_view;
	}
	@Override
	public View getView(int position, View contentView, ViewGroup arg2) {
		ViewHolder holder;
		final BluetoothDeviceBean device = listItems.get(position);
		if(contentView==null){
			holder = new ViewHolder();
			contentView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
			holder.name_view = (TextView) contentView.findViewById(R.id.deviceName);
			holder.address_view = (TextView) contentView.findViewById(R.id.deviceAddr);
			holder.rssi_view = (TextView) contentView.findViewById(R.id.deviceRssi);
			contentView.setTag(holder);
		}else{
			holder = (ViewHolder) contentView.getTag();
		}
		
		holder.name_view.setText(device.getName());
		holder.address_view.setText(device.getAddress());
		holder.rssi_view.setText(String.valueOf(device.getRssi()));
		return contentView;
	}
	
	public void addDeviceItem(BluetoothDeviceBean device){
		this.listItems.add(device);
		Collections.sort(this.listItems);
		this.notifyDataSetChanged();
	}
	
	public void clearAllDevieceItem(){
		this.listItems.clear();
		this.notifyDataSetChanged();
	}

}
