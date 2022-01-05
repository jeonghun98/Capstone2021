package org.techtown.iwu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

//로딩화면 Activity

public class LoadingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        startLoading();
    }

    private void startLoading() { // 로딩화면 구현 함수
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {  // 로딩 후 구현될 Activity 설정하는 함수
                Intent intent = new Intent(getBaseContext(), LogInActivity.class); // 로딩화면 후 로그인 화면 실행
                startActivity(intent); // LogInActivity 시작
                finish(); // LoadingActivity 종료
            }
        }, 2000); //약 2초간 실행
    }
}
