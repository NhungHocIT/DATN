package com.example.GoalMaster;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.GoalMaster.request.GoalCreateUpdateRequestDTO;
import com.example.GoalMaster.ApiResponse;
import com.example.GoalMaster.response.GoalResponseDTO;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Addgoal extends AppCompatActivity {
    private EditText edtNameGoal, edtStartDate, edtDeadline, edtNewTask;
    private ListView listViewTasks;
    private Button btnAddTask, btnSaveGoal;
    private ImageButton btnBackAdd;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addgoal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addgoal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ View
        edtNameGoal = findViewById(R.id.edtNameGoal);
        edtStartDate = findViewById(R.id.edtStartDate);
        edtDeadline = findViewById(R.id.edtDeadline);
        edtNewTask = findViewById(R.id.edtNewTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        btnSaveGoal = findViewById(R.id.btnSaveGoal);
        btnBackAdd = findViewById(R.id.btnBackAdd);
        listViewTasks = findViewById(R.id.listViewTasks);

        // Khởi tạo danh sách nhiệm vụ
        taskList = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, taskList);
        listViewTasks.setAdapter(taskAdapter);
        listViewTasks.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Xử lý chọn ngày
        edtStartDate.setOnClickListener(view -> showDatePicker(edtStartDate));
        edtDeadline.setOnClickListener(view -> showDatePicker(edtDeadline));

        // Thêm nhiệm vụ vào danh sách
        btnAddTask.setOnClickListener(view -> addTask());

        // Xử lý lưu mục tiêu
        btnSaveGoal.setOnClickListener(view -> saveGoal());

        // Xử lý nút quay lại
        btnBackAdd.setOnClickListener(view -> finish());
    }

    // Hiển thị DatePickerDialog
    private void showDatePicker(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    editText.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    // Thêm nhiệm vụ vào danh sách
    private void addTask() {
        String task = edtNewTask.getText().toString().trim();
        if (!task.isEmpty()) {
            taskList.add(task);
            taskAdapter.notifyDataSetChanged();
            edtNewTask.setText("");
        } else {
            Toast.makeText(this, "Vui lòng nhập nhiệm vụ!", Toast.LENGTH_SHORT).show();
        }
    }

    // Xử lý lưu mục tiêu
    private void saveGoal() {
        String goalName = edtNameGoal.getText().toString().trim();
        String startDate = edtStartDate.getText().toString().trim();
        String deadline = edtDeadline.getText().toString().trim();

        if (goalName.isEmpty() || startDate.isEmpty() || deadline.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        GoalCreateUpdateRequestDTO requestDTO = new GoalCreateUpdateRequestDTO();
        requestDTO.setTitle(goalName);
        requestDTO.setStart_date(startDate);
        requestDTO.setEnd_date(deadline);
        requestDTO.setDescription("Mục tiêu chi tiết"); // hoặc một mô tả ngắn nếu bạn muốn
        requestDTO.setUserId(1L); // lấy userId thật từ session hoặc SharedPreferences
        requestDTO.setGoalProgressList(taskList); // danh sách các nhiệm vụ
        requestDTO.setIdGoals(null); // hoặc 0L nếu không hỗ trợ null, vì đây là tạo mới

        ApiClient.getApiService().createGoal(requestDTO).enqueue(new Callback<ApiResponse<GoalResponseDTO>>() {
            @Override
            public void onResponse(Call<ApiResponse<GoalResponseDTO>> call, Response<ApiResponse<GoalResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                    Toast.makeText(Addgoal.this, "Mục tiêu đã được lưu!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Addgoal.this, "Lưu thất bại: " + (response.body() != null ? response.body().getMessage() : "Lỗi server"), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<GoalResponseDTO>> call, Throwable t) {
                Toast.makeText(Addgoal.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
