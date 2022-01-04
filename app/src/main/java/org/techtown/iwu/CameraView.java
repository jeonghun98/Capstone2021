package org.techtown.iwu;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.io.IOException;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder holder; // Surface를 관리하는 공장장 객체

    //카메라 관리자 객체 참조 변수
    Camera camera;

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);

        holder = getHolder();//공장장 소환
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //카메라뷰가 화면에 그려낼 준비가 될때

        //카메라 객체 열기
        camera = Camera.open(0); //0: 후방 카메라, 1: 전방 카메라

        //카메라의 미리보기를 실행 전에 몇가지 설정
        try {
            camera.setPreviewDisplay(holder);

            //카메라는 무조건 가로방향임
            //프리뷰를 90도 회전
            camera.setDisplayOrientation(90);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        camera.stopPreview();

        //카메라 닫기
        camera.release();
        camera= null;
    }
}
