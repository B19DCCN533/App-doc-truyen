package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.model.chuyenmuc;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class adapterchuyenmuc extends BaseAdapter {

    private Context context;
    private int layout;
    private List<chuyenmuc> chuyenmucList;

    public adapterchuyenmuc(Context context, int layout, List<chuyenmuc> chuyenmucList) {
        this.context = context;
        this.layout = layout;
        this.chuyenmucList = chuyenmucList;
    }


    @Override
    public int getCount() {
        return chuyenmucList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=inflater.inflate(layout,null);

        ImageView imgV =(ImageView) convertView.findViewById(R.id.imgchuyenmuc);
        TextView txtV = (TextView) convertView.findViewById(R.id.textviewTenchuyenmuc);
        chuyenmuc cn = chuyenmucList.get(position);
        txtV.setText(cn.getTenchuyenmuc());
        imgV.setImageResource(cn.getHinhanhchuyenmuc());
//        Picasso.get().load(cn.getHinhanhchuyenmuc()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(imgV);

        return convertView;
    }
}
