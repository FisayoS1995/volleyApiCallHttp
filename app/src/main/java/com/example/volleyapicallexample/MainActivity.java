package com.example.volleyapicallexample;

import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName() ;
    private static final String REQUESTTAG = " STRING REQUEST FIRST" ;
    private Button btnApiCall;
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;
    private String url = "http://www.mocky.io/v2/5cb76d623200007647cd4ae4";
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnApiCall = (Button) findViewById(R.id.btnApiCall);

        btnApiCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send request and print the request using volley

                sendRequestAndPrintRequest();

            }
        });
    }

    private void sendRequestAndPrintRequest() {

        mRequestQueue = Volley.newRequestQueue(this);
        // this will get the request

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Response : " +response.toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error :" +error.toString());

            }
        });

        mRequestQueue.add(stringRequest);
        // once we implement the add function this means the request has been filed


        stringRequest.setTag(REQUESTTAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mRequestQueue !=null);
        {
            mRequestQueue.cancelAll(REQUESTTAG);
            // THIS MEANS THAT VOLLEY WILL CANCEL THE STRINGREQUEST API call if called upon
        }
    }
}