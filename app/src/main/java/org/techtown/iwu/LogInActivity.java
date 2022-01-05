package org.techtown.iwu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

//로그인 Activity

public class LogInActivity extends AppCompatActivity {
    private EditText u_id, u_pw; // 넘겨받는 ID, PW
    private Button btn_login, btn_signup; // 로그인, 유저등록 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        u_id = findViewById(R.id.u_id);
        u_pw = findViewById(R.id.u_pw);
        btn_login = findViewById(R.id.LogInButton);
        btn_signup = findViewById(R.id.SignUpButton);


        // 회원가입 버튼을 클릭 시 수행
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class); // 유저등록하는 Register Activity로 넘어감
                startActivity(intent);
            }
        });

        // 로그인 버튼 클릭 시
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = u_id.getText().toString();
                String userPass = u_pw.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 로그인에 성공한 경우 유저의 정보를 모두 받아옴.
                                String userID = jsonObject.getString("u_id");
                                String userPass = jsonObject.getString("u_pw");
                                String userName = jsonObject.getString( "u_name" );
                                String userMajor = jsonObject.getString( "u_major" );
                                String userPhone = jsonObject.getString( "u_phone" );

                                Toast.makeText(getApplicationContext(),userMajor + " "+ userName+"학생, 환영합니다!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LogInActivity.this, MenuActivity.class); // 로그인 후 시작 전 화면(메인 전)으로 이동
                                intent.putExtra("u_id", userID);
                                intent.putExtra("u_pw", userPass);
                                intent.putExtra("u_name", userName);
                                intent.putExtra("u_major", userMajor);
                                intent.putExtra("u_phone", userPhone);

                                startActivity(intent);
                            }
                            else { // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LogInRequest loginRequest = new LogInRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LogInActivity.this);
                queue.add(loginRequest); // 큐에 넘김
            }
        });


    }
}