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

public class WhereStampActivity extends AppCompatActivity {
    FrameLayout stampinmap;
    TextView wheretxt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_wherestamp); // oo호관 화면 실행

        Intent intent = getIntent();
        String where = intent.getStringExtra("where");
        wheretxt = findViewById(R.id.wheretxt);
        wheretxt.setText(where);

        stampinmap = (FrameLayout) findViewById(R.id.mapframe);
        stampinmap.addView(new PointView(this));

    }

    public String getWheretxt() {
        String whereis = (String) wheretxt.getText();
        return whereis;
    }



    public class PointView extends View {
        Paint paint = new Paint();

        public PointView(Context context) {
            super(context);
        }

        @Override
        public void onDraw(Canvas canvas) {
            Bitmap img = BitmapFactory.decodeResource(getResources(),R.drawable.marker);
            Bitmap bigimg = Bitmap.createScaledBitmap(img, img.getWidth()/16, img.getHeight()/16, false);
            if (getWheretxt().matches("1호관")) canvas.drawBitmap(bigimg, 727, 820, paint); // 1호관
            else if(getWheretxt().matches("2호관")) canvas.drawBitmap(bigimg, 790, 700, paint); // 2호관
            else if (getWheretxt().matches("5호관")) canvas.drawBitmap(bigimg, 545, 980, paint); // 5호관
            else if (getWheretxt().matches("6호관")) canvas.drawBitmap(bigimg, 450, 855, paint); // 6호관
            else if (getWheretxt().matches("7호관")) canvas.drawBitmap(bigimg, 335, 795, paint); // 7호관
            else if (getWheretxt().matches("8호관")) canvas.drawBitmap(bigimg, 235, 695, paint); // 8 호관
            else if (getWheretxt().matches("11호관")) canvas.drawBitmap(bigimg, 365, 530, paint); // 11 호관
            else if (getWheretxt().matches("12호관")) canvas.drawBitmap(bigimg, 485, 610, paint); // 12 호관
            else if (getWheretxt().matches("14호관")) canvas.drawBitmap(bigimg, 680, 600, paint); // 14 호관
            else if (getWheretxt().matches("15호관")) canvas.drawBitmap(bigimg, 540, 478, paint); // 15 호관
            else if (getWheretxt().matches("16호관")) canvas.drawBitmap(bigimg, 430, 400, paint); // 16 호관
            else if (getWheretxt().matches("17호관")) canvas.drawBitmap(bigimg, 360, 360, paint); // 17 호관
            else if (getWheretxt().matches("18호관")) canvas.drawBitmap(bigimg, 320, 270, paint); // 18 호관
            else if (getWheretxt().matches("20호관")) canvas.drawBitmap(bigimg, 480, 150, paint); // 20 호관
            else if (getWheretxt().matches("23호관")) canvas.drawBitmap(bigimg, 606, 1220, paint); // 23호관
            else if (getWheretxt().matches("24호관")) canvas.drawBitmap(bigimg, 606, 1220, paint); // 24호관
            else if (getWheretxt().matches("28호관")) canvas.drawBitmap(bigimg, 0, 670, paint); // 28호관
            else if (getWheretxt().matches("29호관")) canvas.drawBitmap(bigimg, 105, 555, paint); // 29호관
            else if (getWheretxt().matches("30호관")) canvas.drawBitmap(bigimg, 255, 1020, paint); // 30호관
            else if (getWheretxt().contains("미유")) canvas.drawBitmap(bigimg, 105, 555, paint); // 미유카페
            else if (getWheretxt().contains("솔찬")) canvas.drawBitmap(bigimg, 123, 135, paint); // 솔찬공원
        }
    }
}
