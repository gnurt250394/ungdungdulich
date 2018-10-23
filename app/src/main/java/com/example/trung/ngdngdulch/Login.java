package com.example.trung.ngdngdulch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trung.ngdngdulch.Database.Databasehelper;
import com.example.trung.ngdngdulch.Database.Userdata;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText txt_name, txt_pass;
    private Button bt_Login;
    private TextView txt_DKLogin, txt_QMKLogin;
    private ImageButton ibt_fbLogin, ibt_ggLogin;
    private CheckBox cb_MK;
    private static final String TTK = "ttk";
    private static final String MK = "mk";
    private static final String REMEMBER = "remember";

    Intent in;
    Userdata data;
    Cursor table;
    SharedPreferences pre;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findID();
        data  = new Userdata(this);
        data.open();

        // khởi tạo SharePreferences
        pre = getSharedPreferences("pre", 0);
        loadDataCB();
        editor = pre.edit();

        // khởi tạo sự kiện khi click vào các button
        txt_DKLogin.setOnClickListener(this);
        bt_Login.setOnClickListener(this);
        txt_QMKLogin.setOnClickListener(this);
        ibt_fbLogin.setOnClickListener(this);
        ibt_ggLogin.setOnClickListener(this);

    }
        //xét ID cho các đối tượng trong layout
    public void findID() {
        txt_name = findViewById(R.id.login_txtName);
        txt_pass = findViewById(R.id.login_txtPass);
        txt_DKLogin = findViewById(R.id.login_txtDK);
        txt_QMKLogin = findViewById(R.id.login_txtQMK);
        bt_Login = findViewById(R.id.login_Bt);
        ibt_fbLogin = findViewById(R.id.login_ibtFB);
        ibt_ggLogin = findViewById(R.id.login_ibtGG);
        cb_MK = findViewById(R.id.login_cbMK);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == bt_Login.getId()) {
            clickLogin();
        } else if (v.getId() == txt_DKLogin.getId()) {
            clickDK();
        } else if (v.getId() == txt_QMKLogin.getId()) {
            clickQMK();
        } else if (v.getId() == ibt_ggLogin.getId()) {
            clickLoginGG();
        } else if (v.getId() == ibt_fbLogin.getId()) {
            clickLoginFB();
        }
    }
    // Tạo sự kiện đăng nhập
    public void clickLogin() {
        checkPass();
    }
    // Tạo sự kiện đăng ký
    public void clickDK() {
        in = new Intent(this, Dangky.class);
        startActivity(in);
    }
    // Tạo sự kiện Quên mật khẩu
    public void clickQMK() {
        in = new Intent(this, QuenMK.class);
        startActivity(in);
    }
//    Tạo sự kiện đăng nhập bằng facebook
    public void clickLoginFB() {
        String name = txt_name.getText().toString();
        data.delete(name);
    }
//    Tạo sự kiện đăng nhập bằng Google
    public void clickLoginGG() {

    }
    //  Lưu giá trị Đăng nhập vào preferences
    public void writeCheckBox(String name, String pass) {
        editor.putString(TTK, name);
        editor.putString(MK, pass);
        editor.putBoolean(REMEMBER, cb_MK.isChecked());
        editor.commit();

    }

    // hiển thị thông tin lưu trong preferences ra màn hình đăng nhập
    public void loadDataCB() {
        if (pre.getBoolean(REMEMBER, false)) {
            String name = pre.getString(TTK, "");
            String pass = pre.getString(MK, "");
            txt_name.setText(name);
            txt_pass.setText(pass);
            cb_MK.setChecked(true);
        } else {
            cb_MK.setChecked(false);
        }
    }

    // Kiểm tra Tài khoản và mật khẩu đăng nhập để gán cho sự kiện đăng nhập
    public void checkPass() {
        table = data.select();
        String name = txt_name.getText().toString().trim();
        String pass = txt_pass.getText().toString().trim();
        if (name.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else{
        while (table.moveToNext()) {
            String ttk = table.getString(table.getColumnIndex("Tentaikhoan"));
            String mk = table.getString(table.getColumnIndex("Matkhau"));
            if (cb_MK.isChecked()) {
                writeCheckBox(name, pass);

            } else {
                editor.clear();
                editor.commit();

            }
            if (name.equals(ttk) && pass.equals(mk)) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                in = new Intent(this, Home.class);
                startActivity(in);
                break;

            }
            if (name.equals(ttk) == false || pass.equals(mk) == false) {
                Toast.makeText(this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                break;
            }

        }

        }


    }
}
