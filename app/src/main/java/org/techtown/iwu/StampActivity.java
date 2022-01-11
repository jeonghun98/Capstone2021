package org.techtown.iwu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StampActivity extends AppCompatActivity {
    Button Mapstamp;
    String mapselect;
    TextView u_mid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int MajorCode = intent.getIntExtra("u_mid", 29);

        if (1 <= MajorCode && MajorCode <= 6) mapselect = "15호관";//
        else if (7 <= MajorCode && MajorCode <= 11) mapselect = "5호관";//
        else if (12 <= MajorCode && MajorCode <= 21) mapselect = "13호관";//
        else if (22 <= MajorCode && MajorCode <= 29) mapselect = "8호관";//
        else if (30 <= MajorCode && MajorCode <= 32) mapselect = "7호관";//
        else if (33 <= MajorCode && MajorCode <= 34) mapselect = "14호관";//
        else if (35 <= MajorCode && MajorCode <= 39) mapselect = "16호관";//
        else if (40 <= MajorCode && MajorCode <= 47) mapselect = "20호관";//
        else if (48 <= MajorCode && MajorCode <= 50) mapselect = "28호관";
        else if (51 <= MajorCode && MajorCode <= 53) mapselect = "29호관";

        u_mid = (TextView) findViewById(R.id.stamp12txt);
        u_mid.setText(mapselect);

        Mapstamp = (Button) findViewById(R.id.MapStamp);
        Mapstamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapInStampActivity.class); // MapInStampActivity 실행
                startActivity(intent);
            }
        });

    }

    @OnClick(R.id.stamp1) void where1(){
        Intent intent = new Intent(StampActivity.this, WhereStampActivity.class);
        intent.putExtra("where", "1호관");
        startActivity(intent);
    }

    @OnClick(R.id.stamp2) void where2() {
        Intent intent = new Intent(StampActivity.this, WhereStampActivity.class);
        intent.putExtra("where", "2호관");// stamp내의 map 실행
        startActivity(intent);
    }

    @OnClick(R.id.stamp3) void where3() {
        Intent intent = new Intent(StampActivity.this, WhereStampActivity.class);
        intent.putExtra("where", "6호관");// stamp내의 map 실행
        startActivity(intent);
    }

    @OnClick(R.id.stamp4) void where4() {
        Intent intent = new Intent(StampActivity.this, WhereStampActivity.class);
        intent.putExtra("where", "11호관");// stamp내의 map 실행
        startActivity(intent);
    }

    @OnClick(R.id.stamp5) void where5(){
        Intent intent = new Intent(StampActivity.this, WhereStampActivity.class);
        intent.putExtra("where", "12호관");
        startActivity(intent);
    }

    @OnClick(R.id.stamp6) void where6(){
        Intent intent = new Intent(StampActivity.this, WhereStampActivity.class);
        intent.putExtra("where", "17호관");
        startActivity(intent);
    }

    @OnClick(R.id.stamp7) void where7(){
        Intent intent = new Intent(StampActivity.this, WhereStampActivity.class);
        intent.putExtra("where", "18호관");
        startActivity(intent);
    }

    @OnClick(R.id.stamp8) void where8(){
        Intent intent = new Intent(StampActivity.this, WhereStampActivity.class);
        intent.putExtra("where", "24호관");
        startActivity(intent);
    }

    @OnClick(R.id.stamp9) void where9() {
        Intent intent = new Intent(StampActivity.this, WhereStampActivity.class);
        intent.putExtra("where", "30호관");
        startActivity(intent);
    }

    @OnClick(R.id.stamp10) void where10() {
        Intent intent = new Intent(StampActivity.this, WhereStampActivity.class);
        intent.putExtra("where", "미유카페");
        startActivity(intent);
    }

    @OnClick(R.id.stamp11) void where11() {
        Intent intent = new Intent(StampActivity.this, WhereStampActivity.class);
        intent.putExtra("where", "솔찬공원");
        startActivity(intent);
    }

    @OnClick(R.id.stamp12) void where12() {
        Intent intent = new Intent(StampActivity.this, WhereStampActivity.class);
        intent.putExtra("where", mapselect);
        startActivity(intent);
    }

}
