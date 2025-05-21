package com.example.GoalMaster;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Chuyển đến LoginActivity
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish(); // Đóng MainActivity để không quay lại bằng nút Back
    }
}
