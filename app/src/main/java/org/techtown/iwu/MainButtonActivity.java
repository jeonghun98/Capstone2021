package org.techtown.iwu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;


public class MainButtonActivity extends AppCompatActivity {
    ImageButton MainMapbtn, MainSetbtn, MainStampbtn; //이미지버튼 Map, Setting, Stamp 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainsetting); // activity_main 보이기


        MainMapbtn = (ImageButton) findViewById(R.id.mainmapbtn); // MainMapbtn 받아오기

        MainMapbtn.setOnClickListener(new View.OnClickListener() { // 지도 이미지 버튼 클릭 시 수행
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class); // MapActivity 수행
                startActivity(intent); // MapActivity 시작
            }
        });

        MainSetbtn = (ImageButton) findViewById(R.id.mainsetbtn); // MainSettingbtn 받아오기

        MainSetbtn.setOnClickListener(new View.OnClickListener() { // setting 이미지 버튼 클릭 시 수행
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class); // SettingActivity 수행
                startActivity(intent);
            }
        });

        MainStampbtn = (ImageButton) findViewById(R.id.mainstampbtn); // MainStampbtn 받아오기

        MainStampbtn.setOnClickListener(new View.OnClickListener() { // stamp 이미지 버튼 클릭 시 수행
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StampActivity.class); // StampActivity 수행
                startActivity(intent);
            }
        });

    }
}
