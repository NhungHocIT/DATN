package com.example.GoalMaster;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.GoalMaster.request.RegisterRequest;
import com.example.GoalMaster.response.RegisterResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Register extends AppCompatActivity {

    // 🔸 Khai báo biến giao diện (UI fields)
    private EditText edtUsername, edtEmailPhone, edtPassword, edtRepass, edtBirth;
    private Button btnRegister;
    private TextView tvSignUp;

    // 🔸 Khai báo biến Retrofit toàn cục (Global retrofit field)
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Ánh xạ view từ layout
        edtUsername = findViewById(R.id.edtName);
        edtEmailPhone = findViewById(R.id.edtEmailPhone);
        edtPassword = findViewById(R.id.edtpass);
        edtRepass = findViewById(R.id.edtRepass);
        btnRegister = findViewById(R.id.btnregister);
        tvSignUp = findViewById(R.id.tvSignUp);
        edtBirth = findViewById(R.id.edtBirth);

        // 🔹 Khởi tạo Retrofit client


        // 👉 Chọn ngày sinh
        edtBirth.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Định dạng yyyy-MM-dd
                        String birthDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                        edtBirth.setText(birthDate);
                    }, year, month, day);
            datePickerDialog.show();
        });


        // 👉 Chuyển về trang đăng nhập
        tvSignUp.setOnClickListener(view -> {
            startActivity(new Intent(Register.this, LoginActivity.class));
            finish();
        });

        // 👉 Xử lý nút Đăng ký
        btnRegister.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String emailPhone = edtEmailPhone.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String confirmPassword = edtRepass.getText().toString().trim();
            String birth = edtBirth.getText().toString().trim();

            // Kiểm tra đầu vào
            if (username.isEmpty() || emailPhone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || birth.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (emailPhone.contains("@") && !android.util.Patterns.EMAIL_ADDRESS.matcher(emailPhone).matches()) {
                Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }


            RegisterRequest request = new RegisterRequest(emailPhone, password, username);
            // Gọi API đăng ký
            Call<RegisterResponse> call = apiService.registerUser(request);
            // Gửi yêu cầu đăng ký
            RegisterRequest request = new RegisterRequest(emailPhone, password,confirmPassword, username, birth);
            ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
            Call<RegisterResponse> call = apiService.registerUser(request);

            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        RegisterResponse registerResponse = response.body();

                        if (registerResponse.isStatus()) {
                            Toast.makeText(Register.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();

                            // Chuyển về màn hình đăng nhập
                            Intent intent = new Intent(Register.this, LoginActivity.class);


                        if (registerResponse.isStatus()) {
                            // ✅ Lưu thông tin vào SharedPreferences
                            SharedPreferences sharedPref = getSharedPreferences("user_data", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("username", username);
                            editor.putString("emailPhone", emailPhone);
                            editor.putString("birth", birth);
                            editor.apply();

                            Toast.makeText(Register.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, LoginActivity.class));

                            finish();
                        } else {
                            Toast.makeText(Register.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register.this, "Lỗi khi đăng ký: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Toast.makeText(Register.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
    }

