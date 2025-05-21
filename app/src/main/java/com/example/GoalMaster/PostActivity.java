package com.example.GoalMaster;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.GoalMaster.response.CommentResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // Giả lập thông tin người dùng (Mock user data)
        currentUser = new User(1, "Nhung", "nhung@example.com");

        recyclerView = findViewById(R.id.recyclerViewPosts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchPosts();
    }

    // Lấy danh sách bài viết từ API
    // Fetch posts from API
    private void fetchPosts() {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
        apiService.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    postAdapter = new PostAdapter(PostActivity.this, response.body(), currentUser, PostActivity.this::showCommentDialog);
                    recyclerView.setAdapter(postAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("PostActivity", "API call error: " + t.getMessage());
                Toast.makeText(PostActivity.this, "Không tải được bài viết", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Hiển thị dialog bình luận cho bài viết
    // Show comment dialog for a post
    private void showCommentDialog(Post post) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_comment);

        RecyclerView rvComments = dialog.findViewById(R.id.recyclerViewComment);
        EditText edtComment = dialog.findViewById(R.id.edtComment);
        Button btnSendComment = dialog.findViewById(R.id.btnSendComment);

        List<PostComment> commentList = new ArrayList<>();
        CommentAdapter commentAdapter = new CommentAdapter(this, commentList);
        rvComments.setLayoutManager(new LinearLayoutManager(this));
        rvComments.setAdapter(commentAdapter);

        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        // Lấy bình luận hiện tại
        // Load current comments
        apiService.getComments(post.getId()).enqueue(new Callback<List<PostComment>>() {
            @Override
            public void onResponse(Call<List<PostComment>> call, Response<List<PostComment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    commentList.clear();
                    commentList.addAll(response.body());
                    commentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<PostComment>> call, Throwable t) {
                Toast.makeText(PostActivity.this, "Không tải được bình luận", Toast.LENGTH_SHORT).show();
            }
        });

        btnSendComment.setOnClickListener(view -> {
            String content = edtComment.getText().toString().trim();
            if (!content.isEmpty()) {
                PostComment newComment = new PostComment(0, post.getId(), currentUser.getId(), content, null);
                apiService.addComment(newComment).enqueue(new Callback<CommentResponse>() {
                    @Override
                    public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(PostActivity.this, "Bình luận đã được gửi", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PostActivity.this, "Gửi bình luận thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentResponse> call, Throwable t) {
                        Toast.makeText(PostActivity.this, "Lỗi mạng: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                Toast.makeText(PostActivity.this, "Vui lòng nhập nội dung bình luận", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}
