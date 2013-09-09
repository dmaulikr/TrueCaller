package com.example.truecaller;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private ArrayList<ContactInfo> data;
	private static LayoutInflater inflater = null;
	private Activity context;

	// we need just to implement getView() method
	public MyAdapter(ArrayList<ContactInfo> data, Activity context) {

		this.data = data;
		this.context = context;
		this.inflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return data.get(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (view == null)
			view = inflater.inflate(R.layout.row, null);

		
		TextView name = (TextView) view.findViewById(R.id.Cname);
		TextView phone = (TextView) view.findViewById(R.id.Cphone);
		name.setText(data.get(position).getName());
		phone.setText(data.get(position).getPhone_number());
		return view;
	}

}
