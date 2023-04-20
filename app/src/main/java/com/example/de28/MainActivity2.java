package com.example.de28;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private SQLiteDatabase db;
    private ListView lvsv;
    private ArrayAdapter<sinhvien> adapter;
    private ArrayList<sinhvien> arrayList = new ArrayList<>();

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        innitdata();
        lvsv  = findViewById(R.id.lv);
       adapter = new ArrayAdapter<sinhvien>(this,0, arrayList){
           @NonNull
           @Override
           public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
               LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item,null);

                TextView hoten = convertView.findViewById(R.id.txtht);
               TextView ngaysinh = convertView.findViewById(R.id.txtns);
               TextView diachi = convertView.findViewById(R.id.txtdc);
               TextView gioitinh = convertView.findViewById(R.id.txtgt);
               TextView truong = convertView.findViewById(R.id.txtt);

                sinhvien s = arrayList.get(position);
                hoten.setText(arrayList.get(position).hoten);
                ngaysinh.setText(arrayList.get(position).ngaysinh);
               diachi.setText(arrayList.get(position).diachi);
               truong.setText(arrayList.get(position).truongyeuthich);

               return convertView;
           }
       };
       loadData();
       lvsv.setAdapter(adapter);
    }

    private void loadData() {
        arrayList.clear();
        String sql = "SELECT * FROM tbsinhvien";
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String hoten = cursor.getString(1);
            String ngaysinh = cursor.getString(2);
            String diachi = cursor.getString(3);
            String gioitinh = cursor.getString(4);
            String truong = cursor.getString(5);
//            String data = id + "-" + hoten + "-" + ngaysinh + "-" + gioitinh + "-" + truong + "-" +"\n";

            sinhvien s = new sinhvien(id,hoten,ngaysinh,diachi,gioitinh,truong);

            arrayList.add(s);
            cursor.moveToNext();
        }
        adapter.notifyDataSetChanged();
    }

    private void innitdata() {
        db = openOrCreateDatabase("qlsinhvien.db",MODE_PRIVATE,null);
        String sql ="CREATE TABLE IF NOT EXISTS tbsinhvien (id integer primary key autoincrement,hoten text,ngaysinh text,diachi text,gioitinh text,truong text)";
        db.execSQL(sql);
    }
}