package com.example.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.appdoctruyen.adapter.adapterchuyenmuc;
import com.example.appdoctruyen.adapter.adapterthongtin;
import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.Taikhoan;
import com.example.appdoctruyen.model.Truyen;
import com.example.appdoctruyen.adapter.adapterTruyen;
import com.example.appdoctruyen.model.chuyenmuc;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView, listViewNew, listViewThongTin;
    DrawerLayout drawerLayout;

    String email;
    String tentaikhoan;
    ArrayList<Truyen> TruyenArrayList, TruyenArrayList1;
    adapterTruyen adapterTruyen;
    ArrayList<chuyenmuc> chuyenmucArrayList;
    ArrayList<Taikhoan> taikhoanArrayList;
    databasedoctruyen databasedoctruyen;

    adapterthongtin adapterthongtin;
    adapterchuyenmuc adapterchuyenmuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasedoctruyen = new databasedoctruyen(this);

        Intent intentpq = getIntent();
        int i=intentpq.getIntExtra("phanq", 0);
        int idd=intentpq.getIntExtra("idd",0);
        email=intentpq.getStringExtra("email");
        tentaikhoan=intentpq.getStringExtra("tentaikhoan");


        AnhXa();
        ActionViewFlipper();
        ActionBar();
        invalidateOptionsMenu();
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,ManNoiDung.class);
                int idt=TruyenArrayList.get(position).getID();
                databasedoctruyen.increaseLuotXem(idt);
                String tent=TruyenArrayList.get(position).getTenTruyen();
                String noidungt=TruyenArrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    if(i==2){
                        Intent intent=new Intent(MainActivity.this,ManAdmin.class);
                        //Gửi id
                        intent.putExtra("Id",idd);
                         startActivity(intent);

                    } else{
                        Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();
                        Log.e("Đăng bài :","Bạn không có quyền");
                    }
                } else if(position ==1){
                    Intent intent = new Intent(MainActivity.this, ManThongTin.class);
                    startActivity(intent);
                } else if(position ==2){
                    Intent intent = new Intent(MainActivity.this, ManTheLoai.class);
                    startActivity(intent);
                } else if(position ==3){
                    Intent intent = new Intent(MainActivity.this, ManLuotXem.class);
                    startActivity(intent);
                } else if(position== 4){
                    finish();
                }
            }
        });
    }

    private void AnhXa(){
        toolbar= findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper= findViewById(R.id.viewFlipper);
        listViewNew= findViewById(R.id.listviewNew);
        listView= findViewById(R.id.listviewmanhinchinh);
        listViewThongTin= findViewById(R.id.listviewthongtin);
        navigationView=findViewById(R.id.navigationView);
        drawerLayout=findViewById(R.id.drawerlayout);

        TruyenArrayList=new ArrayList<>();
        Cursor cursor1 = databasedoctruyen.getData1();
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
            listViewNew.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();

        taikhoanArrayList=new ArrayList<>();
        taikhoanArrayList.add(new Taikhoan(tentaikhoan,email));

        adapterthongtin =new adapterthongtin(this,R.layout.navigation_thongtin,taikhoanArrayList);
        listViewThongTin.setAdapter(adapterthongtin);

        chuyenmucArrayList=new ArrayList<>();
        chuyenmucArrayList.add(new chuyenmuc("Đăng bài",R.drawable.ic_post));
        chuyenmucArrayList.add(new chuyenmuc("Thông tin",R.drawable.ic_face));
        chuyenmucArrayList.add(new chuyenmuc("Thể loại",R.drawable.ic_category));
        chuyenmucArrayList.add(new chuyenmuc("Xếp hạng",R.drawable.ic_rank));
        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất",R.drawable.ic_logout));

        adapterchuyenmuc=new adapterchuyenmuc(this,R.layout.chuyenmuc,chuyenmucArrayList);
        listView.setAdapter(adapterchuyenmuc);
    }

    private void ActionViewFlipper(){

        TruyenArrayList1 =new ArrayList<>();
        Cursor cursor2 = databasedoctruyen.getData2();
        while(cursor2.moveToNext()){
            int id= cursor2.getInt(0);
            String tentruyen=cursor2.getString(1);
            String noidung=cursor2.getString(2);
            String anh=cursor2.getString(3);
            int theloai= cursor2.getInt(4);
            int luotxem= cursor2.getInt(5);
            int id_tk=cursor2.getInt(6);

            TruyenArrayList1.add(new Truyen(id,tentruyen,noidung,anh,theloai,luotxem,id_tk));
            adapterTruyen=new adapterTruyen(getApplicationContext(), TruyenArrayList);
            listViewNew.setAdapter(adapterTruyen);
        }
        cursor2.moveToFirst();
        cursor2.close();
        List<Integer> numbers = new ArrayList<>();
        for (int j = 0; j < TruyenArrayList1.size(); j++) {
            numbers.add(j);
        }
        Collections.shuffle(numbers);

        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            int randomNumber = numbers.get(i);
            String lanhqc=TruyenArrayList1.get(randomNumber).getAnh();
            Picasso.get().load(lanhqc).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,ManNoiDung.class);

                    int idt=TruyenArrayList1.get(randomNumber).getID();
                    databasedoctruyen.increaseLuotXem(idt);
                    String tent=TruyenArrayList1.get(randomNumber).getTenTruyen();
                    String noidungt=TruyenArrayList1.get(randomNumber).getNoiDung();
                    intent.putExtra("tentruyen",tent);
                    intent.putExtra("noidung",noidungt);
                    startActivity(intent);
                }
            });
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_out);
        viewFlipper.setInAnimation(animation_slide_in);

        adapterchuyenmuc = new adapterchuyenmuc(this, R.layout.chuyenmuc,chuyenmucArrayList);
    }

    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.menu1:
//                Intent intent= new Intent(MainActivity.this, ManTimKiem.class);
//                startActivity(intent);
//                break;
//
//            default:
//                break;
//        }
        int itemId = item.getItemId();

        if (itemId == R.id.menu1) {
            Intent intent = new Intent(MainActivity.this, ManTimKiem.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }


}