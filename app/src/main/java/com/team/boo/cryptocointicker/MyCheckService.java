package com.team.boo.cryptocointicker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by bbulaj on 2017-12-05.
 */

public class MyCheckService extends Service {

    public static final String liskCourse = "https://bitbay.net/API/Public/LSKPLN/ticker.json";
    private RequestQueue mRequesQueue;
    private StringRequest stringRequest;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void SendRequestAndPrintResponse(String myString) {
        mRequesQueue = Volley.newRequestQueue(this);

        stringRequest = new StringRequest(myString, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.i(mTAG,"Response: " + response.toString());
                        String temp = new String(response.toString());

                        //odp.setText(temp);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.i(mTAG,"Error: " + error.toString());

            }
        });

        mRequesQueue.add(stringRequest);
    }

    public void costam() {
        do {
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "próba", Toast.LENGTH_LONG);
            //SendRequestAndPrintResponse(myString);
        } while (true);
    }
}
