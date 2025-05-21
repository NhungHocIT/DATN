package com.example.GoalMaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.GoalMaster.response.PostResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private User currentUser;

    private EditText edtStatus;
    private ImageButton btnPost;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        currentUser = new User(1, "thynguyen@example.com", "12345");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnPost = findViewById(R.id.btnPost);
        edtStatus = findViewById(R.id.edtStatus);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Đặt mặc định là trang chủ được chọn
        bottomNavigation.setSelectedItemId(R.id.navigation_home);

        btnPost.setOnClickListener(v -> {
            String status = edtStatus.getText().toString().trim();

            if (status.isEmpty()) {
                Toast.makeText(HomeActivity.this, "Vui lòng nhập nội dung bài viết", Toast.LENGTH_SHORT).show();
            } else {
                // Lấy username (có thể lấy từ user hoặc profile)
                String username = "Thy Nguyen";  // Bạn có thể thay bằng lấy từ user hoặc profile thật
                // Lấy thời gian hiện tại theo định dạng dd/MM/yyyy HH:mm
                String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

                // Tạo Post với đủ 4 tham số
                Post newPost = new Post(currentUser.getId(), username, currentTime, status);

                ApiService apiService = ApiClient.getApiService();
                Call<PostResponse> call = apiService.addPost(newPost);
                call.enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(HomeActivity.this, "Đăng bài thành công", Toast.LENGTH_SHORT).show();
                            edtStatus.setText("");
                            fetchPostsFromApi();
                        } else {
                            Toast.makeText(HomeActivity.this, "Lỗi khi đăng bài", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("HomeActivity", "API tạo bài viết thất bại: " + t.getMessage());
                    }
                });

            }
        });

        // Xử lý chuyển trang khi chọn item trong BottomNavigation
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.navigation_dashboard) {
                    Intent intent = new Intent(HomeActivity.this, Goal.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                } else if (id == R.id.navigation_profile) {
                    Intent intent = new Intent(HomeActivity.this, Profile.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                    return true;

                } else if (id == R.id.navigation_home) {
                    return true;
                }

                return false;
            }
        });

        fetchPostsFromApi();
    }

    private void fetchPostsFromApi() {
        ApiService apiService = ApiClient.getApiService();

        Call<List<Post>> call = apiService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Post> postList = response.body();
                    postAdapter = new PostAdapter(HomeActivity.this, postList, currentUser, post -> {
                        Toast.makeText(HomeActivity.this, "Bấm bình luận bài viết: " + post.getContent(), Toast.LENGTH_SHORT).show();
                    });
                    recyclerView.setAdapter(postAdapter);
                } else {
                    Toast.makeText(HomeActivity.this, "Không lấy được dữ liệu bài viết", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("HomeActivity", "API call failed: " + t.getMessage());
            }
        });
    }
}
