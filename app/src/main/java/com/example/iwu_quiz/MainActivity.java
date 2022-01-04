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


public class MainActivity extends AppCompatActivity { // 카메라 액티비티
    FrameLayout previewFrame;
    CameraSurfaceView cameraView;
    public static final int REQUEST_CODE_MENU = 101; // 퀴즈 액티비티에서 넘어온 값 확인을 위함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previewFrame = findViewById(R.id.previewFrame);
        cameraView = findViewById(R.id.cameraView);
        //권한 Main -> Start
//        AndPermission.with(this) // 카메라, 위치 권한 확인
//                .runtime()
//                .permission(Permission.CAMERA,Permission.ACCESS_FINE_LOCATION)
//                .onGranted(new Action<List<String>>() {
//                    @Override
//                    public void onAction(List<String> permissions) {
//                        //showToast("허용된 권한 갯수 : " + permissions.size());
//
//                    }
//                })
//                .onDenied(new Action<List<String>>() {
//                    @Override
//                    public void onAction(List<String> permissions) {
//                        //showToast("거부된 권한 갯수 : " + permissions.size());
//                    }
//                })
//                .start();
    }

    public void AwardButtonClicked(View v) {
        showMessage_award(1);
    }

//    public void showToast(String data) { //toast 메소드
//
//        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
//    }

    private void showMessage_award(int key) { //어워드 획득 후 나타나는 dialog 메소드
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("건물의 아이템을 획득하셨습니다.");

        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("2차 퀴즈", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { //quiz 액티비티 호출
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //quiz 로부터 받은 응답 처리 메서드
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_MENU) { // 퀴즈 정답값이 넘어오지 않을 때
            Toast.makeText(getApplicationContext(), "onActivityResult 메서드 호출됨, 요청코드 : " +
                    requestCode + ", 결과 코드 : " + resultCode, Toast.LENGTH_LONG).show();
        }
        if(resultCode == RESULT_OK) { // 퀴즈 정답을 맞추면
            String result = data.getStringExtra("정답");
            Toast.makeText(getApplicationContext(), "응답으로 전달된 정답 : " + result, Toast.LENGTH_LONG).show();
        }
    }
}