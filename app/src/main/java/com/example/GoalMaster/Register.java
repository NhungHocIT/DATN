package com.example.GoalMaster;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.example.GoalMaster.User;
import com.example.GoalMaster.database.UserDatabaseHelper;

public class Register extends AppCompatActivity {

    EditText edtUsername, edtEmailPhone, edtPassword, edtBirth; // ✅ thêm edtBirth
    Button btnRegister;
    ApiService apiService;
    TextView tvSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // XML layout đăng ký
        // Ánh xạ view
        edtUsername = findViewById(R.id.edtName);
        edtEmailPhone = findViewById(R.id.edtEmailPhone);
        edtPassword = findViewById(R.id.edtpass);
        btnRegister = findViewById(R.id.btnregister);
        tvSignUp = findViewById(R.id.tvSignUp);
        edtBirth = findViewById(R.id.edtBirth);

        edtBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                                String birthDate = String.format("%02d-%02d-%04d", selectedDay, selectedMonth + 1, selectedYear);
                                edtBirth.setText(birthDate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        apiService = ApiClient.getRetrofit().create(ApiService.class);
        btnRegister.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String emailPhone = edtEmailPhone.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            // Kiểm tra đầu vào (Input validation)
            if (username.isEmpty() || emailPhone.isEmpty() || password.isEmpty()) {
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
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        RegisterResponse registerResponse = response.body();
                        if (registerResponse.isStatus()) {
                            Toast.makeText(Register.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();

                            // Chuyển về màn hình đăng nhập
                            Intent intent = new Intent(Register.this, LoginActivity.class);
                            startActivity(intent);
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