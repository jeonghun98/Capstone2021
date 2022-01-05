package org.techtown.iwu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.util.FusedLocationSource;


// 지도 + 현재위치 불러오는 네이버맵 Activity

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "MapActivity";

    private static final int PERMISSION_REQUEST_CODE = 100; // 위치를 이용하기 위해 권한 요청
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private FusedLocationSource mLocationSource; // 최적 위치 반환을 위해 선언
    private NaverMap mNaverMap; // 네이버맵 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map); // activity_map 띄우기

        // 지도 객체 생성
        FragmentManager fm = getSupportFragmentManager(); // MapActivity와 activity_map의 fragment를 연결해주는 매니저
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map); // activity_map의 fragment
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit(); // fragment를 activity_map 내의 map에 삽입
        }

        // getMapAsync를 호출하여 비동기로 onMapReady 콜백 메서드 호출
        mapFragment.getMapAsync(this);

        // 위치를 반환하는 구현체인 FusedLocationSource 생성
        mLocationSource = new FusedLocationSource(this, PERMISSION_REQUEST_CODE);
    }


    // onMapReady에서 NaverMap 객체를 받음
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Log.d( TAG, "onMapReady");

        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        mNaverMap = naverMap;
        mNaverMap.setLocationSource(mLocationSource);
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
        naverMap.addOnOptionChangeListener(() -> { // 지도 옵션 변경에 대한 이벤트 리스너 등록
            LocationTrackingMode mode = mNaverMap.getLocationTrackingMode(); // 위치추적모드 반환
            mLocationSource.setCompassEnabled(mode == LocationTrackingMode.Follow || mode == LocationTrackingMode.Face); // 나침반(카메라 좌표 이용 시) 사용
            // 권한확인. 결과는 onRequestPermissionsResult 콜백 매서드 호출
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // request code와 권한획득 여부 확인
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mNaverMap.setLocationTrackingMode(LocationTrackingMode.Face); // 위치추적 + 카메라 좌표 + 방향 을 움직이는 모드
           }
        }
        LocationOverlay locationOverlay = mNaverMap.getLocationOverlay(); // 지도로부터 위치 오버레이 객체를 가져옴.
        locationOverlay.setVisible(true); // 지도에 위치 오버레이 나타남.
    }
}

