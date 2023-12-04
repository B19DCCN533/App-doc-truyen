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

public class ManLuotXem extends AppCompatActivity {
    ListView listViewluotxem;
    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_luot_xem);
        databasedoctruyen = new databasedoctruyen(this);

        initList();
        listViewluotxem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ManLuotXem.this,ManNoiDung.class);
                int idt=TruyenArrayList.get(position).getID();
                databasedoctruyen.increaseLuotXem(idt);
                String tent=TruyenArrayList.get(position).getTenTruyen();
                String noidungt=TruyenArrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });
    }

    private void initList() {
        listViewluotxem= findViewById(R.id.listviewluotxem);
        TruyenArrayList=new ArrayList<>();
        Cursor cursor1 = databasedoctruyen.getData4();
        while(cursor1.moveToNext()){
            int id= cursor1.getInt(0);
            String tentruyen=cursor1.getString(1);
            String noidung=cursor1.getString(2);
            String anh=cursor1.getString(3);
            int theloai= cursor1.getInt(4);
            int luotxem= cursor1.getInt(5);
            int id_tk=cursor1.getInt(6);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,theloai,luotxem,id_tk));
            adapterTruyen=new adapterTruyen(getApplicationContext(), TruyenArrayList);
            listViewluotxem.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();
    }
}