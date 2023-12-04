package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appdoctruyen.adapter.adapterTruyen;
import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.Truyen;

import java.util.ArrayList;

public class ManAdmin extends AppCompatActivity {

    ListView listView;
    Button buttonThem;
    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_admin);

        listView= findViewById(R.id.listviewAdmin);
        buttonThem=findViewById(R.id.btnThemtruyen);

        initList();

        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                int id=intent1.getIntExtra("Id",0);
                Intent intent= new Intent(ManAdmin.this, ManDangBai.class);
                intent.putExtra("Id",id);
                startActivity(intent);

            }
        });

        //click item long sẽ xóa
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogDelete(position);
                return false;
            }
        });
    }
    private void DialogDelete(int position){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogdelete);
        dialog.setCanceledOnTouchOutside(false);
        Button btnYes= dialog.findViewById(R.id.btnCo);
        Button btnNo= dialog.findViewById(R.id.btnKhong);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idtruyen=TruyenArrayList.get(position).getID();

                databasedoctruyen.Delete(idtruyen);

//                Intent intent=new Intent(ManAdmin.this, MainActivity.class);
                finish();
//                startActivity(intent);
                Toast.makeText(ManAdmin.this,"Xoá truyện thành công", Toast.LENGTH_SHORT).show();

            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void initList() {
        TruyenArrayList= new ArrayList<>();

        databasedoctruyen=new databasedoctruyen(this);

        Cursor cursor1=databasedoctruyen.getData2();

        while (cursor1.moveToNext()){
            int id= cursor1.getInt(0);
            String tentruyen=cursor1.getString(1);
            String noidung=cursor1.getString(2);
            String anh=cursor1.getString(3);
            int theloai=cursor1.getInt(4);
            int luotxem=cursor1.getInt(5);
            int id_tk=cursor1.getInt(6);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,theloai,luotxem,id_tk));
            adapterTruyen=new adapterTruyen(getApplicationContext(),TruyenArrayList);
            listView.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();
    }
}