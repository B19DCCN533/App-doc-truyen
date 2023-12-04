package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.Taikhoan;

public class ManDangKy extends AppCompatActivity {

    EditText editDKTaikhoan, editDKMatKhau, editDKEmail;
    Button btnDKDangky, btnDKDangNhap;
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_ky);

        AnhXa();
        databasedoctruyen = new databasedoctruyen(this);

        btnDKDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan=editDKTaikhoan.getText().toString();
                String matkhau=editDKMatKhau.getText().toString();
                String email=editDKEmail.getText().toString();

                Taikhoan taikhoan1=CreateTaikhoan();
                if(taikhoan.equals("")||matkhau.equals("")||email.equals("")){
                    Log.e("Thông báo:","Chưa nhập đầy đủ thông tin");
                }
                else{
                    databasedoctruyen.AddTaikhoan(taikhoan1);
                    Toast.makeText(ManDangKy.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private Taikhoan CreateTaikhoan(){
        String taikhoan=editDKTaikhoan.getText().toString();
        String matkhau=editDKMatKhau.getText().toString();
        String email=editDKEmail.getText().toString();
        int phanquyen=1;

        Taikhoan tk= new Taikhoan(taikhoan,matkhau,email,phanquyen);
        return tk;
    }

    private void AnhXa(){
        editDKEmail=findViewById(R.id.dkemail);
        editDKMatKhau=findViewById(R.id.dkmatkhau);
        editDKTaikhoan=findViewById(R.id.dktaikhoan);
        btnDKDangky=findViewById(R.id.dkdangky);
        btnDKDangNhap=findViewById(R.id.dkdangnhap);
    }
}