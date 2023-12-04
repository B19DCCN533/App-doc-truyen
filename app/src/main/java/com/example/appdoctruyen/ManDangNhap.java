package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appdoctruyen.database.databasedoctruyen;

public class ManDangNhap extends AppCompatActivity {

    EditText editTaikhoan, editMatkhau;
    Button btnDangNhap, btnDangky;
    databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_nhap);

        AnhXa();

        databasedoctruyen = new databasedoctruyen(this);

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManDangNhap.this, ManDangKy.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentaikhoan=editTaikhoan.getText().toString();
                String matkhau=editMatkhau.getText().toString();

                Cursor cursor=databasedoctruyen.getData();

                while(cursor.moveToNext()){
                    String datatentakhoan= cursor.getString(1);
                    String datamatkhau= cursor.getString(2);
                    if(datatentakhoan.equals(tentaikhoan)&&datamatkhau.equals(matkhau)){
                        int phanquyen=cursor.getInt(4);
                        int idd=cursor.getInt(0);
                        String email=cursor.getString(3);
                        String tentk=cursor.getString(1);

                        Intent intent= new Intent(ManDangNhap.this, MainActivity.class);

                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);

                        startActivity(intent);
                    }
                }
                cursor.moveToFirst();
                cursor.close();
            }
        });

    }

    public void AnhXa(){
        editMatkhau=findViewById(R.id.matkhau);
        editTaikhoan=findViewById(R.id.taikhoan);
        btnDangNhap=findViewById(R.id.dangnhap);
        btnDangky=findViewById(R.id.dangky);

    }
}