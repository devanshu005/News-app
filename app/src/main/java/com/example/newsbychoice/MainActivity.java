package com.example.newsbychoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
     Spinner spinner,spinner2,spinner3;
     EditText editTextTextPersonName;
     String q="",l,c,ct,qu,lang="",category="",country="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spinner);
        spinner2=findViewById(R.id.spinner2);
        spinner3=findViewById(R.id.spinner3);
        editTextTextPersonName=findViewById(R.id.editTextTextPersonName);

    }

    public void onClick2(View view) {
        q=editTextTextPersonName.getText().toString();
        l=spinner3.getSelectedItem().toString();
        lang=l.substring(l.length()-2);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
Intent intent=new Intent(this,MainActivity2.class);
intent.putExtra("url","https://newsapi.org/v2/everything?q="+q+"&language="+lang+"&from="+date+"&to="+date+"&sortBy=relevancy&apiKey=394b2e622fac4efc8813d72b69b22ff6");
            startActivity(intent);

    }

    public void onClick1(View view) {
        c=spinner.getSelectedItem().toString();
        country=c.substring(c.length()-2);
        ct=spinner2.getSelectedItem().toString();

        Intent intent=new Intent(this,MainActivity2.class);//go to next screen
            intent.putExtra("url","https://newsapi.org/v2/top-headlines?country="+country+"&category="+ct+"&apiKey=394b2e622fac4efc8813d72b69b22ff6");
        startActivity(intent);
    }
}