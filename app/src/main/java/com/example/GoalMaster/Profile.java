package com.example.GoalMaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

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

        // Ánh xạ view cho profile và cấu hình windowInsets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ button Back và thiết lập sự kiện
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());  // Đóng Activity

        // Ánh xạ button Setting và thiết lập sự kiện
        btnSetting = findViewById(R.id.btnset);
        btnSetting.setOnClickListener(this::showPopupMenu);  // Hiển thị PopupMenu
    }

    private void showPopupMenu(android.view.View view) {
        PopupMenu popupMenu = new PopupMenu(Profile.this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.nav_menu, popupMenu.getMenu());  // Load menu từ XML

        popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);  // Xử lý sự kiện click item
        popupMenu.show();  // Hiển thị menu
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
        }
    }
}
