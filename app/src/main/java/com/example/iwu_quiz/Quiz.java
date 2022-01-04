package com.example.iwu_quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class Quiz extends AppCompatActivity { // 퀴즈
    int rand = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Button button1 = findViewById(R.id.B1);
        Button button2 = findViewById(R.id.B2);
        Button button3 = findViewById(R.id.B3);
        Button button4 = findViewById(R.id.B4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rand == 1) showMessage_quiz(1); // 정답 -> 1
                else showMessage_quiz(0); // 오답 -> 0
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rand == 2) showMessage_quiz(1); // 정답 -> 1
                else showMessage_quiz(0); // 오답 -> 0
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rand == 3) showMessage_quiz(1); // 정답 -> 1
                else showMessage_quiz(0); // 오답 -> 0
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rand == 4) showMessage_quiz(1); // 정답 -> 1
                else showMessage_quiz(0); // 오답 -> 0
            }
        });
    }

//    public void Quiz1_ButtonClicked(View v) { //정답 -> 1
//        showMessage_quiz(1);
//    }
//    public void Quiz0_ButtonClicked(View v) { //오답 -> 0
//        showMessage_quiz(0);
//    }

    private void showMessage_quiz(int key) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("결과");
        if(key == 1) builder.setMessage("정답입니다. 스탬프를 획득하셨습니다.");
        else if(key == 0) builder.setMessage("오답입니다. 다시 풀어주세요.");

        builder.setNeutralButton("나가기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(key == 1) { // 정답일때 나가기를 누르면 값을 넘겨주며 현재 액티비티 종료
                    Intent intent = new Intent();
                    intent.putExtra("정답","1");
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}