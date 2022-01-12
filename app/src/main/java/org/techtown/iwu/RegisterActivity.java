package org.techtown.iwu;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

// 회원가입 Activity

public class RegisterActivity extends AppCompatActivity {

    private EditText u_id, u_pw, u_name, u_phone;
    private Button btn_register, btn_major;
    private TextView u_mid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // 아이디 값 찾아주기
        u_id = findViewById(R.id.u_id);
        u_pw = findViewById(R.id.u_pw);
        u_name = findViewById(R.id.u_name);
        Spinner spinner = (Spinner)findViewById(R.id.majorbox); // 학과는 Spinner를 통해 받음
        u_phone = findViewById(R.id.u_phone);

        btn_major = findViewById(R.id.majorbtn);
        btn_major.setOnClickListener(new View.OnClickListener() { // major 선택완료 버튼 누를 시 실행되는 함수
            @Override
            public void onClick(View v) {
                String userMid = spinner.getSelectedItem().toString(); // spinner에서 선택된 string 가져옴
                userMid = userMid.substring(userMid.length()-2); // 맨 뒤의 2자리 숫자 받아오기
                u_mid = (TextView) findViewById(R.id.codenum); // TextView인 codenum의 주소를 받아와서
                u_mid.setText(userMid); // userMid 값 넣기
            }
        });

        // 회원가입 버튼 클릭 시 수행
        btn_register = findViewById(R.id.RegisterButton);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해옴.
                String userID = u_id.getText().toString();
                String userPass = u_pw.getText().toString();
                String userName = u_name.getText().toString();
                String userMajor = spinner.getSelectedItem().toString(); // Spinner 값을 string으로 받아 userMajor로 넘김
                userMajor = userMajor.substring(0, userMajor.length()-3);
                int userPhone = Integer.parseInt(u_phone.getText().toString());
                int userMid = Integer.parseInt(u_mid.getText().toString());



                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { // DB와 연동하여 로그인된 회원의 정보를 받아오는 함수
                        try {
                            JSONObject jsonObject = new JSONObject(response); // 회원 정보들을 받음
                            boolean success = jsonObject.getBoolean("success"); // 인자로 전달된 key에 대한 객체를 "success" 할 시 True 리턴
                            if (success) { // 회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LogInActivity.class);  // 유저 등록 후 로그인화면으로 이동
                                startActivity(intent);
                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) { // 예외처리
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley를 이용해 정보 넘김.
                RegisterRequest registerRequest = new RegisterRequest(userID,userPass,userName, userMajor,userPhone, userMid, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });

    }
}