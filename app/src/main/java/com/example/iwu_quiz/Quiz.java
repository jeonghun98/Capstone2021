package com.example.iwu_quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class Quiz extends AppCompatActivity { // 퀴즈
    int rand = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView t1= (TextView)findViewById(R.id.t1);
        TextView t2= (TextView)findViewById(R.id.t2);
        TextView test1= (TextView)findViewById(R.id.test1);
        TextView test2= (TextView)findViewById(R.id.test2);
        TextView test3= (TextView)findViewById(R.id.test3);
        TextView test4= (TextView)findViewById(R.id.test4);

        Button button1 = findViewById(R.id.B1);
        Button button2 = findViewById(R.id.B2);
        Button button3 = findViewById(R.id.B3);
        Button button4 = findViewById(R.id.B4);

        Random random = new Random();
        rand = random.nextInt(4) + 1;; //정답번호. 랜덤으로 변경 1-4
        String b_id = "5호관"; //camera main에서 넘어온 값(건물)

        Response.Listener<String> responseListener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jasonObject=new JSONObject(response);
                    boolean success=jasonObject.getBoolean("success");

                    if (success) {
                        String b_id = jasonObject.getString("b_id");
                        String q_que = jasonObject.getString("q_que");
                        String q_co = jasonObject.getString("q_co");
                        String q_wr1 = jasonObject.getString("q_wr1");
                        String q_wr2 = jasonObject.getString("q_wr2");
                        String q_wr3 = jasonObject.getString("q_wr3");

                        t1.setText(b_id + "Quiz");
                        t2.setText(q_que);

                        //랜덤으로 정해진 rand값에 따라 test에 co(정답)넣고 나머지에 test에 wr(오답) 넣기
                        if(rand == 1) {
                            test1.setText(q_co); test2.setText(q_wr1); test3.setText(q_wr2); test4.setText(q_wr3);
                        }
                        else if (rand == 2) {
                            test2.setText(q_co); test1.setText(q_wr1); test3.setText(q_wr2); test4.setText(q_wr3);
                        }
                        else if (rand == 3) {
                            test3.setText(q_co); test1.setText(q_wr1); test2.setText(q_wr2); test4.setText(q_wr3);
                        }
                        else if (rand == 4) {
                            test4.setText(q_co); test1.setText(q_wr1); test2.setText(q_wr2); test3.setText(q_wr3);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "가져오기 실패", Toast.LENGTH_SHORT).show();
                        return;

                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "JSONException", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        };
        QuizRequest Request=new QuizRequest(b_id,responseListener);
        RequestQueue queue= Volley.newRequestQueue(Quiz.this);
        queue.add(Request);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rand == 1) showMessage_quiz(1); // 정답 -> key = 1
                else showMessage_quiz(0); // 오답 -> key = 0
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