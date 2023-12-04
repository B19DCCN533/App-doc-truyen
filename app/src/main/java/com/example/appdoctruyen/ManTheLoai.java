package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.appdoctruyen.adapter.adapterTruyen;
import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.Truyen;

import java.util.ArrayList;

public class ManTheLoai extends AppCompatActivity {

    ListView listView1, listView2, listView3;
    databasedoctruyen databasedoctruyen;
    ArrayList<Truyen> TruyenArrayList1,TruyenArrayList2,TruyenArrayList3;
    adapterTruyen adapterTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_the_loai);
        databasedoctruyen = new databasedoctruyen(this);

        initList();
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ManTheLoai.this,ManNoiDung.class);
                int idt=TruyenArrayList1.get(position).getID();
                databasedoctruyen.increaseLuotXem(idt);
                String tent=TruyenArrayList1.get(position).getTenTruyen();
                String noidungt=TruyenArrayList1.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ManTheLoai.this,ManNoiDung.class);
                int idt=TruyenArrayList2.get(position).getID();
                databasedoctruyen.increaseLuotXem(idt);
                String tent=TruyenArrayList2.get(position).getTenTruyen();
                String noidungt=TruyenArrayList2.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ManTheLoai.this,ManNoiDung.class);
                int idt=TruyenArrayList3.get(position).getID();
                databasedoctruyen.increaseLuotXem(idt);
                String tent=TruyenArrayList3.get(position).getTenTruyen();
                String noidungt=TruyenArrayList3.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });
    }

    private void initList() {
        listView1= findViewById(R.id.listview1);
        listView2= findViewById(R.id.listview2);
        listView3= findViewById(R.id.listview3);
        TruyenArrayList1=new ArrayList<>();
        Cursor cursor1 = databasedoctruyen.getData3(1);
        while(cursor1.moveToNext()){
            int id= cursor1.getInt(0);
            String tentruyen=cursor1.getString(1);
            String noidung=cursor1.getString(2);
            String anh=cursor1.getString(3);
            int theloai= cursor1.getInt(4);
            int luotxem= cursor1.getInt(5);
            int id_tk=cursor1.getInt(6);

            TruyenArrayList1.add(new Truyen(id,tentruyen,noidung,anh,theloai,luotxem,id_tk));
            adapterTruyen=new adapterTruyen(getApplicationContext(), TruyenArrayList1);
            listView1.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();

        TruyenArrayList2=new ArrayList<>();
        Cursor cursor2 = databasedoctruyen.getData3(2);
        while(cursor2.moveToNext()){
            int id= cursor2.getInt(0);
            String tentruyen=cursor2.getString(1);
            String noidung=cursor2.getString(2);
            String anh=cursor2.getString(3);
            int theloai= cursor2.getInt(4);
            int luotxem=cursor2.getInt(5);
            int id_tk=cursor2.getInt(6);

            TruyenArrayList2.add(new Truyen(id,tentruyen,noidung,anh,theloai,luotxem,id_tk));
            adapterTruyen=new adapterTruyen(getApplicationContext(), TruyenArrayList2);
            listView2.setAdapter(adapterTruyen);
        }
        cursor2.moveToFirst();
        cursor2.close();

        TruyenArrayList3=new ArrayList<>();
        Cursor cursor3 = databasedoctruyen.getData3(3);
        while(cursor3.moveToNext()){
            int id= cursor3.getInt(0);
            String tentruyen=cursor3.getString(1);
            String noidung=cursor3.getString(2);
            String anh=cursor3.getString(3);
            int theloai= cursor3.getInt(4);
            int luotxem= cursor3.getInt(5);
            int id_tk=cursor3.getInt(6);

            TruyenArrayList3.add(new Truyen(id,tentruyen,noidung,anh,theloai,luotxem,id_tk));
            adapterTruyen=new adapterTruyen(getApplicationContext(), TruyenArrayList3);
            listView3.setAdapter(adapterTruyen);
        }
        cursor3.moveToFirst();
        cursor3.close();
    }
}