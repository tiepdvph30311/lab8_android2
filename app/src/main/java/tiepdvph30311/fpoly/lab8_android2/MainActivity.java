package tiepdvph30311.fpoly.lab8_android2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import tiepdvph30311.fpoly.lab8_android2.Adapter.MyPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo ViewPager và TabLayout
        viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tabs);

        // Thiết lập Adapter cho ViewPager
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        // Hiển thị ProgressDialog
        showProgressDialog();
    }

    private void showProgressDialog() {
        // Tạo ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false); // Không cho phép người dùng hủy bỏ
        progressDialog.show();

        // Dùng Handler để tắt ProgressDialog sau 3 giây
        new Handler().postDelayed(() -> {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }, 3000); // 3000 ms = 3 giây
    }

    public ViewPager getViewPager() {
        return viewPager;
    }
}
