package com.deepshooter.retrofitdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.deepshooter.retrofitdemo.ui.NewsListActivity;
import com.deepshooter.retrofitdemo.ui.NewsListActivityWithCache;
import com.deepshooter.retrofitdemo.ui.PostDataActivity;

public class MainActivity extends AppCompatActivity {

    TextView vT_getDataListText , vT_getDataListCacheText , vT_postDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeValue();
    }


    private void initializeValue()
    {
        vT_getDataListText = (TextView) findViewById(R.id.vT_getDataListText);
        vT_getDataListCacheText = (TextView) findViewById(R.id.vT_getDataListCacheText);
        vT_postDataText = (TextView) findViewById(R.id.vT_postDataText);

        setValues();

    }


    private void setValues()
    {

        vT_getDataListText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, NewsListActivity.class);
                startActivity(intent);
            }
        });



        vT_getDataListCacheText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, NewsListActivityWithCache.class);
                startActivity(intent);
            }
        });



        vT_postDataText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, PostDataActivity.class);
                startActivity(intent);
            }
        });

    }
}
