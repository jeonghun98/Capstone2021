package org.techtown.iwu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// Stamp 화면 내의 2D MAP 스탬프 현황 보는 Activity

public class MapInStampActivity extends AppCompatActivity {
    FrameLayout stampinmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapinstamp); // stamp내의 map 실행
        stampinmap = (FrameLayout) findViewById(R.id.mapframe); // mapframe 받아오기 (이 위에 좌표 표시)
        stampinmap.addView(new PointView(this));
    }

    private class PointView extends View { // 좌표 찍는(그리는) class
        Paint paint = new Paint();

        public PointView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap img = BitmapFactory.decodeResource(getResources(),R.drawable.flag); // flag 그림으로 찍기 위한 설정
            Bitmap bigimg = Bitmap.createScaledBitmap(img, img.getWidth()/16, img.getHeight()/16, false); // 그림 설정
            canvas.drawBitmap(bigimg, 727, 820, paint); // 1호관 위치에 스탬프 찍기
            canvas.drawBitmap(bigimg, 790, 700, paint); // 2호관
            canvas.drawBitmap(bigimg, 545, 980, paint); // 5호관
            canvas.drawBitmap(bigimg, 617, 1180, paint); // 24호관
            canvas.drawBitmap(bigimg, 123, 135, paint); // 솔찬공원
        }
    }
}