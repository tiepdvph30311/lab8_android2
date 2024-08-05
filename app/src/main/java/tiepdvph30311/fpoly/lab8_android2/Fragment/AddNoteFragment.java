package tiepdvph30311.fpoly.lab8_android2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;


import java.util.Calendar;

import tiepdvph30311.fpoly.lab8_android2.MainActivity;
import tiepdvph30311.fpoly.lab8_android2.Model.NoteViewModel;
import tiepdvph30311.fpoly.lab8_android2.R;

public class AddNoteFragment extends Fragment {

    private EditText contentEditText;
    private EditText timeEditText;
    private Button addButton;
    private NoteViewModel noteViewModel;
    private String selectedTime = ""; // Chuỗi lưu trữ thời gian đã chọn

    public AddNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);

        contentEditText = view.findViewById(R.id.content);
        timeEditText = view.findViewById(R.id.time);
        addButton = view.findViewById(R.id.add_button);

        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);

        timeEditText.setOnClickListener(v -> showDateTimePicker());

        addButton.setOnClickListener(v -> {
            String content = contentEditText.getText().toString().trim();
            String time = selectedTime; // Sử dụng thời gian đã chọn

            if (content.isEmpty() || time.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                noteViewModel.addNote(content, time);
                Toast.makeText(requireContext(), "Note added", Toast.LENGTH_SHORT).show();
                contentEditText.setText("");
                timeEditText.setText("");

                // Chuyển đến tab danh sách ghi chú
                ((MainActivity) getActivity()).getViewPager().setCurrentItem(1); // Cập nhật vị trí tab nếu cần
            }
        });

        return view;
    }

    private void showDateTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, year1, month1, dayOfMonth) -> {
                    // Sau khi chọn ngày, hiển thị TimePickerDialog
                    showTimePicker(year1, month1, dayOfMonth);
                },
                year, month, day);

        datePickerDialog.show();
    }

    private void showTimePicker(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),
                (view, hourOfDay, minute1) -> {
                    // Xử lý thời gian đã chọn và cập nhật EditText
                    selectedTime = String.format("%04d-%02d-%02d %02d:%02d",
                            year, month + 1, dayOfMonth, hourOfDay, minute1);
                    timeEditText.setText(selectedTime); // Hiển thị thời gian đã chọn
                },
                hour, minute, true);

        timePickerDialog.show();
    }
}
