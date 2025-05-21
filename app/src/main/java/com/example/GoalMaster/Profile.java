package com.example.GoalMaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Profile extends AppCompatActivity {

    private ImageView btnBack, btnSetting;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Ánh xạ button Back và thiết lập sự kiện
        btnBack = findViewById(R.id.btnBack);

        btnSetting.setOnClickListener(this::showPopupMenu);  // Hiển thị PopupMenu

        // Nút quay lại HomeActivity
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        // Nút setting hiển thị popup menu
        btnSetting = findViewById(R.id.btnset);
        btnSetting.setOnClickListener(this::showPopupMenu);
        // Lấy tên người dùng từ SharedPreferences và hiển thị
        TextView txtTenNguoiDung = findViewById(R.id.txtTenNguoiDung);

    // Lấy SharedPreferences tên "user_prefs", kiểu MODE_PRIVATE
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String tenNguoiDung = prefs.getString("user_name", "Người dùng");

// Gán tên lên TextView
        txtTenNguoiDung.setText(tenNguoiDung);

    }

    private void showPopupMenu(android.view.View view) {
        PopupMenu popupMenu = new PopupMenu(Profile.this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();

        inflater.inflate(R.menu.nav_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
        popupMenu.show();

    }

    private boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.User) {
            // Chỉnh sửa hồ sơ
            Intent intent = new Intent(Profile.this, Editprofile.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.repasss) {
            // Đổi mật khẩu
            Intent intent = new Intent(Profile.this, ChangePass.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.logout) {
            // Đăng xuất và quay lại màn hình đăng nhập
            Intent intent = new Intent(Profile.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();  // Đóng Profile
            return true;
        } else {
            return false;  // Trường hợp không có mục nào được chọn

            startActivity(new Intent(Profile.this, Editprofile.class));
            return true;
        } else if (id == R.id.repasss) {
            startActivity(new Intent(Profile.this, ChangePass.class));
            return true;
        } else if (id == R.id.logout) {
            Intent intent = new Intent(Profile.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        } else {
            return false;
        }
    }
}
