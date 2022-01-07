package com.example.iwu_quiz;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class QuizRequest extends StringRequest {

    //서버 url 설정(php파일 연동)
    final static  private String URL="http://hun9898.dothome.co.kr/Quiz.php";
    private Map<String,String> map;

    public QuizRequest(int b_id, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("b_id",b_id + "");

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}