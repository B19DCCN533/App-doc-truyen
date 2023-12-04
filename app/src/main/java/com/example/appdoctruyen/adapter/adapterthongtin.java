package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.model.Taikhoan;

import java.util.List;

public class adapterthongtin  extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Taikhoan> taikhoanList;

    public adapterthongtin(Context context, int layout, List<Taikhoan> taikhoanList) {
        this.context = context;
        this.layout = layout;
        this.taikhoanList = taikhoanList;
    }

    @Override
    public int getCount() {
        return taikhoanList.size();
    }

    @Override
    public Object getItem(int postion) {
        return null;
    }

    @Override
    public long getItemId(int postion) {
        return 0;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        TextView txtTenTaikhoan=(TextView) convertView.findViewById(R.id.TEXT_NAME);
        TextView txtEmail=(TextView) convertView.findViewById(R.id.TEXT_Gmail);

        Taikhoan taikhoan=taikhoanList.get(postion);
        txtTenTaikhoan.setText(taikhoan.getmTenTaikhoan());
        txtEmail.setText(taikhoan.getmEmail());
        return convertView;
    }
}
