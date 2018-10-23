package com.example.trung.ngdngdulch;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trung.ngdngdulch.Database.Userdata;

public class Dangky extends AppCompatActivity {
    EditText txt_TTK, txt_MK, txt_HoTen, txt_NgaySinh, txt_SDT, txt_DiaChi;
    Button bt_DK;
    RadioButton rbt_boy, rbt_girl;
    Userdata data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        data = new Userdata(this);
        data.open();
        findID();
        bt_DK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insert();
                Toast.makeText(Dangky.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(Dangky.this, Login.class);
                startActivity(in);

            }
        });
    }

    public void findID() {
        txt_DiaChi = findViewById(R.id.DK_txtEmail);
        txt_HoTen = findViewById(R.id.DK_txtNguoi);
        txt_MK = findViewById(R.id.DK_txtPass);
        txt_NgaySinh = findViewById(R.id.DK_txtDate);
        txt_SDT = findViewById(R.id.DK_txtSdt);
        txt_TTK = findViewById(R.id.DK_txtName);
        bt_DK = findViewById(R.id.DK_bt);
        rbt_boy = findViewById(R.id.DK_rbtBoy);
        rbt_girl = findViewById(R.id.DK_rbtGirl);
    }

    public void Insert() {
        String TTK = txt_TTK.getText().toString();
        String MK = txt_MK.getText().toString();
        String Hoten = txt_HoTen.getText().toString();
        String NgaySinh = txt_NgaySinh.getText().toString();
        String Sdt = txt_SDT.getText().toString();
        String diaChi = txt_DiaChi.getText().toString();

        data.Insert(TTK, MK, Hoten, NgaySinh, Sdt, diaChi);
    }

//    public void show() {
//        String name = txt_show.getText().toString();
//        controller = data.select();
//        String str1 = "";
//        while (controller.moveToNext()) {
//            String str = controller.getString(controller.getColumnIndex("Tentaikhoan"));
//            str1 = str1 + "Name " + str;
//        }
//        txt_show.setText(str1);
//    }
}
