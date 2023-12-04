package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.appdoctruyen.adapter.adapterTruyen;
import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.Truyen;

import java.util.ArrayList;

public class ManTimKiem extends AppCompatActivity {

    ListView listView;
    EditText edt;
    ArrayList<Truyen> TruyenArrayList;
    ArrayList<Truyen> arrayList;
    adapterTruyen adapterTruyen;
    databasedoctruyen databasedoctruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_tim_kiem);

        listView=findViewById(R.id.listviewTimKiem);
        edt = findViewById(R.id.timkiem);
        initList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(ManTimKiem.this,ManNoiDung.class);
                int idt=TruyenArrayList.get(position).getID();
                databasedoctruyen.increaseLuotXem(idt);
                String tent= arrayList.get(position).getTenTruyen();
                String noidungt= arrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });

        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }
    private void filter(String text){
        arrayList.clear();
        ArrayList<Truyen> filteredList=new ArrayList<>();

        for(Truyen item: TruyenArrayList){
            if(item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
                arrayList.add(item);
            }
        }
        adapterTruyen.filterList(filteredList);
    }

    private void initList() {
        TruyenArrayList = new ArrayList<>();

        arrayList=new ArrayList<>();
        databasedoctruyen=new databasedoctruyen(this);
        Cursor cursor=databasedoctruyen.getData2();
        while(cursor.moveToNext()){
            int id= cursor.getInt(0);
            String tentruyen=cursor.getString(1);
            String noidung=cursor.getString(2);
            String anh=cursor.getString(3);
            int theloai=cursor.getInt(4);
            int luotxem=cursor.getInt(5);
            int id_tk=cursor.getInt(6);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,theloai,luotxem,id_tk));
            arrayList.add(new Truyen(id,tentruyen,noidung,anh,theloai,luotxem,id_tk));

            adapterTruyen=new adapterTruyen(getApplicationContext(),TruyenArrayList);
            listView.setAdapter(adapterTruyen);
        }
        cursor.moveToFirst();
        cursor.close();
    }

}