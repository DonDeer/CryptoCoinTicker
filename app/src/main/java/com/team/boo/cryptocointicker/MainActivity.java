package com.team.boo.cryptocointicker;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.team.boo.cryptocointicker.model.BitApi;
import com.team.boo.cryptocointicker.model.Kursy;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private static final String mTAG = MainActivity.class.getName();
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;
    private TextView odpowiedzTextView;
    private Button przycisk;
    private Kursy kursObecny;
    private BitApi bitfinexObj;
    private JsonObjectRequest job;
    private Handler handler;

    private String url1 = "https://bitbay.net/API/Public/LSKPLN/ticker.json";
    private String currency1 = "LSK: \n";
    private String url2 = "https://bitbay.net/API/Public/BTCPLN/ticker.json";
    private String currency2 = "BTC: \n";
    private String url3 = "https://bitbay.net/API/Public/BCCPLN/ticker.json";
    private String currency3 = "BCC: \n";
    private String url4 = "https://bitbay.net/API/Public/LTCPLN/ticker.json";
    private String currency4 = "LTC: \n";
    private String url5 = "https://bitbay.net/API/Public/GAMEPLN/ticker.json";
    private String currency5 = "GAME: \n";
    private String url6 = "https://bitbay.net/API/Public/ETHPLN/ticker.json";
    private String currency6 = "ETH: \n";

    private final String bitfinexUrl = "https://api.bitfinex.com/v1/";
    private final String bitfinexBtcUsdString = "pubticker/btcusd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        przycisk = (Button) findViewById(R.id.myButton);
        odpowiedzTextView = (TextView) findViewById(R.id.myText);
        mRequestQueue = Volley.newRequestQueue(this);
        //handler = new Handler();

    }

    public BitApi getObjBitF(String url, String currency) {

        job = new JsonObjectRequest(Request.Method.GET, url + currency, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        bitfinexObj = gson.fromJson(response.toString(), BitApi.class);
//                        String s = bitfinexObj.getLastPrice().toString();
//                        odpowiedzTextView.setText(s);
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
        odpowiedzTextView.setText("Bitfinex Public REST\n\n");


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                 getObjBitF(bitfinexUrl, bitfinexBtcUsdString);
            }
        };

new Thread(new Runnable() {
    @Override
    public void run() {
        Message message = new Message();
        handler.sendMessage(message);
    }
});
        String s = bitfinexObj.getLastPrice();

        //  Intent i = new Intent(this, MyCheckService.class);
        //  startService(i);

    }
}
