package com.team.boo.cryptocointicker;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    private RequestQueue mRequesQueue;
    private StringRequest stringRequest;

    private String url1 = "https://bitbay.net/API/Public/LSKPLN/ticker.json";
    private String url2 = "https://bitbay.net/API/Public/BTCPLN/ticker.json";
    private String url3 = "https://bitbay.net/API/Public/BCCPLN/ticker.json";
    private String url4 = "https://bitbay.net/API/Public/LTCPLN/ticker.json";
    private String url5 = "https://bitbay.net/API/Public/GAMEPLN/ticker.json";
    private String url6 = "https://bitbay.net/API/Public/ETHPLN/ticker.json";

    private static final String mTAG = MainActivity.class.getName();

    private TextView odp;
    String outputOdp;

    private Button przycisk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        przycisk = (Button) findViewById(R.id.myButton);
        odp = (TextView) findViewById(R.id.myText);
    }

    private void SendRequestAndPrintResponse(String myString) {
        mRequesQueue = Volley.newRequestQueue(this);

        stringRequest = new StringRequest(myString, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(mTAG,"Response: " + response.toString());

                String temp = new String(odp.getText().toString()) + "\n\n" + response.toString();
                odp.setText(temp);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(mTAG,"Error: " + error.toString());
            }
        });

        mRequesQueue.add(stringRequest);
    }

    public void myButtonClicked(View view) {
        odp.setText("");

        SendRequestAndPrintResponse(url1);
        SendRequestAndPrintResponse(url2);
        SendRequestAndPrintResponse(url3);
        SendRequestAndPrintResponse(url4);
        SendRequestAndPrintResponse(url5);
        SendRequestAndPrintResponse(url6);

        Intent i = new Intent(this, MyCheckService.class);
        startService(i);

    }
}
