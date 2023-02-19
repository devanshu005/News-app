package com.example.newsbychoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity implements myAdapter.ClickInterface {
    RecyclerView recv;
    myAdapter adapter;
    ArrayList<model>data;
    String link;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        data=new ArrayList<>();
        progressBar=findViewById(R.id.progressBar);

        recv=findViewById(R.id.recv);
        recv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter=new myAdapter(this,data,this::onClickopen);
        recv.setAdapter(adapter);
        Intent intent=getIntent();//receive intent from activity1
        link=intent.getStringExtra("url");
        run1();

    }
    private void run1()
    {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = response.getJSONArray("articles");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        JSONObject sources = object.getJSONObject("source");
                        String name = sources.getString("name");
                        String titles = object.getString("title");
                        String descriptions = object.getString("description");
                        String images = object.getString("urlToImage");
                        String urls = object.getString("url");
                        data.add(new model(images,name,titles,descriptions,urls));
                        adapter.notifyDataSetChanged();

                    }
                } catch (JSONException exception) {
                    exception.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this,"Something went wrong",Toast.LENGTH_SHORT).show();


            }
        })
        {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };
requestQueue.add(jsonObjectRequest);


    }

    @Override
    public void onClickopen(int position) {
       String url=data.get(position).getLink();
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);// open url in chrome
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}