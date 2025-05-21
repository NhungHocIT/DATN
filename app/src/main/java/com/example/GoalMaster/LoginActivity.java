package com.example.GoalMaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.GoalMaster.request.LoginRequest;
import com.example.GoalMaster.response.LoginResponse;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    // Khai báo các thành phần nhập liệu
    private EditText etEmailPhone, etPassword;
    private Button btnLogin;
    private MaterialButton btnGoogle;
    private TextView tvForgotPassword, tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            if (v != null) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            }
            return insets;
        });

        // Ánh xạ các thành phần UI
        etEmailPhone = findViewById(R.id.edtEmailPhone);
        etPassword = findViewById(R.id.edtpass);
        btnLogin = findViewById(R.id.btnlogin);
        btnGoogle = findViewById(R.id.btnGoogle);
        tvForgotPassword = findViewById(R.id.forgetpass);
        tvSignUp = findViewById(R.id.tvSignUp);

        // Xử lý sự kiện nút Đăng nhập
        btnLogin.setOnClickListener(v -> {
            String emailPhone = etEmailPhone.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Kiểm tra dữ liệu đầu vào
            if (emailPhone.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

// Kiểm tra định dạng email nếu có dấu @
            if (emailPhone.contains("@") && !android.util.Patterns.EMAIL_ADDRESS.matcher(emailPhone).matches()) {
                Toast.makeText(LoginActivity.this, "Email không hợp lệ. Vui lòng nhập đúng định dạng!", Toast.LENGTH_SHORT).show();
                return;
            }


            // Tạo LoginRequest
            LoginRequest loginRequest = new LoginRequest(emailPhone, password);

            // Gọi API
            ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
            Call<LoginResponse> call = apiService.loginUser(loginRequest);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                        // Chuyển sang màn hình chính
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Lỗi kết nối server!", Toast.LENGTH_SHORT).show();
                    Log.e("LoginError", t.getMessage(), t);
                }
            });
        });

        // Xử lý sự kiện Quên mật khẩu
        tvForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, Forgotpass.class);
            startActivity(intent);
        });

        // Xử lý sự kiện Đăng nhập với Google
        btnGoogle.setOnClickListener(v -> {
            Toast.makeText(LoginActivity.this, "Đăng nhập với Google", Toast.LENGTH_SHORT).show();
        });

        // Xử lý sự kiện Chưa có tài khoản? Đăng ký ngay
        tvSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, Register.class);
            startActivity(intent);
        });
    }
}
