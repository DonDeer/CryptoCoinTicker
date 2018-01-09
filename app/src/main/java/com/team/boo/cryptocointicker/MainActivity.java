package com.team.boo.cryptocointicker;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.team.boo.cryptocointicker.model.BitApi;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private static final String mTAG = MainActivity.class.getName();
    private RequestQueue mRequestQueue;

    private TextView odpTextView;
    private Button startButton;
    private BitApi bitfinexObj;
    private JsonObjectRequest job;
    private Handler handler;

    private final String bitfinexUrl = "https://api.bitfinex.com/v1/pubticker/";
    private final String btcusdString = "btcusd";
    private final String bchusdString = "bchusd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        odpTextView = (TextView) findViewById(R.id.odpTextView);
        mRequestQueue = Volley.newRequestQueue(this);
        handler = new Handler();

    }

    public BitApi getObjBitF(String url, String currency) {

        job = new JsonObjectRequest(Request.Method.GET, url + currency, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        bitfinexObj = gson.fromJson(response.toString(), BitApi.class);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        mRequestQueue.add(job);
        return bitfinexObj;
    }

    public void myButtonClicked(View view) throws InterruptedException {
        //odpTextView.setText("Bitfinex Public REST\n\n");

        getObjBitF(bitfinexUrl, btcusdString);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String s = bitfinexObj.getLastPrice().toString();
                odpTextView.append(s);
            }
        },1000);

        //  Intent i = new Intent(this, MyCheckService.class);
        //  startService(i);

    }
}
