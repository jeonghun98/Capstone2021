package org.techtown.iwu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// 각 stamp 영역을 선택하면 나타나는 Activity

public class WhereStampActivity extends AppCompatActivity {
    FrameLayout stampinmap;
    TextView wheretxt;
    int mapselect;  //major에 따른 건물
    int where;      //stampactivity에서 선택한 건물

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_wherestamp); // oo호관 화면 실행

        Intent intent = getIntent();
        where = intent.getIntExtra("where", 1); // 몇 호관인지 정보 받아와서

        wheretxt = findViewById(R.id.wheretxt);
        wheretxt.setText(where+"호관"); // 글자 넣기

        mapselect = intent.getIntExtra("mapselect", 8); //u_mid default 29 -> mapselect 8

        stampinmap = (FrameLayout) findViewById(R.id.mapframe); // frame 영역에 위치 넣기
        stampinmap.addView(new PointView(this)); // frame 위에 그리기
    }

//    onDraw에서 if문을 보다 용이하게 사용하기 위해서 where과 mapselect를 string에서 int형으로 교체함
//    public String getWheretxt() { // 몇 호관인지 string으로 받아와서 return 하는 함수
//        String whereis = (String) wheretxt.getText();
//        return whereis;
//    }

    public class PointView extends View { // frame 위에 위치 나타내기 시작
        Paint paint = new Paint();

        public PointView(Context context) {
            super(context);
        }

        @Override
        public void onDraw(Canvas canvas) {
            Bitmap img = BitmapFactory.decodeResource(getResources(),R.drawable.marker);
            Bitmap img_red = BitmapFactory.decodeResource(getResources(),R.drawable.marker_red);
            Bitmap bigimg = Bitmap.createScaledBitmap(img, img.getWidth()/16, img.getHeight()/16, false);
            Bitmap bigimg_red = Bitmap.createScaledBitmap(img_red, img.getWidth()/11, img.getHeight()/11, false);

            //각 건물의 bitamp
            Bitmap stamp1, stamp2, stamp6, stamp11, stamp12, stamp17, stamp18, stamp24, stamp30, stamp31, stamp32, stampsele;

            //where은 -> bigimg_red, 나머지는 bigimag
            if(where == 1) stamp1 = bigimg_red; else stamp1 = bigimg;
            if(where == 2) stamp2 = bigimg_red; else stamp2 = bigimg;
            if(where == 6) stamp6 = bigimg_red; else stamp6 = bigimg;
            if(where == 11) stamp11 = bigimg_red; else stamp11 = bigimg;
            if(where == 12) stamp12 = bigimg_red; else stamp12 = bigimg;
            if(where == 17) stamp17 = bigimg_red; else stamp17 = bigimg;
            if(where == 18) stamp18 = bigimg_red; else stamp18 = bigimg;
            if(where == 24) stamp24 = bigimg_red; else stamp24 = bigimg;
            if(where == 30) stamp30 = bigimg_red; else stamp30 = bigimg;
            if(where == 31) stamp31 = bigimg_red; else stamp31 = bigimg;
            if(where == 32) stamp32 = bigimg_red; else stamp32 = bigimg;
            if(where == mapselect) stampsele = bigimg_red; else stampsele = bigimg;

            //기본 건물 11개 찍기
            canvas.drawBitmap(stamp1, 727, 820, paint); // 1호관 찍히는 위치
            canvas.drawBitmap(stamp2, 790, 700, paint); // 2호관
            canvas.drawBitmap(stamp6, 450, 855, paint); // 6호관
            canvas.drawBitmap(stamp11, 365, 530, paint); // 11 호관
            canvas.drawBitmap(stamp12, 485, 610, paint); // 12 호관
            canvas.drawBitmap(stamp17, 360, 360, paint); // 17 호관
            canvas.drawBitmap(stamp18, 320, 270, paint); // 18 호관
            canvas.drawBitmap(stamp24, 606, 1220, paint); // 24호관
            canvas.drawBitmap(stamp30, 255, 1020, paint); // 30호관
            canvas.drawBitmap(stamp31, 105, 555, paint); // 미유카페 >> 31
            canvas.drawBitmap(stamp32, 123, 135, paint); // 솔찬공원 >> 32

            //사용자의 전공(mapselect)에 따른 건물 찍기
            if (mapselect == 5) canvas.drawBitmap(stampsele, 545, 980, paint); // 5호관
            else if (mapselect == 7) canvas.drawBitmap(stampsele, 335, 795, paint); // 7호관
            else if (mapselect == 8) canvas.drawBitmap(stampsele, 235, 695, paint); // 8호관
            else if (mapselect == 13) canvas.drawBitmap(stampsele, 644, 702, paint); // 13 호관
            else if (mapselect == 14) canvas.drawBitmap(stampsele, 680, 600, paint); // 14 호관
            else if (mapselect == 15) canvas.drawBitmap(stampsele, 540, 478, paint); // 15 호관
            else if (mapselect == 16) canvas.drawBitmap(stampsele, 430, 400, paint); // 16 호관
            else if (mapselect == 20) canvas.drawBitmap(stampsele, 480, 150, paint); // 20 호관
            //else if (mapselect == 23) canvas.drawBitmap(stampsele, 606, 1220, paint); // 23호관 강당(mapselect에 없어서 일단 제외함)
            else if (mapselect == 28) canvas.drawBitmap(stampsele, 0, 670, paint); // 28호관
            else if (mapselect == 29) canvas.drawBitmap(stampsele, 105, 555, paint); // 29호관
        }
    }
}
