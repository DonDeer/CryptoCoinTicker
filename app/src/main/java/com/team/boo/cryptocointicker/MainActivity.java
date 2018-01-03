package com.team.boo.cryptocointicker;


import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.team.boo.cryptocointicker.model.Kursy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String mTAG = MainActivity.class.getName();
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;
    private TextView odpowiedzTextView;
    private Button przycisk;
    private Kursy kursObecny;
    private JsonArrayRequest jar;
    private JsonObjectRequest job;
    //private List<Kursy> mojeKursy = new ArrayList<>();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        przycisk = (Button) findViewById(R.id.myButton);
        odpowiedzTextView = (TextView) findViewById(R.id.myText);
    }

    private void SendRequestAndPrintResponse(String myString, final String myCurrency) {
        mRequestQueue = Volley.newRequestQueue(this);

        stringRequest = new StringRequest(myString, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String temp = new String(myCurrency + response + "\n\n");
                Log.i(mTAG, ""+response);
                odpowiedzTextView.append(temp);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(mTAG,"Error: " + error.toString());
            }
        });

        mRequestQueue.add(stringRequest);
    }

    public void getJsonAndTransformToGson(){
        mRequestQueue = Volley.newRequestQueue(this);
        jar = new JsonArrayRequest(url1,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(mTAG, ""+response.length());//tego nie wyswietla nawet
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject kursWaluty = (JSONObject)response.get(i);
                                Gson gson = new Gson();
                                kursObecny = gson.fromJson(kursWaluty.toString(), Kursy.class);
                                //mojeKursy.add(kursObecny);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        odpowiedzTextView.setText(kursObecny.getLast());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        odpowiedzTextView.setText(error.toString());
                    }
                }
        );
        mRequestQueue.add(jar);
    }

    public void getJsonObj(){
        mRequestQueue = Volley.newRequestQueue(this);
        job = new JsonObjectRequest(Request.Method.GET,url1, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject kursWaluty = response;
                        Gson gson = new Gson();
                        kursObecny = gson.fromJson(response.toString(),Kursy.class);
                        odpowiedzTextView.setText(response.toString());
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(job);
    }
    public void myButtonClicked(View view) throws InterruptedException {
        odpowiedzTextView.setText("");
        //getJsonAndTransformToGson();
        getJsonObj();
        /*
        SendRequestAndPrintResponse(url1,currency1);
        SendRequestAndPrintResponse(url2,currency2);
        SendRequestAndPrintResponse(url3,currency3);
        SendRequestAndPrintResponse(url4,currency4);
        SendRequestAndPrintResponse(url5,currency5);
        SendRequestAndPrintResponse(url6,currency6);
*/
      //  Intent i = new Intent(this, MyCheckService.class);
      //  startService(i);

    }
}
