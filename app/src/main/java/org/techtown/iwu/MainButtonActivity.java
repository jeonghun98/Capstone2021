package org.techtown.iwu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainButtonActivity extends AppCompatActivity {
    ImageButton MainMapbtn, MainSetbtn, MainStampbtn; //이미지버튼 Map, Setting, Stamp 선언
    FrameLayout previewFrame; //카메라 뷰를 위한 frame
    CameraSurfaceView cameraView; //카메라
    ImageButton button1; //임시 방편 어워드 버튼
    int b_id = 0;

    //호관, 위도, 경도
    static final int b_location[] = {5,7,13};
    static final double b_latitude[] = {37.3756, 37.3744, 37.3759};
    static final double b_longtitude[] = {126.6347, 126.6336, 126.6333};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainbuttons); // activity_main 보이기

        previewFrame = findViewById(R.id.previewFrame);
        cameraView = findViewById(R.id.cameraView);

        //화면에 나타나는 임시 어워드
        button1 = findViewById(R.id.imageButton1);
        button1.setVisibility(View.INVISIBLE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage_award();
            }
        });

        MainMapbtn = (ImageButton) findViewById(R.id.mainmapbtn); // MainMapbtn 받아오기

        MainMapbtn.setOnClickListener(new View.OnClickListener() { // 메인화면에서 지도 버튼 클릭 시 수행
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class); // MapActivity 수행
                startActivity(intent); // MapActivity 시작
            }
        });

        MainSetbtn = (ImageButton) findViewById(R.id.mainsetbtn); // MainSettingbtn 받아오기

        MainSetbtn.setOnClickListener(new View.OnClickListener() { // 메인화면에서 setting 버튼 클릭 시 수행
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class); // SettingActivity 수행
                startActivity(intent);
            }
        });


        Intent intent = getIntent(); // 앞의 LogInActivity에서 넘긴 정보 받기
        int MajorCode = intent.getIntExtra("u_mid", 29); // Major code 받아오기
        MainStampbtn = (ImageButton) findViewById(R.id.mainstampbtn); // MainStampbtn 받아오기
        MainStampbtn.setOnClickListener(new View.OnClickListener() { // 메인화면에서 stamp 버튼 클릭 시 수행
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StampActivity.class); // StampActivity 수행
                intent.putExtra("u_mid", MajorCode); // Major code 넘겨주기
                startActivity(intent); // StampActivity 시작
            }
        });

        startLocationService(); //위치 활성화
    }

    private void showMessage_award() { //어워드 획득 후 나타나는 dialog 메소드
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(b_id + "호관의 아이템을 획득하셨습니다.");

        builder.setNeutralButton("나가기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("2차 퀴즈", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { //quiz 액티비티 호출
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                switch (b_id){ // b_id 추가 수정 예정
                    case 1 : intent.putExtra("b_id",1); break; // quiz activity -> 사용자가 발견한 b_id 넘겨줌
                    case 2 : intent.putExtra("b_id",2); break;
                    case 5 : intent.putExtra("b_id",5); break;
                    case 6 : intent.putExtra("b_id",6); break;
                    case 7 : intent.putExtra("b_id",7); break;
                    case 8 : intent.putExtra("b_id",8); break;
                    case 11 : intent.putExtra("b_id",11); break;
                    case 12 : intent.putExtra("b_id",12); break;
                    case 13 : intent.putExtra("b_id",13); break;
                    case 14 : intent.putExtra("b_id",14); break;
                    case 15 : intent.putExtra("b_id",15); break;
                    case 16 : intent.putExtra("b_id",16); break;
                    case 17 : intent.putExtra("b_id",17); break;
                    case 18 : intent.putExtra("b_id",18); break;
                    case 24 : intent.putExtra("b_id",24); break;
                    case 28 : intent.putExtra("b_id",28); break;
                    case 29 : intent.putExtra("b_id",29); break;
                    case 30 : intent.putExtra("b_id",30); break;
                    case 31 : intent.putExtra("b_id",31); break; //미유
                    case 32 : intent.putExtra("b_id",32); break; //솔찬
                }
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //해당 코드는 4G(3G)를 사용하지 않는 핸드폰에서 작용하지 않아서 -> 현재 virtual device 만 가능

    public void startLocationService() {
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                //String message = "start -> Latitude : " + latitude + "\nLongitude : " + longitude;
                //showToast(message);
                button1 = findViewById(R.id.imageButton1);

                //사용자의 위치가 해당 건물의 위도,경도를 중심으로 원안에 위치해 있을 때 visible
                for(int i = 0; i < b_latitude.length; i++) {
                    if (Math.pow(0.0005, 2) >= (Math.pow(b_latitude[i] - latitude, 2) + Math.pow(b_longtitude[i] - longitude, 2))) {
                        button1.setVisibility(View.VISIBLE);
                        b_id = b_location[i];
                        break;
                    }
                    else {
                        button1.setVisibility(View.INVISIBLE);
                    }
                }
            }
            else showToast("location == null");

            GPSListener gpsListener = new GPSListener();
            long minTime = 5000;
            float minDistance = 0;
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    class GPSListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            //String message = "location -> Latitude : " + latitude + "\nLongitude : " + longitude;
            //showToast(message);

            //사용자의 위치가 해당 건물의 위도,경도를 중심으로 원안에 위치해 있을 때 visible
            button1 = findViewById(R.id.imageButton1);
            for(int i = 0; i < b_latitude.length; i++) {
                if (Math.pow(0.0005, 2) >= (Math.pow(b_latitude[i] - latitude, 2) + Math.pow(b_longtitude[i] - longitude, 2))) {
                    button1.setVisibility(View.VISIBLE);
                    b_id = b_location[i];
                    //showToast(b_id + "호관 어워드");
                    break;
                }
                else {
                    button1.setVisibility(View.INVISIBLE);
                }
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onProviderDisabled(String provider) {}
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
