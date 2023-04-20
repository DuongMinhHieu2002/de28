package com.example.de28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;

    private EditText edtht,edtns,edtdc;
    private RadioGroup g1,g2;
    private RadioButton r1,r2,r3,r4,r5;
    private Button btnluu,btnht;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        innitdata();
        edtht = findViewById(R.id.edtht);
        edtns = findViewById(R.id.edtns);
        edtdc = findViewById(R.id.edtdc);
        btnluu = findViewById(R.id.btl);
        btnht = findViewById(R.id.btnht);
        g1 = findViewById(R.id.g1);
        g2 = findViewById(R.id.g2);
        r1 = findViewById(R.id.nam);
        r2 = findViewById(R.id.nu);
        r3 = findViewById(R.id.cntt);
        r4 = findViewById(R.id.kt);
        r5 = findViewById(R.id.sp);
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertRow();
            }
        });
        btnht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }



    private void insertRow() {
        String hoten = edtht.getText().toString();
        String ngaysinh = edtns.getText().toString();
        String diachi = edtdc.getText().toString();
        String gioitinh;
        int ckeck1 = g1.getCheckedRadioButtonId();
        if (ckeck1 == R.id.nam) gioitinh = r1.getText().toString();
        else gioitinh = r2.getText().toString();
        String truong;
        int check2 = g2.getCheckedRadioButtonId();
        if ( check2 == R.id.kt) truong =r3.getText().toString();
        else if (check2 == R.id.cntt) {
            truong = r4.getText().toString();
        }
        else truong = r5.getText().toString();
        String sql = "INSERT INTO tbsinhvien (hoten,ngaysinh,diachi,gioitinh,truong) VALUES ('"+hoten+"','"+ngaysinh+"','"+diachi+"','"+gioitinh+"','"+truong+"')";
        db.execSQL(sql);
    }


    private void innitdata() {
        db = openOrCreateDatabase("qlsinhvien.db",MODE_PRIVATE,null);
        String sql ="CREATE TABLE IF NOT EXISTS tbsinhvien (id integer primary key autoincrement,hoten text,ngaysinh text,diachi text,gioitinh text,truong text)";
        db.execSQL(sql);
    }
}