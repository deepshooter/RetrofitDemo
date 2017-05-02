package com.deepshooter.retrofitdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.deepshooter.retrofitdemo.R;
import com.deepshooter.retrofitdemo.customs.SnackBar;
import com.deepshooter.retrofitdemo.ui.getNewsDetails.GetNewsDetails;
import com.deepshooter.retrofitdemo.utils.OnResponseListner;
import com.deepshooter.retrofitdemo.utils.WebServices;

public class NewsDetailsActivity extends AppCompatActivity implements OnResponseListner {


    String ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        ID = getIntent().getStringExtra("id");
        callNewsDetailsService(ID);

    }


    private void callNewsDetailsService(String id){

        WebServices<GetNewsDetails> newsDetailsWebServices = new WebServices<>(NewsDetailsActivity.this);
        newsDetailsWebServices.getNewsDetails(WebServices.PublicDevService, WebServices.ApiType.getNewsDetails,id);
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces) {

        if (URL == WebServices.ApiType.getNewsDetails) {

            if (isSucces) {

                GetNewsDetails getNewsDetails = (GetNewsDetails) response;
                Log.e("NewsDetails",getNewsDetails.getResponse()+"");

            }else {

                SnackBar.makeText(NewsDetailsActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }

        }

    }



}
