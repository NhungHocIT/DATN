package com.example.GoalMaster;

import com.example.GoalMaster.request.GoalCreateUpdateRequestDTO;
import com.example.GoalMaster.request.GoalDeleteResponseDTO;
import com.example.GoalMaster.request.LoginRequest;
import com.example.GoalMaster.request.RegisterRequest;
import com.example.GoalMaster.response.CommentResponse;
import com.example.GoalMaster.response.GoalResponseDTO;
import com.example.GoalMaster.response.LoginResponse;
import com.example.GoalMaster.response.PostResponse;
import com.example.GoalMaster.response.RegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // API đăng nhập
    @POST("api/v1/auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    // API đăng ký
    @POST("api/v1/auth/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    // Lấy danh sách bài viết
    @GET("api/v1/posts")
    Call<List<Post>> getPosts();

    // Thêm bài viết mới
    @POST("api/v1/posts")
    Call<PostResponse> addPost(@Body Post post);

    // Sửa bài viết
    @PUT("api/v1/posts/{postId}")
    Call<PostResponse> editPost(@Path("postId") long postId, @Body Post post);
    //Với API cần truyền tham số userId trong URL, ta dùng @Query("userId") để Retrofit tự nối vào URL.
    // Xóa bài viết
    @DELETE("api/v1/posts/{postId}")
    Call<ApiResponse> deletePost(@Path("postId") long postId, @Query("userId") long userId);

    // Like bài viết
    @POST("api/v1/posts/{postId}/like")
    Call<PostResponse> likePost(@Path("postId") long postId, @Query("userId") long userId);

    // Share bài viết
    @POST("api/v1/posts/{postId}/share")
    Call<PostResponse> sharePost(@Path("postId") long postId, @Query("userId") long userId);

    // Thêm bình luận
    @POST("api/v1/posts/comment")
    Call<CommentResponse> addComment(@Body PostComment comment);

    // Lấy bình luận của bài viết
    @GET("api/v1/posts/{postId}/comments")
    Call<List<PostComment>> getComments(@Path("postId") long postId);


    //API addGoal
    @POST("/api/v1/goals")
    Call<ApiResponse<GoalResponseDTO>> createGoal(@Body GoalCreateUpdateRequestDTO goalRequest);
    //API editGoal
    @PUT("/api/v1/goals/edit")
    Call<ApiResponse<GoalResponseDTO>> editGoal(@Body GoalCreateUpdateRequestDTO goalRequest);
    //API deleteGoal
    @DELETE("/api/v1/goals/delete")
    Call<GoalDeleteResponseDTO> deleteGoal(@Body GoalDeleteResponseDTO deleteRequest);
}
