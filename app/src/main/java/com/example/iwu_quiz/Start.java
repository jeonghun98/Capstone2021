package com.example.iwu_quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //권한 처리
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        AndPermission.with(this) // 카메라, 위치 권한 받고 확인
                .runtime()
                .permission(Permission.CAMERA,Permission.ACCESS_FINE_LOCATION)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showToast("허용된 권한 갯수 : " + permissions.size());

                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showToast("거부된 권한 갯수 : " + permissions.size());
                    }
                })
                .start();
    }

    public void onStartClick(View v){ //버튼 클릭 후 카메라 액티비티로 전환
        Intent intent = new Intent(Start.this, MainActivity.class);
        startActivity(intent);
    }
    public void showToast(String data) { //toast 메소드
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}