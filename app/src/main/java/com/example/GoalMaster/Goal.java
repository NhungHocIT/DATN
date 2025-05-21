package com.example.GoalMaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Goal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_goal);

        // Xử lý padding cho hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Goal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các nút
        ImageButton btnBack = findViewById(R.id.btnBack);
        ImageButton btnOngoingGoals = findViewById(R.id.btnOngoingGoals);
        ImageButton btnCompletedGoals = findViewById(R.id.btnCompletedGoals);
        ImageButton btnFailedGoals = findViewById(R.id.btnFailedGoals);
        ImageButton btnAddGoal = findViewById(R.id.btnAddGoal);

        // Xử lý sự kiện khi nhấn nút Back
        btnBack.setOnClickListener(v -> finish());

        // Xử lý khi nhấn vào các nút chuyển màn hình
        btnOngoingGoals.setOnClickListener(v -> {
            Intent intent = new Intent(Goal.this, Doinggoal.class);
            startActivity(intent);
        });

        btnCompletedGoals.setOnClickListener(v -> {
            Intent intent = new Intent(Goal.this, Donegoal.class);
            startActivity(intent);
        });

        btnFailedGoals.setOnClickListener(v -> {
            Intent intent = new Intent(Goal.this, Failgoal.class);
            startActivity(intent);
        });

        // Xử lý khi nhấn nút thêm mục tiêu mới
        btnAddGoal.setOnClickListener(v -> {
            Intent intent = new Intent(Goal.this, Addgoal.class);
            startActivity(intent);
        });
    }
}
