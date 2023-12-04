package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.Truyen;

public class ManDangBai extends AppCompatActivity {

    EditText edtTenTruyen, edtNoiDung,edtAnh,edtTheloai;
    Button btnDangbai;
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_bai);

        edtTenTruyen=findViewById(R.id.dbTentruyen);
        edtNoiDung=findViewById(R.id.dbnoidung);
        edtAnh=findViewById(R.id.dbimg);
        edtTheloai=findViewById(R.id.dbtheloai);
        btnDangbai=findViewById(R.id.dbdangbai);

        databasedoctruyen = new databasedoctruyen(this);

        btnDangbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentruyen=edtTenTruyen.getText().toString();
                String noidung=edtNoiDung.getText().toString();
                String img=edtAnh.getText().toString();
                String sotheloai=edtTheloai.getText().toString();
                int theloai = Integer.valueOf(sotheloai);
                Truyen truyen=CreateTruyen();
                if(tentruyen.equals("")||noidung.equals("")||img.equals("")||sotheloai.equals("")){
                    Toast.makeText(ManDangBai.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    Log.e("ERR : ", "Nhập đầy đủ thông tin");
                }
                else{
                    databasedoctruyen.AddTruyen(truyen);
                    Toast.makeText(ManDangBai.this,"Đăng truyện thành công",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ManDangBai.this,ManAdmin.class);
                    finish();
                    startActivity(intent);
                }
            }
        });

    }

    private Truyen CreateTruyen(){
        String tentruyen=edtTenTruyen.getText().toString();
        String noidung=edtNoiDung.getText().toString();
        String img=edtAnh.getText().toString();
        int theloai = Integer.valueOf(edtTheloai.getText().toString());

        Intent intent=getIntent();
        int luotxem=0;
        int id=intent.getIntExtra("Id",0);
        Truyen truyen= new Truyen(tentruyen,noidung,img,theloai,luotxem,id);
        return truyen;

    }
}