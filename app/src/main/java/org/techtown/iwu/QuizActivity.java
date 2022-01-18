package org.techtown.iwu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class QuizActivity extends AppCompatActivity { // 퀴즈
    int b_id; //(camera) main에서 넘어온 값(건물 id)
    int rand; //정답번호(0-3)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent quizIntent = getIntent();
        b_id = quizIntent.getIntExtra("b_id", 0);
        //Toast.makeText(getApplicationContext(), "b_id" + b_id, Toast.LENGTH_SHORT).show();

        TextView b_text = (TextView) findViewById(R.id.b_text);
        TextView qu_text = (TextView) findViewById(R.id.qu_text); // Question text
        TextView an0_text = (TextView) findViewById(R.id.an0_text); // Answer text(정답)
        TextView an1_text = (TextView) findViewById(R.id.an1_text); // Answer text(오답)
        TextView an2_text = (TextView) findViewById(R.id.an2_text); // Answer text(오답)
        TextView an3_text = (TextView) findViewById(R.id.an3_text); // Answer text(오답)

        Button an0_button = findViewById(R.id.an0_button); // Answer button(정답)
        Button an1_button = findViewById(R.id.an1_button); // Answer button(오답)
        Button an2_button = findViewById(R.id.an2_button); // Answer button(오답)
        Button an3_button = findViewById(R.id.an3_button); // Answer button(오답)

        Random random = new Random();
        rand = random.nextInt(4); // 랜덤으로 변경 0-3
//      int b_id = 13;
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jasonObject = new JSONObject(response);
                    boolean success = jasonObject.getBoolean("success");

                    if (success) {
                        String b_id = jasonObject.getString("b_id");
                        String b_name = jasonObject.getString("b_name");
                        String q_qu = jasonObject.getString("q_qu");
                        String q_an0 = jasonObject.getString("q_an0");
                        String q_an1 = jasonObject.getString("q_an1");
                        String q_an2 = jasonObject.getString("q_an2");
                        String q_an3 = jasonObject.getString("q_an3");

                        b_text.setText(b_id + "호관 " + b_name + " Quiz");
                        qu_text.setText(q_qu);

                        //랜덤으로 정해진 random 값에 따라 text 에 an0(정답)넣고 나머지에 test 에 an1-3(오답) 넣기
                        if (rand == 0) {
                            an0_text.setText(q_an0); an1_text.setText(q_an1);
                            an2_text.setText(q_an2); an3_text.setText(q_an3);
                        } else if (rand == 1) {
                            an1_text.setText(q_an0); an0_text.setText(q_an1);
                            an2_text.setText(q_an2); an3_text.setText(q_an3);
                        } else if (rand == 2) {
                            an2_text.setText(q_an0); an0_text.setText(q_an1);
                            an1_text.setText(q_an2); an3_text.setText(q_an3);
                        } else if (rand == 3) {
                            an3_text.setText(q_an0); an0_text.setText(q_an1);
                            an1_text.setText(q_an2); an2_text.setText(q_an3);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "가져오기 실패", Toast.LENGTH_SHORT).show();
                        return;

                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "JSONException", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        };

        QuizRequest Request = new QuizRequest(b_id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(QuizActivity.this);
        queue.add(Request);
        //DB에서 정보 가져오기

        an0_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rand == 0) showMessage_quiz(0); // 버튼 0이 정답이라면 -> key 값을 0으로
                else showMessage_quiz(1); // 정답이 아니라면 -> key 1
            }
        });

        an1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rand == 1) showMessage_quiz(0);
                else showMessage_quiz(1);
            }
        });

        an2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rand == 2) showMessage_quiz(0);
                else showMessage_quiz(1);
            }
        });

        an3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rand == 3) showMessage_quiz(0);
                else showMessage_quiz(1);
            }
        });
    }

    private void showMessage_quiz(int key) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("결과");
        if (key == 0) builder.setMessage("정답입니다. 스탬프를 획득하셨습니다.");
        else if (key == 1) builder.setMessage("오답입니다. 다시 풀어주세요.");

        builder.setNeutralButton("나가기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (key == 0) { // 정답일때 나가기를 누르면 값을 넘겨주며 현재 액티비티 종료
                    //Intent intent = new Intent();
                    //intent.putExtra("정답", rand);
                    //setResult(RESULT_OK, intent);

                    //quiz 정답 -> 스탬프 DB에서 사용 예정
                    finish();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}